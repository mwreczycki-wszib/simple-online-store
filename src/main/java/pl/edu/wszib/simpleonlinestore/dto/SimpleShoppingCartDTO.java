package pl.edu.wszib.simpleonlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SimpleShoppingCartDTO {

    private Integer id;
    private ShoppingCartStatusDTO status;
    private BigDecimal totalPrice;

}
