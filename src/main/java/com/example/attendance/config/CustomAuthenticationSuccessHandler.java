package com.example.attendance.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.attendance.entity.Account;
import com.example.attendance.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        String employeeId = authentication.getName();
        HttpSession session = request.getSession();

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/managerhome");
        } else {
        	Account account = userService.findByEmployeeId(employeeId);
        	if (account != null) {
                session.setAttribute("employeeId", employeeId);
                session.setAttribute("Name", account.getName());
            }
            response.sendRedirect("/userhome");
        }
    }
}