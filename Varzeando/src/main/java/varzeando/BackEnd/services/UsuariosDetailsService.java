//package varzeando.BackEnd.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
////import varzeando.BackEnd.data.UsuariosDetails;
//import varzeando.BackEnd.dto.request.RequestSegundoCadastro;
//import varzeando.BackEnd.models.Usuario;
//import varzeando.BackEnd.repository.UsuariosRepository;
//
//@RequiredArgsConstructor
//@Service
//public class UsuariosDetailsService implements UserDetailsService {
//
//    private final UsuariosRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Usuario user = usuarioRepository.findByEmail(email)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("usuario não encontrado")));
//        //return new User("diego","diegog",new ArrayList<>());
//        if(user.equals(null)){
//            throw new UsernameNotFoundException("Usuario nao encontrado");
//        }
//        return  new UsuariosDetails(user);
//    }
//    public Usuario salvardois(RequestSegundoCadastro requestSegundoCadastro){
//        Usuario user=usuarioRepository.findByEmail(requestSegundoCadastro.getEmail())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, ("valores ivalidos")));
////        user.setLatitude(requestSegundoCadastro.getLatitude());
////        user.setLongitude(requestSegundoCadastro.getLongitude());
////        user.setPosicao(requestSegundoCadastro.getPosicao());
//        usuarioRepository.deleteById(user.getId());
//        Usuario userFim= usuarioRepository.save(Usuario.builder()
//                .id(user.getId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .dataNascimento(user.getDataNascimento())
//                .latitude(requestSegundoCadastro.getLatitude())
//                .longitude(requestSegundoCadastro.getLongitude())
//                .build());
//        return userFim;
//    }
//
//
//}