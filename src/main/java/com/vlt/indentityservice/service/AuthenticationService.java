package com.vlt.indentityservice.service;

import com.vlt.indentityservice.dto.request.AuthenticationRequest;
import com.vlt.indentityservice.dto.response.AuthenticationResponse;
import com.vlt.indentityservice.entity.User;
import com.vlt.indentityservice.exceptiion.AppException;
import com.vlt.indentityservice.exceptiion.ErrorCode;
import com.vlt.indentityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public boolean authenticate(AuthenticationRequest request) {
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
