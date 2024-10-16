package tads.ufrn.excriteriabuilder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDTO {

    private String name;
    private Double maxPrice;
    private Double minPrice;
    private String category;
}
