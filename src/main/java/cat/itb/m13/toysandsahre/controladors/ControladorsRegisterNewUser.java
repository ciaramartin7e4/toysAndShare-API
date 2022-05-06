package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.UsuariConsultaDTO;
import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ControladorsRegisterNewUser {
    private final ServeisUser serveisUser;


    @GetMapping("/userDetail")
    public UsuariConsultaDTO consultaQuiEts(@AuthenticationPrincipal Usuaris usu) {
//        System.out.println("Este es mi usuario "+ usu.toString());
        return new UsuariConsultaDTO(usu.getName(), usu.getLastname(), usu.getEmail(), usu.getAddress(), usu.getCity(), usu.getCountry(), usu.getPhone(), usu.getPostalCode(), usu.getDateCreated(), usu.getDescription(), usu.getId(), usu.getProfileImage(), usu.getPassword(), usu.getLastLogin());
    }

    @PostMapping("/usuaris")
    public ResponseEntity<?> nouUsuari(@RequestBody Usuaris nouUsuari) {
        try {
            Usuaris res = serveisUser.crearNouUsuari(nouUsuari);
            UsuariConsultaDTO usu = new UsuariConsultaDTO(res.getName(), res.getLastname(), res.getEmail(), res.getAddress(), res.getCity(), res.getCountry(), res.getPhone(), res.getPostalCode(), res.getDateCreated(), res.getDescription(), res.getId(), res.getProfileImage(), res.getPassword(), res.getLastLogin());
            return new ResponseEntity<UsuariConsultaDTO>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            //per si intentem afegir 2 usuaris amb el mateix username, saltarà excepció
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
