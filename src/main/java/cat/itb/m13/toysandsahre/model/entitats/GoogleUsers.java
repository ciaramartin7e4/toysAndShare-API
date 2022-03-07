package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "google_users")
public class GoogleUsers {

    @Id
    @GeneratedValue
    int id;
    String google_id;
    String email;

}
