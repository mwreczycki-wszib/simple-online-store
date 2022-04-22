package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartStatusDTO;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartStatus;

@Mapper(
        componentModel = "spring"
)
public interface ShoppingCartStatusMapper {

    ShoppingCartStatusDTO toDTO(ShoppingCartStatus shoppingCartStatus);

    ShoppingCartStatus toDB(ShoppingCartStatusDTO shoppingCartStatusDTO);

}
