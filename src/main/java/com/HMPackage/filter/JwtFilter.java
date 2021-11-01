package com.HMPackage.filter;

import com.HMPackage.serviceImplementation.UserServiceImpl;
import com.HMPackage.util.JwtUtils;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
     private UserServiceImpl userServiceImpl;

    JwtUtils jwtUtils = new JwtUtils();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = jwtUtils.getJwtFromRequest(request);

            if (StringUtils.hasText(token) && jwtUtils.validateToken(token, "secret")) {
                String name = jwtUtils.getUserNameFromJWTs(token, "secret");

                if (Strings.isNullOrEmpty(name)) {
                    request.getRequestDispatcher("/error" + "invalid").forward(request, response);
                }
                UserDetails userDetails = userServiceImpl.loadByUserName(name);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            request.getRequestDispatcher("/error" + ex.getMessage()).forward(request, response);
        }
    }
}
