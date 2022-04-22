package pl.edu.wszib.simpleonlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private ProductCategoryDTO category;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private int amount;
}
