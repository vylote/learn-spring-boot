package com.vlt.indentityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // 1. Giữ lại Bean này để dùng mã hóa mật khẩu trong Service/Mapper của bạn
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    // 2. Cấu hình này giúp "mở khóa" các API để bạn không bị hỏi mật khẩu ngẫu nhiên nữa
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để có thể gọi API từ Postman/Frontend
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép tất cả các request mà không cần login
                );
        return http.build();
    }
}
