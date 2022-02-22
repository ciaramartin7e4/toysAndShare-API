package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.GoogleUsers;
import cat.itb.m13.toysandsahre.model.entitats.Users;
import cat.itb.m13.toysandsahre.model.repositoris.ServeisGoogle;
import cat.itb.m13.toysandsahre.model.serveis.ServeisProduct;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorToysAndShare {
    private final ServeisUser serveisUser;
    private final ServeisProduct serveisProduct;
    private final ServeisGoogle serveisGoogle;


    //Google USER

//    @GetMapping ("/gusers")
//    public List<GoogleUsers> getGoogleUsers(){
//        return serveisGoogle.get();
//    }

    // USER

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> consultarUsuari(@PathVariable Integer id) {
        Users user = serveisUser.getById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

}
