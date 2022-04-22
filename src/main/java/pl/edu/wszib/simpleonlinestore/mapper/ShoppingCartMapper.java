package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.edu.wszib.simpleonlinestore.dto.ProductDTO;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.dto.SimpleShoppingCartDTO;
import pl.edu.wszib.simpleonlinestore.model.Product;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;
import pl.edu.wszib.simpleonlinestore.utils.ShoppingcartCalculator;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ShoppingCartItemMapper.class, ShoppingCartStatusMapper.class}
)
public interface ShoppingCartMapper {

    ShoppingCartDTO toDTO(ShoppingCart shoppingCart);

    ShoppingCart toDB(ShoppingCartDTO shoppingCartDTO);

    SimpleShoppingCartDTO toSimpleDTO(ShoppingCart shoppingCart);

    List<ShoppingCartDTO> toDTO(List<ShoppingCart> shoppingCarts);

    List<SimpleShoppingCartDTO> toSimpleDTO(List<ShoppingCart> shoppingCarts);

    @AfterMapping
    default void setTotal(@MappingTarget ShoppingCartDTO.ShoppingCartDTOBuilder builder, ShoppingCart shoppingCart) {
       builder.totalPrice(
               ShoppingcartCalculator.calculateTotal(shoppingCart)
       );
    }
}
