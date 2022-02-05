package com.ssafy.project.EmotionPlanet.Config.JWT;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    public String create() {
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT");
        builder.setIssuer("EmotionPlanet");
        builder.setSubject("EP");
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)); //5분 기준
        builder.setIssuedAt(new Date());

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretKey = Encoders.BASE64URL.encode(key.getEncoded());
        System.out.println(secretKey);
        SecretKey finalKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        builder.signWith(finalKey);

        String jws = builder.compact();
        return jws;
    }
}
