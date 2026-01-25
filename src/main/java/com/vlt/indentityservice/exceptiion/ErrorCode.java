package com.vlt.indentityservice.exceptiion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORY_EXCEPTION(9999, "Uncategory error"),
    INVALID_KEY(999, "Uncategory error"),
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1002, "User not existed"),
    USERNAME_INVALID_LENGTH(1003, "Username at least 3 characters"),
    IS_BLANK(1004, "Information not empty"),
    USERNAME_INVALID_CHARACTER(1005, "Username can only contain at least a letter and numbers"),
    PASSWORD_INVALID_CHARACTER(1006, "Password must have lowercase, uppercase letter, number, " +
            "special character (@,#,$,!,%,*,?,&) and at least 8 characters"),
    FIRSTNAME_INVALID_CHARACTER(1007, "Firstname can only contain letters and numbers"),
    LASTNAME_INVALID_CHARACTER(1008, "Lastname can only contain letters"),
    BIRTHDATE_INVALID(1009, "Birthdate can be in the future"),
    ;
    int code;
    String message;
}
