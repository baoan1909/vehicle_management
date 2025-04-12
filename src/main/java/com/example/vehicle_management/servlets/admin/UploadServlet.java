package com.example.vehicle_management.servlets.admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(urlPatterns = "/upload", asyncSupported = true)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1 MB
        maxFileSize = 5 * 1024 * 1024,        // 5 MB
        maxRequestSize = 10 * 1024 * 1024     // 10 MB
)
public class UploadServlet extends HttpServlet {
    private static final String FLASK_API_URL = "http://127.0.0.1:5000/detect_license_plate";
    private static final int BUFFER_SIZE = 8192;
    private static final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cardSwipeId = request.getParameter("id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("id"));
        //----------Xử lý kết nối Api quét biển số xe--------------------------
        Part filePart = request.getPart("image");
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("error", "Chưa chọn ảnh để upload.");
            if (cardSwipeId == 0) {
                request.getRequestDispatcher("/views/admin/swipe/swipe-in.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("/views/admin/swipe/swipe-out.jsp").forward(request, response);
                return;
            }
        }

        // Validate file type
        String contentType = filePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            request.setAttribute("error", "Chỉ cho phép tải lên tập tin hình ảnh.");
            if (cardSwipeId == 0) {
                request.getRequestDispatcher("/views/admin/swipe/swipe-in.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("/views/admin/swipe/swipe-out.jsp").forward(request, response);
                return;
            }
        }

        HttpURLConnection conn = null;
        StringBuilder result = new StringBuilder();

        try {
            String boundary = Long.toHexString(System.currentTimeMillis());
            URL url = new URL(getFlaskApiUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);  // 10 seconds
            conn.setReadTimeout(30000);     // 30 seconds
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (
                    OutputStream output = conn.getOutputStream();
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);
                    InputStream inputStream = filePart.getInputStream()
            ) {
                String fileName = filePart.getSubmittedFileName();
                // Gửi phần dữ liệu file ảnh
                writer.append("--" + boundary).append("\r\n");
                writer.append("Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\"\r\n");
                writer.append("Content-Type: " + contentType).append("\r\n");
                writer.append("\r\n").flush();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                output.flush();
                writer.append("\r\n").flush();
                writer.append("--" + boundary + "--").append("\r\n").flush();
            }

            // Check response code
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Lỗi khi gọi Flask API. Mã lỗi: " + responseCode);
            }

            // Process response
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }

            // Parse JSON response
            JsonObject resultJson = gson.fromJson(result.toString(), JsonObject.class);
            request.setAttribute("plateNumber", resultJson.get("license_plate").getAsString());
            request.setAttribute("fileName", resultJson.get("image_name").getAsString());

        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi xử lý ảnh: " + e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        if (cardSwipeId == 0) {
            request.getRequestDispatcher("/views/admin/swipe/swipe-in.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/admin/swipe/swipe-out.jsp").forward(request, response);
        }
        //----------------------------------------------------------------------------------
    }

    private String getFlaskApiUrl () {
        return System.getProperty("flask.api.url", FLASK_API_URL);
    }
}