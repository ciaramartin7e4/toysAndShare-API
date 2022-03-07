package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT u FROM Products u WHERE u.id = :id")
    List<Products> findProductsByUserId(int id);
}
