//package varzeando.BackEnd.data;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import varzeando.BackEnd.models.Usuario;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Optional;
//@RequiredArgsConstructor
//public class UsuariosDetails implements UserDetails {
//    private final Usuario usuario;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public String getPassword() {
//        return usuario.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return usuario.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}