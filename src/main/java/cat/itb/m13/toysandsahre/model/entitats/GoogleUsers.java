package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GoogleUsers {

    @Id
    @GeneratedValue
    int id;
    String googleId;
    String email;

}
