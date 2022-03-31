package cat.itb.m13.toysandsahre.model.serveis;

import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeisUser {
    private final UserRepository userRepository;

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
}
