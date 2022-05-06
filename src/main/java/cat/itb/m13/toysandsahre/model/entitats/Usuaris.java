package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "usuaris")
public class Usuaris implements UserDetails {

    @Id
    @GeneratedValue
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "lastname")
    String lastname;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "address")
    String address;
    @Column(name = "city")
    String city;
    @Column(name = "country")
    String country;
    @Column(name = "phone")
    String phone;
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

//    @OneToMany
//    private List<Products> products;

//    public Usuaris(int id, String name, String lastname, String email,
//                   String password, String address, String city,
//                   String country, String phone, Long postalCode,
//                   Date dateCreated, int lastLogin, int status,
//                   String description, String profileImage,
//                   List<Products> products) {
//        this.id = id;
//        this.name = name;
//        this.lastname = lastname;
//        this.email = email;
//        this.password = password;
//        this.address = address;
//        this.city = city;
//        this.country = country;
//        this.phone = phone;
//        this.postalCode = postalCode;
//        this.dateCreated = dateCreated;
//        this.lastLogin = lastLogin;
//        this.status = status;
//        this.description = description;
//        this.profileImage = profileImage;
//        this.products = products;
//    }


    public Usuaris(int id, String name, String lastname, String email, String password, String address, String city, String country, String phone, Long postalCode, Date dateCreated, int lastLogin, int status, String description, String profileImage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.postalCode = postalCode;
        this.dateCreated = dateCreated;
        this.lastLogin = lastLogin;
        this.status = status;
        this.description = description;
        this.profileImage = profileImage;
    }

    public Usuaris() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        //l'altre rol seria "ROLE_ADMIN", per exemple
        return roles;

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "google_id", referencedColumnName = "google_id")
//    private GoogleUsers googleUsers;

//    @Override
//    public String toString() {
//        return "Usuaris{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                ", city='" + city + '\'' +
//                ", country='" + country + '\'' +
//                ", phone='" + phone + '\'' +
//                ", postalCode=" + postalCode +
//                ", dateCreated=" + dateCreated +
//                ", lastLogin=" + lastLogin +
//                ", status=" + status +
//                ", description='" + description + '\'' +
//                ", profileImage='" + profileImage + '\'' +
//                ", products=" + products +
//                '}';
//    }
}
