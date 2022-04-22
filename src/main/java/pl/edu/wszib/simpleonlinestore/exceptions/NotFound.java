package pl.edu.wszib.simpleonlinestore.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    private Class clazz;

    private Integer id;

    public NotFound(Integer id, Class<?> clazz) {
        this.clazz = clazz;
        this.id = id;
    }
}
