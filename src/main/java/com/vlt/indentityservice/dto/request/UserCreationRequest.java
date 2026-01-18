package com.vlt.indentityservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID_LENGH")
    @NotBlank(message = "IS_BLANK")
    @Pattern(
            regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$",
            message = "USERNAME_INVALID_CHARACTER"
    )
    private String username;

    @NotBlank(message = "IS_BLANK")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&])[A-Za-z\\d@#$!%*?&]{8,}$",
            message = "PASSWORD_INVALID_CHARACTER"
    )
    private String password;

    @NotBlank(message = "IS_BLANK")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "FIRSTNAME_INVALID_CHARACTER"
    )
    private String firstName;

    @NotBlank(message = "IS_BLANK")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "LASTNAME_INVALID_CHARACTER"
    )
    private String lastName;

    @Past(message = "BIRTHDATE_INVALID")
    private LocalDate dob;
}
