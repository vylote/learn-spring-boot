package com.vlt.indentityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapping;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;
}
