//package varzeando.BackEnd.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class JwtUtil {
//    public String extractNome(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
//        final  Claims claims =extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
//    }
//    private Boolean expirado(String token){
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public String gerarToken(UserDetails userDetails){
//        Map<String,Object> claims=new HashMap<>();
//        return criarToken(claims,userDetails.getUsername());
//    }
//
//    private String criarToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+10006060*10)).signWith(SignatureAlgorithm.HS512,"secret").compact();
//
//    }
//    public Boolean validarToken(String token, UserDetails userDetails){
//        final String username=extractNome(token);
//        return (username.equals(userDetails.getUsername())&&!expirado(token));
//    }
//}