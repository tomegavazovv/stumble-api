package com.example.stumble.authentication.filters;

import com.example.stumble.authentication.UsernamePasswordAuth;
import com.example.stumble.converters.UserDetailsConverter;
import com.example.stumble.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String username = body.split("\"")[3];
        String password = body.split("\"")[7];
        Authentication a = new UsernamePasswordAuth(username, password);
        manager.authenticate(a);

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setClaims(Map.of("username", username))
                .signWith(key)
                .compact();

        response.setHeader("Authorization", jwt);
        new MappingJackson2HttpMessageConverter()
                .write(new UserDetailsConverter().convert(userRepository.findUserByEmail(username).get()),
                        MediaType.APPLICATION_JSON,
                        new ServletServerHttpResponse(response));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
