package ru.nsu.nsustudyhelper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.nsu.nsustudyhelper.exception.InvalidArgumentException;

@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ExceptionWrapper> handleResourceNotFoundException(InvalidArgumentException ex) {
        return new ResponseEntity<>(new ExceptionWrapper(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @RequiredArgsConstructor
    @Getter
    private final static class ExceptionWrapper {
        private final String errorMessage;
    }
}
