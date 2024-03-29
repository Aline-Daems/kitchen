package be.technobel.kitchen.pl.config.security;

import be.technobel.kitchen.dal.models.entities.Author;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class JWTProvider {

    // Signature
    private static final String JWT_SECRET = "6eH45Z3RdgwyF3";

    // Delais de validité du token
    private static final long EXPIRES_AT = 900_000;

    // en-tête
    private static final String Auth_HEADER = "Authorization";

    // Type
    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String username) {
        return TOKEN_PREFIX + JWT.create()
                .withSubject(username)
                .withClaim("authors", username)
                .withExpiresAt(Instant.now().plusMillis(EXPIRES_AT))
                .sign(Algorithm.HMAC512(JWT_SECRET));

    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader(Auth_HEADER);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        return header.replaceFirst(TOKEN_PREFIX, "");
    }

    public boolean validateToken(String token) {

        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(JWT_SECRET))
                    .acceptExpiresAt(EXPIRES_AT)
                    .withClaimPresence("sub")
                    .build().verify(token);

            String username = jwt.getSubject();
            Author author = (Author) userDetailsService.loadUserByUsername(username);

            if(!author.isEnabled())
                return false;

            return true;
        } catch (JWTVerificationException | UsernameNotFoundException ex) {
            return false;
        }

    }

    public Authentication createAuthentification(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

}
