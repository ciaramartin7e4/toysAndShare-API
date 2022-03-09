package cat.itb.m13.toysandsahre.model.serveis;


import cat.itb.m13.toysandsahre.model.entitats.Products;
import cat.itb.m13.toysandsahre.model.repositoris.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeisProduct {
    private final ProductRepository productRepository;

    // llistar tots els productes
    public List<Products> getProducts(){
        return productRepository.findAll();
    }

    // llista productes by id
    public Products getById(int id){
        return productRepository.findById(id).orElse(null);
    }

    // add Product
    public Products set (Products it){
        return productRepository.save(it);
    }

    //delete product by id
    public Products delete(int id){
        Products product = productRepository.findById(id).orElse(null);
        if(product != null){
            productRepository.deleteById(id);
        }
        return product;
    }

    // consultar producto por id usuari
    public List<Products> getListByUserId(int id){
        return productRepository.findProductsByUserId(id);
    }
}
