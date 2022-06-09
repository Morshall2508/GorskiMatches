package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

class TokenCreator {

    private AccountInfo accountInfo;

    TokenCreator(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    String jwtToken;

    {
        assert false;
        jwtToken = Jwts.builder()
                .claim("email", accountInfo.getEmail())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(20l, ChronoUnit.MINUTES)))
                .compact();
    }
}
