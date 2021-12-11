package com.shop.shop.configuration.jwt;

import com.shop.shop.configuration.exception.NotFoundException;
import com.shop.shop.entity.User;
import com.shop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends GenericFilterBean {

    private JwtProvider jwtProvider;
    private UserService userService;
    private final String headerForAuth = "Authorization";

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(headerForAuth);
        boolean validate = jwtProvider.validate(token);
        if(validate){
            String emailFromToken = jwtProvider.getEmailFromToken(token);
            User user = userService.getByEmail(emailFromToken);
            if (user==null){
                throw new NotFoundException("Cannot find user");
            }
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        // UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, new SimpleGrantedAuthority(user.getRole().name()));
    }
}
