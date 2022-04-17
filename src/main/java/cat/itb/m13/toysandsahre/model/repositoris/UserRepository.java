package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuaris, Integer> {
    @Query(value="SELECT u FROM Usuaris u WHERE u.email = :email and u.password = :password")
    Optional<Usuaris> findByEmailPassword(@Param("email") String email, @Param("password") String password);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM Usuaris u WHERE u.name = :usename")
    Optional<Usuaris> findByUserName(String username);

}
