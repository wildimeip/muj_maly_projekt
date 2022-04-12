package cz.projekt.domain.message;

import cz.projekt.domain.enums.FlashMessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class FlashMessage {

    private FlashMessageTypeEnum type;
    private String message;

    public FlashMessage(String message, FlashMessageTypeEnum type) {
        this.type = type;
        this.message = message;
    }
}