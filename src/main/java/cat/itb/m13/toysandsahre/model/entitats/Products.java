package cat.itb.m13.toysandsahre.model.entitats;

import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
//import org.graalvm.compiler.lir.LIRInstruction;

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
//    @Column(name="donator_id")
//    int donator_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuari_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuaris usuaris;

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
    //    public Products(int id, String productName, Double price, String productLocation, String productDescription, Date dateCreated, String imageLink, int donator_id) {
//        super();
//        this.id = id;
//        this.productName = productName;
//        this.price = price;
//        this.productLocation = productLocation;
//        this.productDescription = productDescription;
//        this.dateCreated = dateCreated;
//        this.imageLink = imageLink;
//        this.donator_id = donator_id;
//    }

    public Products() {
        super();
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

//    public int getDonator_id() {
//        return donator_id;
//    }

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

//    public void setDonator_id(int donator_id) {
//        this.donator_id = donator_id;
//    }

    public void setUsuaris(Usuaris usuaris) {
        this.usuaris = usuaris;
    }
}
