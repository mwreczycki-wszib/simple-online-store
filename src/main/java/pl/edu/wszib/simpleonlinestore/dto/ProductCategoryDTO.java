package pl.edu.wszib.simpleonlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductCategoryDTO {

    private Integer id;
    private String name;
    private TaxRateDTO taxRate;

}
