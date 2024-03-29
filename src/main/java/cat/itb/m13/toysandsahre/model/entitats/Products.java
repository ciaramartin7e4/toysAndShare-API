package cat.itb.m13.toysandsahre.model.entitats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "product_name")
    String productName;
    @Column(name = "price")
    Double price;
    @Column(name = "product_location")
    String productLocation;
    @Column(name = "product_description")
    String productDescription;
    @Column(name = "date_created")
    Date dateCreated;
    @Column(name = "image_link")
    String imageLink;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuari_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuaris usuaris;

    public Products() {
        super();
    }

    public Products(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, Usuaris usuaris) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.productLocation = productLocation;
        this.productDescription = productDescription;
        this.dateCreated = dateCreated;
        this.imageLink = imageLink;
        this.usuaris = usuaris;
    }

    //    Getters
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Usuaris getUsuaris() {
        return usuaris;
    }

    //    Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setUsuaris(Usuaris usuaris) {
        this.usuaris = usuaris;
    }
}
