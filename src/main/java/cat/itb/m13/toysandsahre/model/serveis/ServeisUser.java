package cat.itb.m13.toysandsahre.model.serveis;

import cat.itb.m13.toysandsahre.model.entitats.Products;
import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class ServeisUser {
    private final UserRepository userRepository;
    private final PasswordEncoder xifrat;

    public Usuaris consultarPerUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public Usuaris crearNouUsuari(Usuaris nouUsuari) {
        //falta controlar que els 2 passwords del client coincideixen
        //passar un UsuariCreacioDTO
        nouUsuari.setPassword(xifrat.encode(nouUsuari.getPassword()));
        userRepository.save(nouUsuari);
        return nouUsuari;
    }


    //Llista tots els usuaris
    public List<Usuaris> getUsers(){
        return userRepository.findAll();
    }

    // user by id
    public Usuaris getById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public Usuaris getByEmailPassword(String email, String password){
        return userRepository.findByEmailPassword(email, password).orElse(null);
    }
    // afegeix un usuari
    public Usuaris set(Usuaris it){
        return userRepository.save(it);
    }

    public Usuaris delete(int id){
        Usuaris user = userRepository.findById(id).orElse(null);
        if (user != null){
            userRepository.deleteById(id);
        }
        return user;
    }

    public Usuaris getByProduct(Products product) {
        return userRepository.findByProductId(product);
    }
}
