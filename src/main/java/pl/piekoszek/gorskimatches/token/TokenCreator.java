package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenCreator {

    public String jwtToken(String email) {

        String jwtToken;
        {
            jwtToken = Jwts.builder()
                    .claim("email", email)
                    .setId(UUID.randomUUID().toString())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(Instant.now().plus(20l, ChronoUnit.MINUTES)))
                    .compact();
        }
        return jwtToken;
    }
}
