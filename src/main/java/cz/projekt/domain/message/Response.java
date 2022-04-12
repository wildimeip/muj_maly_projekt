package cz.projekt.domain.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    boolean success;
    T result;
    List<FlashMessage> flashes;
    List<?> errors;
    Timestamp timestamp;

    public Response(boolean success, T result, List<FlashMessage> flashes) {
        this.success = success;
        this.result = result;
        this.flashes = flashes;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.errors = new ArrayList<>();
    }

    public Response(boolean success, List<FlashMessage> flashes, T result, List<?> errors) {
        this.success = success;
        this.result = result;
        this.flashes = flashes;
        this.errors = errors;
        this.timestamp = new Timestamp(System.currentTimeMillis());

    }
}