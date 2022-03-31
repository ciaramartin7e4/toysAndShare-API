package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "usuaris")
public class Usuaris {

    @Id
    @GeneratedValue
    int id;
    String name;
    String lastname;
    String email;
    String password;
    String adress;
    String city;
    String country;
    @Column(name = "postalCode")
    Long postalCode;
    @Column(name = "dateCreated")
    Date dateCreated;
    @Column(name = "lastLogin")
    int lastLogin;
    int status;
    String description;
    @Column(name = "profileImage")
    String profileImage;


    @OneToMany
    private List<Products> products;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "google_id", referencedColumnName = "google_id")
//    private GoogleUsers googleUsers;
}
