package com.vlt.indentityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    // --- Nhóm Lỗi Hệ Thống & Bảo Mật (5xx, 401, 403) ---
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR), // 500
    UNAUTHENTICATED(1010, "Unauthenticated", HttpStatus.UNAUTHORIZED), // 401: Chưa đăng nhập
    UNAUTHORIZED(1009, "You don't have permission to do that", HttpStatus.FORBIDDEN), // 403: Đăng nhập rồi nhưng k có quyền
    INVALID_KEY(999, "Invalid message key", HttpStatus.BAD_REQUEST), // 400: Lỗi dev sai key

    // --- Nhóm Lỗi Tài Nguyên (404, 409) ---
    // Dùng 409 Conflict cho trường hợp dữ liệu đã tồn tại (trùng username, email...)
    RESOURCE_EXISTED(1001, "Resource already existed", HttpStatus.CONFLICT), 
    
    // Dùng 404 Not Found khi không tìm thấy dữ liệu
    RESOURCE_NOT_FOUND(1002, "Resource not found", HttpStatus.NOT_FOUND), 

    // --- Nhóm Validate Dữ Liệu Đầu Vào (400 Bad Request) ---
    // Tất cả lỗi liên quan đến Form nhập liệu thường trả về 400
    MISSING_REQUIRED_FIELD(1004, "Required field is missing", HttpStatus.BAD_REQUEST), 
    INVALID_LENGTH(1003, "Input length is invalid", HttpStatus.BAD_REQUEST), 
    INVALID_FORMAT(1005, "Input format is invalid", HttpStatus.BAD_REQUEST), 
    INVALID_DOB(1007, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    // Thêm cái này nếu bạn tách lỗi password yếu ra riêng như đã bàn
    WEAK_PASSWORD(1006, "Password is too weak", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode statusCode;
}