package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.GoogleUsers;
import cat.itb.m13.toysandsahre.model.entitats.Products;
import cat.itb.m13.toysandsahre.model.entitats.Status;
import cat.itb.m13.toysandsahre.model.entitats.Users;
import cat.itb.m13.toysandsahre.model.repositoris.ServeisGoogle;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import cat.itb.m13.toysandsahre.model.serveis.ServeisProduct;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorToysAndShare {
    private final ServeisUser serveisUser;
    private final ServeisProduct serveisProduct;
    private final ServeisGoogle serveisGoogle;
    UserRepository userRepository;


    //Google USER

//    @GetMapping ("/gusers")
//    public List<GoogleUsers> getGoogleUsers(){
//        return serveisGoogle.get();
//    }

    // USER
    @GetMapping("/users")
    public ResponseEntity<List<Users>> consultarUsuari() {
        List<Users> users = serveisUser.getUsers();
        if(users == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users);
    }

//    @PostMapping("/users/register")
//    public Status registerUser(@Valid @RequestBody Users newUser) {
//        List<Users> users = userRepository.findAll();
//        System.out.println("New user: " + newUser.toString());
//        for (Users user : users) {
//            System.out.println("Registered user: " + newUser.toString());
//            if (user.equals(newUser)) {
//                System.out.println("User Already exists!");
//                return Status.USER_ALREADY_EXISTS;
//            }
//        }
//        userRepository.save(newUser);
//        return Status.SUCCESS;
//    }
//
//    @PostMapping("/users/login")
//    public Status loginUser(@Valid @RequestBody Users user) {
//        List<Users> users = userRepository.findAll();
//        for (Users other : users) {
//            if (other.equals(user)) {
//                user.setStatus(1);
//                userRepository.save(user);
//                return Status.SUCCESS;
//            }
//        }
//        return Status.FAILURE;
//    }
//    @PostMapping("/users/logout")
//    public Status logUserOut(@Valid @RequestBody Users user) {
//        List<Users> users = userRepository.findAll();
//        for (Users other : users) {
//            if (other.equals(user)) {
//                user.setStatus(0);
//                userRepository.save(user);
//                return Status.SUCCESS;
//            }
//        }
//        return Status.FAILURE;
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> consultarUsuari(@PathVariable Integer id) {
        Users user = serveisUser.getById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/login/{email}")
    public ResponseEntity<Users> consultarUsuariByEmailPassword(@PathVariable String email, String password) {
        Users user = serveisUser.getByEmailPassword(email, password);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Users> postUser(@RequestBody Users user) {
        Users u = serveisUser.set(user);
        return new ResponseEntity<Users>(u, HttpStatus.CREATED);
    }
    @PutMapping("/users")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        Users u = serveisUser.set(user);
        return ResponseEntity.ok(u);
    }
    @DeleteMapping("/users/{id}")
    public Users deleteUser(@PathVariable int id) {
        return serveisUser.delete(id);
    }

    //PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getLists() {
        return ResponseEntity.ok(serveisProduct.getProducts());
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getListsById(@PathVariable int id){
        Products products = serveisProduct.getById(id);
        if(products == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }
    @PostMapping("/products")
    public ResponseEntity<Products> postLista(@RequestBody Products products){
        Products p = serveisProduct.set(products);
        return new ResponseEntity<Products>(p, HttpStatus.CREATED);
    }
    @PutMapping("/products")
    public ResponseEntity<Products> updateLista(@RequestBody Products lista) {
        Products p = serveisProduct.set(lista);
        return ResponseEntity.ok(p);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Integer> deleteList(@PathVariable int id) {
        serveisProduct.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/user/{id}")
    public ResponseEntity<List<Products>> getListByUserId(@PathVariable int id) {
        List<Products> lista = serveisProduct.getListByUserId(id);
        return ResponseEntity.ok(lista);
    }

}
