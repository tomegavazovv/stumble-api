package com.example.stumble.authentication.authenticationProvider;

import com.example.stumble.authentication.userDetails.CustomUserDetails;
import com.example.stumble.authentication.userDetailsService.JpaUserDetailsService;
import com.example.stumble.authentication.UsernamePasswordAuth;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class UsernamePasswordAuthProvider implements AuthenticationProvider {
    private final JpaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthProvider(JpaUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        auth(email, password);
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuth.class.isAssignableFrom(aClass);
    }

    public void auth(String email,String password){
        CustomUserDetails user = userDetailsService.loadUserByUsername(email);
            if (passwordEncoder.matches(password, user.getPassword()))
                return;
            else
                throw new BadCredentialsException("Bad credentials.");
    }
}
