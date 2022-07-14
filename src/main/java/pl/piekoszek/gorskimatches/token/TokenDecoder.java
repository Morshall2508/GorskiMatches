package pl.piekoszek.gorskimatches.token;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletResponse;



public class TokenDecoder {

    private TokenData tokenData;

    TokenDecoder(TokenData tokenData){
        this.tokenData=tokenData;
    }
    Jws<Claims> jws;

    TokenData tokenDecoder(String jwsString) {
        try {

            jws = Jwts.parserBuilder()
                    .setSigningKey("oih5jireonjdgfssdgfs3atoigef1312321312nw4agf4w")
                    .build()
                    .parseClaimsJws(jwsString);

        } catch (JwtException ex) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return new TokenData();
    }
}


