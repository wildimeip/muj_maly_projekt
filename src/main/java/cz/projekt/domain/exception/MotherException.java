package cz.projekt.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class MotherException extends RuntimeException{
    private HttpStatus httpStatus;

    private ExceptionLevel level;

    public enum ExceptionLevel {
        WARNING, ERROR
    }

    public MotherException(String msg) {
        super(msg);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.level = ExceptionLevel.ERROR;
    }

    public MotherException(String msg, Throwable throwable) {
        super(msg, throwable);
        this.level = ExceptionLevel.ERROR;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public MotherException(String message, Object... params) {
        this(String.format(message.replaceAll("\\{\\}", "%s"), params));
    }
}



