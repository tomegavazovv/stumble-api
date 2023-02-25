package com.example.stumble.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuth extends UsernamePasswordAuthenticationToken {

    public UsernamePasswordAuth(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public UsernamePasswordAuth(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
