package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.Products;
import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
//import cat.itb.m13.toysandsahre.model.repositoris.ServeisGoogle;
import cat.itb.m13.toysandsahre.model.repositoris.ProductRepository;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import cat.itb.m13.toysandsahre.model.serveis.ServeisProduct;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorToysAndShare {
    private final ServeisUser serveisUser;
    private final ServeisProduct serveisProduct;
//    private final ServeisGoogle serveisGoogle;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

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
    public ResponseEntity<Usuaris> postUser(@RequestBody Usuaris user) throws ParseException {
        String sDate1 = String.valueOf(user.getDateCreated());
        Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(sDate1);
        Usuaris newUser = new Usuaris(user.getId(), user.getName(), user.getLastname(), user.getEmail(), user.getPassword(), user.getAddress(), user.getCity(), user.getCountry(), user.getPhone(), user.getPostalCode(),date1, user.getLastLogin(), user.getStatus(), user.getDescription(), user.getProfileImage());
        Usuaris u = serveisUser.set(newUser);
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

    @PostMapping("/users/{id}/products")
    public ResponseEntity<Products> createProducts(@PathVariable(value = "id") int id, @RequestBody Products commentRequest) {
        Products comment = userRepository.findById(id).map(usuari -> {
            commentRequest.setUsuaris(usuari);
            LocalDateTime dateTime = LocalDateTime.now();
            Date date = java.sql.Timestamp.valueOf(dateTime);
            Products products1 = new Products(commentRequest.getId(), commentRequest.getProductName(), commentRequest.getPrice(), commentRequest.getProductLocation(),
                    commentRequest.getProductDescription(), date, commentRequest.getImageLink(), commentRequest.getUsuaris());

            return productRepository.save(products1);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
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

    @DeleteMapping("/users/{id}/products")
    public ResponseEntity<List<Products>> deleteAllCommentsOfTutorial(@PathVariable(value = "id") int id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found Usuari with id = " + id);
        }
        productRepository.deleteByUsuarisId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{id}/products")
    public ResponseEntity<List<Products>> getAllProductsByUsuariId(@PathVariable(value = "id") int id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found Usuari with id = " + id);
        }
        List<Products> products = productRepository.findByUsuarisId(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
