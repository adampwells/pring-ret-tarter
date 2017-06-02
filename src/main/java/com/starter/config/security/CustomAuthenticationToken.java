package com.starter.config.security;

import com.starter.dto.SessionDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by adam.wells on 18/06/2016.
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private SessionDto sessionDto;

    public CustomAuthenticationToken(Object principal, Object credentials, List<GrantedAuthority> grantedAuthorities, SessionDto sessionDto) {
        super(principal, credentials, grantedAuthorities);
        this.sessionDto = sessionDto;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }
}
