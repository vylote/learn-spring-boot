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

    @Size(min = 3, message = "INVALID_LENGTH")
    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",
            message = "INVALID_FORMAT"
    )
    String username;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]+$",
            message = "WEAK_PASSWORD"
    )
    @Size(min = 8, message = "PASSWORD_TOO_SHORT")
    String password;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT"
    )
    String firstName;

    @NotBlank(message = "MISSING_REQUIRED_FIELD")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "INVALID_FORMAT"
    )
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    @NotNull
    LocalDate dob;

    Set<String> roles;
}