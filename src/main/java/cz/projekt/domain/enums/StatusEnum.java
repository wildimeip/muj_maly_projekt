package cz.projekt.domain.enums;

import lombok.Getter;

public enum StatusEnum {
    PENDING("PENDING"),
    BOUGHT("BOUGHT"),
    NOT_IN_STOCK("NOT_IN_STOCK"),
    DELETE("DELETE");

    @Getter
    private final String type;

    StatusEnum(String type) {
        this.type = type;
    }

    public static StatusEnum getByString(String string) {
        if (string == null) {
            return null;
        }
        for (StatusEnum status : values()) {
            if (status.type.equals(string)) {
                return status;
            }
        }

        return null;
    }
}
