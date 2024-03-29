package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.ProductoDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final PasswordEncoder xifrat;

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
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(dateTime);
        Usuaris newUser = new Usuaris(user.getId(), user.getName(), user.getLastname(), user.getEmail(), xifrat.encode(user.getPassword()), user.getAddress(), user.getCity(), user.getCountry(), user.getPhone(), user.getPostalCode(),date, user.getLastLogin(), user.getStatus(), user.getDescription(), user.getProfileImage());
        Usuaris u = serveisUser.set(newUser);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
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
    public ResponseEntity<List<ProductoDTO>> getLists() {
        List<Products> aux = productRepository.findAll();
        List<ProductoDTO> aux2 = new ArrayList<>();
        for (Products p : aux){
            aux2.add(new ProductoDTO(p.getId(), p.getProductName(), p.getPrice(), p.getProductLocation(), p.getProductDescription(), p.getDateCreated(), p.getImageLink(), p.getUsuaris().getId()));
        }
        return ResponseEntity.ok(aux2);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getListsById(@PathVariable int id){
        Products products = serveisProduct.getById(id);
        System.out.println("El producto: "+products);
        System.out.println("El producto con el ususario: "+products.getUsuaris());
        ProductoDTO p = new ProductoDTO(products.getId(), products.getProductName(), products.getPrice(), products.getProductLocation(), products.getProductDescription(), products.getDateCreated(), products.getImageLink(), products.getUsuaris().getId());
        System.out.println(p);
        //        ResponseEntity<?> newResponse = p.getUsuaris();
        System.out.println(ResponseEntity.ok(p));
        return ResponseEntity.ok(p);
    }

    @GetMapping("/UserAndProduct/{id}")
    public ResponseEntity<Products> getProductsAndUserById(@PathVariable int id){
        Products products = productRepository.findProductAndUserById(id);
        System.out.println(products);
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

    @PutMapping("/users/{id}/products")
    public ResponseEntity<Products> updateProduct(@PathVariable(value = "id") int id, @RequestBody Products product) {
        Products p = userRepository.findById(id).map(usuari -> {
            product.setUsuaris(usuari);
            LocalDateTime dateTime = LocalDateTime.now();
            Date date = java.sql.Timestamp.valueOf(dateTime);
            Products productToUpload = new Products(product.getId(), product.getProductName(), product.getPrice(), product.getProductLocation(),
                    product.getProductDescription(), date, product.getImageLink(), product.getUsuaris());

            return productRepository.save(productToUpload);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Integer> deleteList(@PathVariable int id) {
        serveisProduct.delete(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}/products")
    public ResponseEntity<List<Products>> deleteAllProductssOfTutorial(@PathVariable(value = "id") int id) {
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
