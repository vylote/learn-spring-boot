package com.vlt.indentityservice.config;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.vlt.indentityservice.dto.response.ApiResponse;
import com.vlt.indentityservice.exceptiion.ErrorCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
    //khi gặp lỗi 401 spring kiểm tra xem có authentication entry point không, nếu có nó sẽ chạy hàm commence
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
        
        response.setStatus(errorCode.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        /* tại sao phải làm dòng này mà không return apiResponse như trong controller vì hàm commence nằm ở tầng
        Filter (Servlet) thấp hơn Controller không có spring hỗ trợ tự convert object sang json */
        
        //Đây là thư viện Jackson. Nó giúp biến đổi (Serialize) một Java Object (apiResponse) thành một chuỗi String JSON ({"code": 1010...}).
        ObjectMapper objectMapper = new ObjectMapper();
        //Chúng ta phải tự tay ghi chuỗi JSON đó vào luồng phản hồi
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));   
        //Đẩy dữ liệu đi ngay lập tức về phía Client, đảm bảo không bị kẹt lại trong bộ nhớ đệm server.
        response.flushBuffer();             
    }
    
}
