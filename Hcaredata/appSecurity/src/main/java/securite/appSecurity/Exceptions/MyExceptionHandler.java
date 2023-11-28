package securite.appSecurity.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> InstitutionException(MyException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
