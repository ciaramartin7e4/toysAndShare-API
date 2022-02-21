package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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


}
