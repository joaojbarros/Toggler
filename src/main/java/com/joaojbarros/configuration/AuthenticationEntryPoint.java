package com.joaojbarros.configuration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaojbarros.model.ErrorResponse;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse error = new ErrorResponse();
        error.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        error.setMessage(authEx.getMessage());
        error.setException(authEx.getClass().getName());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(new Date().getTime());
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();
        response.addHeader("Content-Type", "application/json");
        writer.println(mapper.writeValueAsString(error));
    }
	
	@Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }

}
