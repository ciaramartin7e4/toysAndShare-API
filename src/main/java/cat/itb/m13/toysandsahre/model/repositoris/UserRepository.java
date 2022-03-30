package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.security.util.Password;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailPassword(String email, String password);
    Boolean existsByEmail(String email);
}
