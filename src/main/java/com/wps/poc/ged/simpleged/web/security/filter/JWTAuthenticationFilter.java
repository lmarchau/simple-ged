package com.wps.poc.ged.simpleged.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wps.poc.ged.simpleged.model.GedUser;
import com.wps.poc.ged.simpleged.web.security.domain.GedUserDetails;
import com.wps.poc.ged.simpleged.web.security.domain.Token;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wps.poc.ged.simpleged.web.security.util.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private ObjectMapper mapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            GedUser credentials = mapper.readValue(request.getInputStream(), GedUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        } catch (IOException e) {
            // FIXME --> send a dedicated exception
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        GedUserDetails gedUserDetails = (GedUserDetails) auth.getPrincipal();
        Token token = new Token();
        token.setToken(generateToken(gedUserDetails.getUsername()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token.getToken());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        mapper.writeValue(response.getWriter(), token);
    }
}
