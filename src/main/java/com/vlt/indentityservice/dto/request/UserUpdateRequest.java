package com.vlt.indentityservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserUpdateRequest {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
