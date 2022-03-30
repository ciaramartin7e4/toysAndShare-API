package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value="SELECT u FROM Users u WHERE u.email = :email and u.password = :password)")
    Optional<Users> findByEmailPassword(@Param("email") String email, @Param("password") String password);

    Boolean existsByEmail(String email);
}
