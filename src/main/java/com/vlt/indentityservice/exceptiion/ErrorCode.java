package com.vlt.indentityservice.exceptiion;

public enum ErrorCode {
    UNCATEGORY_EXCEPTION(9999, "Uncategory error"),
    INVALID_KEY(1000, "Uncategory error"),
    USER_EXITED(1001, "User exited"),
    USER_NOT_FOUND(1002, "User Not Found"),
    USERNAME_INVALID_LENGTH(1003, "Username at least 3 characters"),
    IS_BLANK(1004, "Username not empty"),
    USERNAME_INVALID_CHARACTER(1005, "Username can only contain at least a letter and numbers"),
    PASSWORD_INVALID_CHARACTER(1006, "Password must have lowercase, uppercase letter, number, " +
            "special character (@,#,$,!,%,*,?,&) and at least 8 characters"),
    FIRSTNAME_INVALID_CHARACTER(1007, "Firstname can only contain letters and numbers"),
    LASTNAME_INVALID_CHARACTER(1008, "Lastname can only contain letters"),
    BIRTHDATE_INVALID(1009, "Birthdate can be in the future"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
