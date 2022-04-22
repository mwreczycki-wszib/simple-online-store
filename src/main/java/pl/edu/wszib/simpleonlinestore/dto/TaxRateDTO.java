package pl.edu.wszib.simpleonlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TaxRateDTO {
    private Integer id;
    private BigDecimal rate;
}
