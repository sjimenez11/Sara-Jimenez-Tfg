package com.connectedReads.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.connectedReads.config.jwt.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
public class JwtAuthorizationFilter  extends
        BasicAuthenticationFilter {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    public JwtAuthorizationFilter(JwtTokenUtils jwtTokenUtils,
                                  UserDetailsService userDetailsService, AuthenticationManager
                                          authManager){
        super(authManager);
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsService = userDetailsService;
    }
    private Optional<UsernamePasswordAuthenticationToken>
    getAuthentication(String token){
        DecodedJWT tokenDecoded = jwtTokenUtils.decode(token);
        if(tokenDecoded == null){
            return Optional.empty();
        }
        String username =
                tokenDecoded.getClaim("username").toString();
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
        return Optional.of(new
                UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        ));
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(AUTHORIZATION);
        if (header == null || !header.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        getAuthentication(header.substring(7)).ifPresent(authentication->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication
            );
        });
        chain.doFilter(request, response);
    }
}
