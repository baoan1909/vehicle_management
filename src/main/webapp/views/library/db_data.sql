use vehicle_management;
-- Thêm dữ liệu cho VehicleType
INSERT INTO VehicleType (VehicleTypeName, Description) VALUES
('Xe đạp', 'Phù hợp với khu công nghiệp'),
('Xe máy', 'Xe hai bánh động cơ'),
('Xe hơi', 'Dùng cho cá nhân hoặc dịch vụ');

-- Thêm dữ liệu cho Customer
INSERT INTO Customer (FullName, DateOfBirth, Gender, PhoneNumber, Address, Email, IdentifyCard) VALUES
('Nguyễn Văn A', '1990-05-15', 'Nam', '0987654321', 'Hà Nội', 'a@gmail.com', '123456789'),
('Trần Thị B', '1985-08-22', 'Nữ', '0912345678', 'TP.HCM', 'b@gmail.com', '987654321'),
('Lê Văn C', '1995-12-10', 'Nam', '0909090909', 'Đà Nẵng', 'c@gmail.com', '567890123');

-- Thêm dữ liệu cho Role
INSERT INTO Role (RoleName) VALUES ('Admin'), ('User');

-- Thêm dữ liệu cho Account
INSERT INTO Account (UserName, HashPassword, CustomerId, RoleId, Status) VALUES
('nguyenvana', 'hashedpass1', 1, 1, 1),
('tranthib', 'hashedpass2', 2, 2, 1),
('levanc', 'hashedpass3', 3, 2, 1);

-- Thêm dữ liệu cho TicketType
INSERT INTO TicketType (TicketTypeName, Description) VALUES
('Vé tháng', 'Dành cho khách đăng ký tháng'),
('Vé ngày', 'Dành cho khách vãng lai'),
('Vé VIP', 'Dành cho khách hàng cao cấp');

-- Thêm dữ liệu cho ParkingFeeOfVisitor
INSERT INTO ParkingFeeOfVisitor (Price, VehicleTypeId, StartDate) VALUES
(3000, 1, '2024-01-01'),
(5000, 2, '2024-01-01'),
(30000, 3, '2024-01-01');

-- Thêm dữ liệu cho ParkingFeeOfCustomer
INSERT INTO ParkingFeeOfCustomer (TicketTypeId, VehicleTypeId, Price, Description, StartDate) VALUES
(1, 1, 84000, 'Vé tháng xe đạp', '2024-01-01'),
(1, 2, 140000, 'Vé tháng xe máy', '2024-01-01'),
(1, 3, 840000, 'Vé tháng xe hơi', '2024-01-01');

-- Thêm dữ liệu cho Card
INSERT INTO Card (CardNumber, Type, VehicleTypeId, IsCreated, IsUsed) VALUES
('C001', 'Đăng ký', 1, TRUE, FALSE),
('C002', 'Vãng lai', 2, TRUE, TRUE),
('C003', 'Đăng ký', 3, TRUE, TRUE);

-- Thêm dữ liệu cho CustomerRegisterTicket
INSERT INTO CustomerRegisterTicket (CardId, CustomerId, FeeCustomerId, EffectiveDate, ExpirationDate, LicensePlate, VehicleTypeId, CardReceiptDate, TicketTypeId, Price) VALUES
(1, 1, 1, '2024-01-01', '2024-12-31', '29A-12345', 1, '2024-01-01', 1, 84000),
(2, 2, 2, '2024-01-01', '2024-12-31', '59B-67890', 2, '2024-01-01', 1, 140000),
(3, 3, 3, '2024-01-01', '2024-12-31', '43C-11223', 3, '2024-01-01', 1, 840000);

-- Thêm dữ liệu cho LostCard
INSERT INTO LostCard (CustomerId, CardId, NotificationTime, TimeOfLost, TicketPrice, LostCardFee, CheckInLicensePhoto, CheckInCustomerPhoto, VisitorName, VisitorPhoneNum, IdentifyCard, RegistrationLicense, Note) VALUES
(1, 1, '2024-03-01 08:00:00', '2024-03-01 07:30:00', 100000, 5000, 'photo1.jpg', 'cust1.jpg', NULL, NULL, NULL, 'Reg1', 'Làm lại thẻ'),
(NULL, 2, '2024-03-05 12:30:00', '2024-03-05 11:00:00', 100000, 30000, 'photo2.jpg', 'cust2.jpg', 'Nguyễn Văn D', '0977555544', '321456987', 'Reg2', 'Khách vãng lai - mất thẻ'),
(3, 3, '2024-03-10 18:00:00', '2024-03-10 17:30:00', 100000, 5000, 'photo3.jpg', 'cust3.jpg', NULL, NULL, NULL, 'Reg3', 'Báo cáo mất thẻ');

-- Thêm dữ liệu cho CardSwipe
INSERT INTO CardSwipe (LicensePlate, CardId, CheckInTime, CheckOutTime, CheckInImagePath, ImagePathOut, Price, VehicleTypeId) VALUES
('29A-12345', 1, '2024-03-01 07:00:00', '2024-03-01 18:00:00', 'checkin1.jpg', 'checkout1.jpg', NULL, 1),
('59B-67890', 2, '2024-03-05 10:00:00', '2024-03-05 20:00:00', 'checkin2.jpg', 'checkout2.jpg', NULL, 2),
('43C-11223', 3, '2024-03-10 16:00:00', NULL, 'checkin3.jpg', NULL, NULL, 3);
