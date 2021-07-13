package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rookie.bigdata.entity.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * @Classname JWTUtils
 * @Description TODO
 * @Author rookie
 * @Date 2021/7/13 15:08
 * @Version 1.0
 */
public class JWTUtils {


    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "adasdsdjlsjdsjouaji9fjnskldnfsodfijhsjioufghdjsanjdskj";

    /**
     * 签发人
     */
    private static final String ISSUER = "rookie";


    private JWTUtils() {
    }

    /**
     * Description: 创建 Json Web Token
     */
    public static String create(/*String username, */SysUser user) {
        return Jwts.builder()
                // [Attention] 要先 setClaims(初始化底层 map) 再设置 subject, 如果 subject 先设置, 会被覆盖.
                .setClaims(JSON.parseObject(JSON.toJSONString(user)))
                // 主题
                .setSubject(user.getUsername())
                // TODO 过期时间交由 Redis 处理
                .setExpiration(
                        Date.from(
                                LocalDateTime.now()
                                        //有效期为生成后24小时
                                        .plus(24L, ChronoUnit.HOURS)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()
                        )
                )
                // 颁发时间
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                // 颁发人
                .setIssuer(ISSUER)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512)
                .serializeToJsonWith(map -> JSON.toJSONBytes(map))
                .compact();
    }

    /**
     * Description: 获得 subject
     */
    public static String subject(String jwt) {
        return claims(jwt).getSubject();
    }

    public static SysUser userDetails(String jwt) {
        return JSON.parseObject(JSON.toJSONString(claims(jwt)), SysUser.class);
    }

    private static Claims claims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .deserializeJsonWith(bytes -> JSONObject.parseObject(new String(bytes), new TypeReference<Map<String, Object>>() {
                }))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
