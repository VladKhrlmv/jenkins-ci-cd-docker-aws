package pwr.piisw.cinema.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CinemaException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String description;

    public CinemaException(CinemaExceptionType exceptionType) {
        super(exceptionType.getDescription());
        this.status = exceptionType.getStatus();
        this.code = exceptionType.getCode();
        this.description = exceptionType.getDescription();
    }
}