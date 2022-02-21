package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
