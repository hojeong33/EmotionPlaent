package com.ssafy.project.EmotionPlanet.Config.JWT;

import com.ssafy.project.EmotionPlanet.Dto.TokenDto;
import com.ssafy.project.EmotionPlanet.Dto.UserDto;
import com.ssafy.project.EmotionPlanet.Dto.UserSecretDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {


    private final String encodeKey = "6ZIjhannFz8FQQhGkPM80cx8aKhZJB1zF-L0BYFumUNGPoNorcTfqNVisFU2oxH_ZrpjoozNFb7b-3_qDMCeiQ";
    private final Integer accessExpMin = 30;
    private final Integer refreshExpMin = 10080;

    public String create() {
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ", "JWT");
        builder.setIssuer("EmotionPlanet");
        builder.setSubject("EP");
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)); //5분 기준
        builder.setIssuedAt(new Date());

//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        String secretKey = Encoders.BASE64URL.encode(key.getEncoded());
//        System.out.println(secretKey);
//        encodeKey = secretKey;
        SecretKey finalKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(encodeKey));
        builder.signWith(finalKey);

        String jws = builder.compact();
        return jws;
    }

    public TokenDto create(UserSecretDto userInfo) {

        // Create Access Token
        String accessJws = createJws(accessExpMin, userInfo);

        // Create Refresh Token
        String refreshJws = createJws(refreshExpMin, null);

        TokenDto tokens = new TokenDto();
        tokens.setAccessJws(accessJws);
        tokens.setRefreshJws(refreshJws);

        return tokens;

    }

    public String createJws(Integer expMin, UserSecretDto userInfo) {

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(encodeKey));

        //JWT Builder create
        JwtBuilder builder = Jwts.builder();

        // header configuration
        builder.setHeaderParam("typ", "JWT");

        // claim configuration
        builder.setIssuer("EmotionPlanet");
        builder.setSubject("EP");
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expMin));
        builder.setIssuedAt(new Date());
        if(userInfo != null) {
            builder.claim("userInfo", userInfo);
        }

        // signature configuration
        builder.signWith(secretKey);
        String jws = builder.compact();

        return jws;

    }

    public boolean validate(String token) {

        Jws<Claims> jws;
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        String encodeKey = Encoders.BASE64URL.encode(key.getEncoded());
//        System.out.println("encodeKey : " + encodeKey);
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(encodeKey));
        System.out.println("=================================================================");
        System.out.println("validate: " + secretKey);
        try {
            JwtParserBuilder jpb = Jwts.parserBuilder();
            jpb.setSigningKey(secretKey);
            jws = jpb.build().parseClaimsJws(token);

            System.out.println(jws);
            System.out.println(jws.getBody().getSubject());

            return true;
        }catch(JwtException e) {
            return false;
        }

    }

    public String decode(String token) {

        String[] splitToken = token.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(splitToken[1]);

        String decodedString = null;
        try {
            decodedString = new String(decodedBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;

    }
}
