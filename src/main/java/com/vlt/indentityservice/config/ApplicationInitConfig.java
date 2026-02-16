package com.vlt.indentityservice.config;

import java.util.HashSet;

import com.vlt.indentityservice.entity.Permission;
import com.vlt.indentityservice.entity.Role;
import com.vlt.indentityservice.enums.PredefinedPermission;
import com.vlt.indentityservice.repository.PermissionRepository;
import com.vlt.indentityservice.repository.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.enums.PredefinedRole;
import com.vlt.indentityservice.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {

            var allPermissions = new HashSet<Permission>();

            for (PredefinedPermission perm : PredefinedPermission.values()) {
                Permission permission = permissionRepository.findById(perm.name())
                        .orElseGet(() -> permissionRepository.save(
                                Permission.builder()
                                        .name(perm.name())
                                        .description("Quyền: " + perm.name())
                                        .build()
                        ));
                allPermissions.add(permission);
            }

            // 1. Luôn đảm bảo Role USER đã tồn tại (cho người dùng đăng ký sau này dùng)
            if (roleRepository.findById(PredefinedRole.USER.name()).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER.name())
                        .description("User role")
                        .build());
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                String adminRoleName = PredefinedRole.ADMIN.name();

                // 2. Tìm Role ADMIN trong DB. Nếu chưa có thì tạo mới và lưu xuống DB
                Role adminRole = roleRepository.findById(adminRoleName)
                        .orElseGet(() -> roleRepository.save(
                                Role.builder()
                                        .name(adminRoleName)
                                        .description("System Administrator") // Thêm mô tả nếu thích
                                        .permissions(allPermissions)
                                        .build()
                        ));

                // 3. Tạo Set chứa Entity Role (Không phải String nữa)
                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin"))
                             .roles(roles)
                            .build();

                userRepository.save(user);
                log.warn("admin user has been created with default passwd admin, please change it");
            }
        };
    }
}
