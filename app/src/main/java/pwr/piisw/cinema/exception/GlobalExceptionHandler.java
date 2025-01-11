package pwr.piisw.cinema.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Getter
public class GlobalExceptionHandler {

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<ErrorResponse> handleCinemaException(CinemaException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getDescription());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse("MALFORMED_JSON_REQUEST", "Invalid JSON format");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    record ErrorResponse(String code, String message) {
    }
}