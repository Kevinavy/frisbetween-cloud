package com.kevinavy.authenticationcenter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JsonWebTokenCenter {
    private static final Logger logger = LogManager.getLogger(JsonWebTokenCenter.class);

    public static final Long EXPIRE_TIME = 86400000L;
    public static final String APP_SECRET = "a2V2aW5hdnk=";

    public static String getToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("jacob-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    public static String getUsernameByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return claims.get("username").toString();
        } catch (Exception e) {
            logger.error("get role username : {0}", e);
        }
        return "";
    }
}
