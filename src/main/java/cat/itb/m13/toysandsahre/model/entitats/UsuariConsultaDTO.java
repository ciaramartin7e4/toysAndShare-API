package cat.itb.m13.toysandsahre.model.entitats;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UsuariConsultaDTO {

    private String name;
    private String lastName;
    private String email;
    private String adrress;
    private String city;
    private String country;
    private String phone;
    private Long postalCode;
    private Date dateCreated;

}
