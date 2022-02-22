package cat.itb.m13.toysandsahre.model.repositoris;

import cat.itb.m13.toysandsahre.model.entitats.GoogleUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServeisGoogle extends JpaRepository<GoogleUsers, Integer> {

}
