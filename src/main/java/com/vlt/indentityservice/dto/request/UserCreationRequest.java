package com.vlt.indentityservice.dto.request;

import com.vlt.indentityservice.validator.DobConstraint;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "INVALID_LENGTH") // Dùng chung cho lỗi độ dài
    @NotBlank(message = "MISSING_REQUIRED_FIELD") // Dùng chung cho lỗi bỏ trống
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",
            message = "INVALID_FORMAT" // Dùng chung cho lỗi sai định dạng (regex)
    )
    String username;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            // Giữ nguyên regex độ khó mật khẩu của bạn
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$",
            message = "WEAK_PASSWORD" // Riêng mật khẩu nên để 1 mã riêng vì nó đặc thù
    )
    String password;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT" // Tái sử dụng INVALID_FORMAT
    )
    String firstName;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT" // Tái sử dụng INVALID_FORMAT
    )
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    @NotNull
    LocalDate dob;

    Set<String> roles;
}