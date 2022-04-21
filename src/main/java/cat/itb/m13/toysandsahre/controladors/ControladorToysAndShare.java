package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.Products;
import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
//import cat.itb.m13.toysandsahre.model.repositoris.ServeisGoogle;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import cat.itb.m13.toysandsahre.model.serveis.ServeisProduct;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorToysAndShare {
    private final ServeisUser serveisUser;
    private final ServeisProduct serveisProduct;
//    private final ServeisGoogle serveisGoogle;
    UserRepository userRepository;


    //Google USER

//    @GetMapping ("/gusers")
//    public List<GoogleUsers> getGoogleUsers(){
//        return serveisGoogle.get();
//    }

    // USER
    @GetMapping("/users")
    public ResponseEntity<List<Usuaris>> consultarUsuari() {
        List<Usuaris> users = serveisUser.getUsers();
        if(users == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuaris> consultarUsuari(@PathVariable Integer id) {
        Usuaris user = serveisUser.getById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/login/{email}")
    public ResponseEntity<Usuaris> consultarUsuariByEmailPassword(@PathVariable String email, String password) {
        Usuaris user = serveisUser.getByEmailPassword(email, password);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Usuaris> postUser(@RequestBody Usuaris user) {
        Usuaris u = serveisUser.set(user);
        return new ResponseEntity<Usuaris>(u, HttpStatus.CREATED);
    }
    @PutMapping("/users")
    public ResponseEntity<Usuaris> updateUser(@RequestBody Usuaris user) {
        Usuaris u = serveisUser.set(user);
        return ResponseEntity.ok(u);
    }
    @DeleteMapping("/users/{id}")
    public Usuaris deleteUser(@PathVariable int id) {
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
    @GetMapping("/user/product/{product}")
    public ResponseEntity<Usuaris> getUserByProduct(@PathVariable Products product){
        Usuaris user = serveisUser.getByProduct(product);
        return ResponseEntity.ok(user);
    }
}
