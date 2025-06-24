package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.error.ShopError;
import org.skypro.skyshop.model.exeptions.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Глобальный обработчик исключений для всех контроллеров
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        // Создаем объект ошибки
        ShopError error = new ShopError("HTTP 404", ex.getMessage());

        // Возвращаем ResponseEntity с HTTP-статусом 404
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
