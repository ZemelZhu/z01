package com.zemel.framework.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zemel.data.type.Roles;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: zemel
 * @Date: 2020/4/1 22:12
 */
public class TokenUtil {
    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 50 * 60 * 1000;

    private static String rand = String.valueOf(Math.random());

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135" + rand;

    public static String sign(int userId) {
        return sign(String.valueOf(userId));
    }

    public static String sign(int userId, Roles roles) {
        String sign = sign(String.valueOf(userId), roles.getSecret());
        return sign;
    }

    public static String sign(String userId, String secret) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header)
                .withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 生成签名,15分钟后过期
     *
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        return sign(userId, Roles.USER.getSecret());
    }

    public static int getUserId(HttpServletRequest request) {
        return getUserId(request, Roles.USER);
    }

    public static int getUserId(HttpServletRequest request, Roles roles) {
        String token = RequestUtil.getToken(request);
        return getUserId(token, roles.getSecret());
    }

    public static boolean verity(String token, String secret) {
        if (StringUtil.isNullOrEmpty(token))
            return false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Date expiresAt = jwt.getExpiresAt();
            expiresAt.setTime(System.currentTimeMillis() + EXPIRE_TIME);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static void main(String[] args) {
        for (Roles roles : Roles.values()) {
            String sign = sign(123456, roles);
            System.out.println(sign);
        }

    }

    public static int getUserId(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim userId = jwt.getClaim("userId");
            return Integer.valueOf(userId.asString());
        } catch (Exception e) {
            return 0;
        }
    }
}
