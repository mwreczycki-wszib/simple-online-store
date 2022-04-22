package pl.edu.wszib.simpleonlinestore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private ShoppingCartStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private List<ShoppingCartItem> items = new ArrayList<>();

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;

}
