from flask import Flask, request, jsonify, render_template
import cv2
import numpy as np
import torch
from src.char_classification.model import CNN_Model
from utils_LP import character_recog_CNN, crop_n_rotate_LP
from models.experimental import attempt_load
from detect import detect
import os

app = Flask(__name__)

# Load models (you should adjust these paths based on your environment)
CHAR_CLASSIFICATION_WEIGHTS = './src/weights/weight.h5'
LP_weights = 'LP_detect_yolov7_500img.pt'

model_char = CNN_Model(trainable=False).model
model_char.load_weights(CHAR_CLASSIFICATION_WEIGHTS)

# Check for GPU availability
if torch.cuda.is_available():
    device = torch.device("cuda")
else:
    device = torch.device("cpu")

model_LP = attempt_load(LP_weights, map_location=device)

# Route to serve the HTML page
@app.route('/')
def index():
    return render_template('index.html')  # Ensure your HTML file is saved as 'index.html'

# API to process image and detect license plate
@app.route('/detect_license_plate', methods=['POST'])
def detect_license_plate():
    # Get image from request (supporting file uploads)
    if 'image' not in request.files:
        return jsonify({'error': 'No image file provided'}), 400
    
    image_file = request.files['image']
    if not image_file:
        return jsonify({'error': 'No image file provided'}), 400

    # Read the image from the file
    try:
        image_bytes = image_file.read()
        image_np = np.array(bytearray(image_bytes), dtype=np.uint8)
        source_img = cv2.imdecode(image_np, cv2.IMREAD_COLOR)
    except Exception as e:
        return jsonify({'error': f'Failed to read image: {str(e)}'}), 500

    # Detect license plate
    pred, LP_detected_img = detect(model_LP, source_img, device, imgsz=640)

    c = 0
    strFinalString = ""

    for *xyxy, conf, cls in reversed(pred):
        x1, y1, x2, y2 = int(xyxy[0]), int(xyxy[1]), int(xyxy[2]), int(xyxy[3])
        angle, rotate_thresh, LP_rotated = crop_n_rotate_LP(source_img, x1, y1, x2, y2)

        if (rotate_thresh is None) or (LP_rotated is None):
            continue

        # Character recognition
        LP_rotated_copy = LP_rotated.copy()
        cont, hier = cv2.findContours(rotate_thresh, cv2.RETR_CCOMP, cv2.CHAIN_APPROX_SIMPLE)
        cont = sorted(cont, key=cv2.contourArea, reverse=True)[:17]

        char_x = []
        height, width, _ = LP_rotated_copy.shape
        roiarea = height * width

        for ind, cnt in enumerate(cont):
            (x, y, w, h) = cv2.boundingRect(cont[ind])
            ratiochar = w / h
            char_area = w * h
            if (0.01 * roiarea < char_area < 0.09 * roiarea) and (0.25 < ratiochar < 0.7):
                char_x.append([x, y, w, h])

        if not char_x:
            continue

        char_x = np.array(char_x)
        
        threshold_12line = char_x[:, 1].min() + (char_x[:, 3].mean() / 2)
        char_x = sorted(char_x, key=lambda x: x[0], reverse=False)
        first_line = ""
        second_line = ""

        for i, char in enumerate(char_x):
            x, y, w, h = char
            imgROI = rotate_thresh[y:y + h, x:x + w]
            text = character_recog_CNN(model_char, imgROI)
            if text == 'Background':
                text = ''

            if y < threshold_12line:
                first_line += text
            else:
                second_line += text

        strFinalString = first_line + second_line
        cv2.putText(LP_detected_img, strFinalString, (x1, y1 - 20), cv2.FONT_HERSHEY_DUPLEX, 2, (255, 255, 0), 2)

        # Output the recognized license plate
        with open("license_plates.txt", "a", encoding="utf-8") as f:
            f.write(f"License Plate_{c}: {strFinalString}\n")
        
        c += 1

    # Get the file name from the image
    filename = image_file.filename

    return jsonify({
        'license_plate': strFinalString,
        'image_name': filename
    })

if __name__ == '__main__':
    app.run(debug=True)
