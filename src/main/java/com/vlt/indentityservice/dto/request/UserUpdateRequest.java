package com.vlt.indentityservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    
    // --- PASSWORD ---
    // Thay IS_BLANK -> MISSING_REQUIRED_FIELD
    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    // Thay PASSWORD_INVALID_CHARACTER -> WEAK_PASSWORD (Giữ riêng để báo lỗi chi tiết)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$",
            message = "WEAK_PASSWORD"
    )
    String password;

    // --- FIRSTNAME ---
    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    // Gom nhóm lỗi định dạng về INVALID_FORMAT
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT"
    )
    String firstName;

    // --- LASTNAME ---
    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    // Gom nhóm lỗi định dạng về INVALID_FORMAT
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT"
    )
    String lastName;

    // --- DOB ---
    // Dùng key INVALID_DOB như đã định nghĩa trong Enum
    @Past(message = "INVALID_DOB")
    LocalDate dob;
}