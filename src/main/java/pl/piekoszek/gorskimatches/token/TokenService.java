package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenService {

    private final SecretKey key;

    public TokenService(@Value("${token.key}") String key) {
        this.key = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String encode(String email) {
        JwtBuilder tokenBuilder = Jwts.builder()
                .claim("email", email)
                .signWith(key)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(31, ChronoUnit.DAYS)));
        return tokenBuilder.compact();
    }

    public TokenData decode(String jwsString) {
        var jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwsString);

        return new TokenData(jws.getBody().get("email", String.class));
    }
}
