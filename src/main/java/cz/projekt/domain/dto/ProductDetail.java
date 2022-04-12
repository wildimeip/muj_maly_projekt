package cz.projekt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDetail {
    private Long productId;
    private String name;
    private Long valueTypeId;
    private String valueTypeName;
}
