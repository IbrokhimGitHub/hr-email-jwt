package uz.pdp.hremailjwt.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.hremailjwt.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
    private static final String secretKey = "secretShit";

    public String generateToken(String username, Set<Role> roles){
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
    public String getEmailFromToken(String token){
        try {


            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return null;
        }
    }
}
