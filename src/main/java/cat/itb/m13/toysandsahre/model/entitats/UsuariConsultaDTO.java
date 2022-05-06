package cat.itb.m13.toysandsahre.model.entitats;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class UsuariConsultaDTO {

//    private Boolean error;
//
//    public Boolean getError() {
//        return true;
//    }
//
//    public String getMissage() {
//        return missage;
//    }
//
//    public Usuaris getUsuaris() {
//        return usuaris;
//    }

//    private String missage;
    private String name;
    private String lastname;
    private String email;
    private String address;
    private String city;
    private String country;
    private String phone;
    private Long postalCode;
    private Date dateCreated;
    private String description;
    private int id;
    private String profileImage;
    String password;
    int lastLogin;

//    private Usuaris usuaris;
}
