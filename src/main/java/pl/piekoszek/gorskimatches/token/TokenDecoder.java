package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.Jwts;

public class TokenDecoder {

    static TokenData tokenDecoder(String jwsString) {
        var jws = Jwts.parserBuilder()
                .setSigningKey("oih5jireonjdgfssdgfs3atoigef1312321312nw4agf4w")
                .build()
                .parseClaimsJws(jwsString);

        return new TokenData(jws.getBody().get("email", String.class));
    }
}


