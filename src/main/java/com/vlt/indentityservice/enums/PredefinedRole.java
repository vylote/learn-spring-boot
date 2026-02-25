package com.vlt.indentityservice.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum PredefinedRole {
    ADMIN("System administrator"),
    USER("User");

    String description;
}
