package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartItemDTO;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartItem;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ProductMapper.class}
)
public interface ShoppingCartItemMapper {

    ShoppingCartItem toDB(ShoppingCartItemDTO shoppingCartItemDTO);

    ShoppingCartItemDTO toDTO(ShoppingCartItem shoppingCartItem);

    List<ShoppingCartItem> toDB(List<ShoppingCartItemDTO> shoppingCartItemDTOs);

    List<ShoppingCartItemDTO> toDTO(List<ShoppingCartItem> shoppingCartItems);
}
