package org.skypro.skyshop.model.error;

public class ShopError {
    private  String code;
    private  String message;

    // Конструктор
    public ShopError(String code, String message) {
        this.code=code;
        this.message = message;
    }

    // Геттеры
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // Переопределение toString() (опционально)
    @Override
    public String toString() {
        return "ShopError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
