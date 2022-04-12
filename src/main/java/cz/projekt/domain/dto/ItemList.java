package cz.projekt.domain.dto;


import cz.projekt.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemList {
    private Long id;
    private String name;
    private Integer amount;
    private String createdBy;
    private LocalDateTime created;
    private StatusEnum status;
    private String valueType;
}
