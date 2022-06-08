package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

class TokenCreator {

    Instant now = null;
    String jwtToken;

    {
        assert false;
        jwtToken = Jwts.builder()
                .claim("name", "Jane Doe")
                .claim("email", "jane@example.com")
                .setSubject("jane")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(20l, ChronoUnit.MINUTES)))
                .compact();
    }

}
