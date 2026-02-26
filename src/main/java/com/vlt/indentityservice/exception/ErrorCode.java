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
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1010, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "You don't have permission to do that", HttpStatus.FORBIDDEN),
    INVALID_KEY(999, "Invalid message key", HttpStatus.BAD_REQUEST),

    // --- Nhóm Lỗi Tài Nguyên (404, 409) ---
    RESOURCE_EXISTED(1001, "Resource already existed", HttpStatus.CONFLICT),
    RESOURCE_NOT_FOUND(1002, "Resource not found", HttpStatus.NOT_FOUND),

    // --- Nhóm Validate Dữ Liệu Đầu Vào (400 Bad Request) ---
    MISSING_REQUIRED_FIELD(1004, "Required field is missing", HttpStatus.BAD_REQUEST),

    // Đã thêm {min} vào đây để tận dụng GlobalExceptionHandler
    INVALID_LENGTH(1003, "Length must be at least {min} characters", HttpStatus.BAD_REQUEST),

    INVALID_FORMAT(1005, "Input format is invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT(1006, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1007, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),

    // Đã đổi mã 1007 thành 1008 để tránh trùng lặp với INVALID_DOB
    WEAK_PASSWORD(1008, "Password must include uppercase, lowercase, numbers, and special characters", HttpStatus.BAD_REQUEST),
    ;

    int code;
    String message;
    HttpStatusCode statusCode;
}