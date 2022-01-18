package com.isladellago.usermanager.service.impl;

import com.isladellago.usermanager.model.User;
import com.isladellago.usermanager.service.TokenService;
import com.isladellago.usermanager.util.CustomJwtClaims;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("jwtsTokenService")
@Log4j2
public class JwtsTokenServiceImpl implements TokenService {

    private static final String JWT_ISSUER = "com.isladellago";
    private static final Date EXPIRATION_LIMIT_DATE =
            new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);

    private String jwtSignatureSecret;

    @Override
    public String generateToken(User user) {
        log.info("[Generate token] Generate token started, user email: {}",
                user.getEmail());

        final String token = Jwts.builder()
                .setSubject(user.getFullName())
                .claim(CustomJwtClaims.USER_ID_CLAIM, user.getId())
                .claim(CustomJwtClaims.EMAIL_CLAIM, user.getEmail())
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(EXPIRATION_LIMIT_DATE)
                .signWith(SignatureAlgorithm.HS512, jwtSignatureSecret)
                .compact();

        log.info("[Generate token] Generated token: {}, user email: {}",
                token, user.getEmail());

        return token;
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSignatureSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    @Override
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSignatureSecret)
                .parseClaimsJws(token)
                .getBody()
                .get(CustomJwtClaims.EMAIL_CLAIM, String.class);
    }

    @Value("${jwt.signature.secret}")
    public void setJwtSignatureSecret(String jwtSignatureSecret) {
        this.jwtSignatureSecret = jwtSignatureSecret;
    }
}
