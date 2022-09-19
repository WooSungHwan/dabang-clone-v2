package com.blackdog.dabang.config.jwt;

import com.blackdog.dabang.config.security.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateJwtToken(SecurityUserDetails securityUserDetails) {
        Date now = new Date();
        String jwtToken = Jwts.builder()
                .setSubject("DABANG-SERVER API")
                .setIssuer("DABANG-USER")
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(new Date(now.getTime() + jwtProperties.getExpiration()))
                .claim("seq", securityUserDetails.getSeq())
                .claim("userId", securityUserDetails.getId())
                .claim("type", securityUserDetails.getType())
                .compact();

        return jwtToken;
    }

    public String getJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring(7);
    }

    public Long getSeq(String jwtToken) {
        return getClaims(jwtToken).get("seq", Long.class);
    }

    private Claims getClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
