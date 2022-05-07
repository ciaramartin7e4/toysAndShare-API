package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Integer> {
    List<Products> findByUsuarisId(int id);

    @Transactional
    void deleteByUsuarisId(int id);

    @Query(value = "SELECT p, p.usuaris.id, u FROM Products p, Usuaris u where p.id = :id and p.usuaris.id = u.id")
    Products findProductAndUserById(int id);
}
