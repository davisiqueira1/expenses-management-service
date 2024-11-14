package com.davi.expensemanagementservice.config.auth;

import com.davi.expensemanagementservice.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final AppUserRepository appUserRepository;

    public SecurityFilter(TokenProvider tokenProvider, AppUserRepository appUserRepository) {
        this.tokenProvider = tokenProvider;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            var login = tokenProvider.validateToken(token);
            var user = appUserRepository.findByUsername(login);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    protected String recoverToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }

        return token.replace("Bearer ", "");
    }
}
