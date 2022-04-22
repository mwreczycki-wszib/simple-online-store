package pl.edu.wszib.simpleonlinestore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartItemDTO {
    private Integer id;
    private ProductDTO product;
    private int amount;
}
