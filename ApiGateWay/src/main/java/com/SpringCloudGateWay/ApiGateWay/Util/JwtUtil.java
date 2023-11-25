package com.SpringCloudGateWay.ApiGateWay.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String extractUserEmail(String Token) {
        return extractClaim(Token, Claims::getSubject);
    }

    public boolean isValidToken(String Token, UserDetails userDetails){

        final String email=extractUserEmail(Token);

        return email.equals(userDetails.getUsername()) && !isTokenExpired(Token);
    }

    private boolean isTokenExpired(String token) {

        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }


    //extract particular claim
    private <T> T extractClaim(String token, Function<Claims,T> claimResolver ) {

        final Claims claims=extractAllClaims(token);

        return  claimResolver.apply(claims);
    }




    public Claims extractAllClaims(String Token){
        return Jwts.parserBuilder().setSigningKey(getSignKey())
                .build().parseClaimsJws(Token).getBody();
    }




    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}