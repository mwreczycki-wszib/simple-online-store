package pl.edu.wszib.simpleonlinestore.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.simpleonlinestore.dto.TaxRateDTO;
import pl.edu.wszib.simpleonlinestore.model.TaxRate;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaxRateMapper {

    TaxRate toDB(TaxRateDTO taxRateDTO);

    TaxRateDTO toDTO(TaxRate taxRate);

    List<TaxRate> toDB(List<TaxRateDTO> taxRateDTOs);

    List<TaxRateDTO> toDTO(List<TaxRate> taxRates);

}
