package br.com.sistema.sistema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Ocorreu um erro na aplicação: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Adicione métodos adicionais para lidar com outras exceções, se necessário
}