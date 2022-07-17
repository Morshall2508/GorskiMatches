package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenCreator {
    public static String jwtToken(String email) {

        JwtBuilder tokenBuilder = Jwts.builder()
                .claim("email", email)
                .signWith(Keys.hmacShaKeyFor("oih5jireonjdgfssdgfs3atoigef1312321312nw4agf4w".getBytes()))
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(31, ChronoUnit.DAYS)));
        return tokenBuilder.compact();
    }
}
