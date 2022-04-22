package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pl.edu.wszib.simpleonlinestore.dto.ProductDTO;
import pl.edu.wszib.simpleonlinestore.dto.ShoppingCartItemDTO;
import pl.edu.wszib.simpleonlinestore.model.Product;
import pl.edu.wszib.simpleonlinestore.model.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ProductCategoryMapper.class}
)
public interface ProductMapper {

    @Mapping(target = "price", source = "netPrice")
    Product toDB(ProductDTO productDTO);

    @Mapping(target = "netPrice", source = "price")
    ProductDTO toDTO(Product product);

    List<Product> toDB(List<ProductDTO> productDTOs);

    List<ProductDTO> toDTO(List<Product> products);

    @AfterMapping
    default void setGross(@MappingTarget ProductDTO.ProductDTOBuilder builder, Product product) {
        builder.grossPrice(product
                .getPrice()
                .multiply(
                        product
                                .getCategory()
                                .getTaxRate()
                                .getRate()
                                .add(BigDecimal.ONE)));
    }
}
