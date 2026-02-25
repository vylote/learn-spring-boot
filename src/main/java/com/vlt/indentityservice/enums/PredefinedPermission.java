package com.vlt.indentityservice.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum PredefinedPermission {
    CREATE_USER("Cho phép tạo mới tài khoản người dùng"),
    UPDATE_USER("Cho phép cập nhật thông tin người dùng"),
    DELETE_USER("Cho phép xóa người dùng khỏi hệ thống"),

    CREATE_ROLE("Cho phép tạo mới vai trò (Role)"),
    UPDATE_ROLE("Cho phép chỉnh sửa thông tin vai trò"),
    DELETE_ROLE("Cho phép xóa vai trò"),

    CREATE_PERMISSION("Cho phép định nghĩa quyền hạn mới"),
    UPDATE_PERMISSION("Cho phép thay đổi mô tả quyền hạn"),
    DELETE_PERMISSION("Cho phép xóa quyền hạn");

    String description;
}
