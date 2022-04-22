package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.simpleonlinestore.dto.ProductCategoryDTO;
import pl.edu.wszib.simpleonlinestore.model.ProductCategory;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {TaxRateMapper.class}

)
public interface ProductCategoryMapper {

    ProductCategory toDB(ProductCategoryDTO productCategoryDTO);

    ProductCategoryDTO toDTO(ProductCategory productCategory);

    List<ProductCategory> toDB(List<ProductCategoryDTO> productCategoryDTOs);

    List<ProductCategoryDTO> toDTO(List<ProductCategory> productCategory);

}
