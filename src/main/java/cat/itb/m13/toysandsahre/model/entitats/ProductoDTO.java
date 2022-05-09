package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
=======
>>>>>>> 2880763ad5986c080f17f05d8a8dedac489a9156
import java.util.Date;
@Data
@NoArgsConstructor
public class ProductoDTO {
    int id;
    String productName;
    Double price;
    String productLocation;
    String productDescription;
    Date dateCreated;
    String imageLink;
<<<<<<< HEAD
    int usuariId;

    public ProductoDTO(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, int usuariId) {
=======
    int usuari_id;

    public ProductoDTO(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, int usuari_id) {
>>>>>>> 2880763ad5986c080f17f05d8a8dedac489a9156
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productLocation = productLocation;
        this.productDescription = productDescription;
        this.dateCreated = dateCreated;
        this.imageLink = imageLink;
<<<<<<< HEAD
        this.usuariId = usuariId;
=======
        this.usuari_id = usuari_id;
>>>>>>> 2880763ad5986c080f17f05d8a8dedac489a9156
    }
}
