package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    int usuari_id;

    public ProductoDTO(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, int usuari_id) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productLocation = productLocation;
        this.productDescription = productDescription;
        this.dateCreated = dateCreated;
        this.imageLink = imageLink;
        this.usuari_id = usuari_id;
    }
}
