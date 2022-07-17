package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class TokenDecoder {

    TokenData decode(String jwsString) {
        var jws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor("oih5jireonjdgfssdgfs3atoigef1312321312nw4agf4w".getBytes()))
                .build()
                .parseClaimsJws(jwsString);

        return new TokenData(jws.getBody().get("email", String.class));
    }
}


