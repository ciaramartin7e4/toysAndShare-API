package cat.itb.m13.toysandsahre.model.entitats;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    int usuariId;

    public ProductoDTO(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, int usuariId) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productLocation = productLocation;
        this.productDescription = productDescription;
        this.dateCreated = dateCreated;
        this.imageLink = imageLink;
        this.usuariId = usuariId;
    }
}
