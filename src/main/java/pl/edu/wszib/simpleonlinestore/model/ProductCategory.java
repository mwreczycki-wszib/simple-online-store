package pl.edu.wszib.simpleonlinestore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private TaxRate taxRate;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;

}
