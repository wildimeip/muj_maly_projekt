package cz.projekt.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemNew {
    private Long listId;
    private Long productId;
    private String name;
    private Integer amount = 1;
}
