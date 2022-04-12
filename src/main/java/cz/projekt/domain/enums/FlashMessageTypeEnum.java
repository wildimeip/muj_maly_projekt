package cz.projekt.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FlashMessageTypeEnum {
    INFO("info"), SPECIAL("special"), ERROR("error");

    String value;

    FlashMessageTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}