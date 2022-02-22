package cat.itb.m13.toysandsahre.model.entitats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue
    int id;
    String name;
    String email;
    String password;
    Date dateCreated;
    int lastLogin;
    int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "googleId", referencedColumnName = "googleId")
    private GoogleUsers googleUsers;
}
