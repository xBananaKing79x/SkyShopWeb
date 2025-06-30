package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private double productPrice;
    private final UUID id;

    public SimpleProduct(UUID id, String productName, double productPrice) {
        super(id, productName);
        this.id = id;
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else throw new IllegalArgumentException("Цена товара должна быть положительным числом.");
    }

    @Override
    public double getProductPrice() {
        return productPrice;
    }

    //Переопределяем метод
    @Override
    public boolean isSpecial() {
        return false;//Обычный продукт не является специальным
    }

    //Переопределение методов интерфейса Searchable
    @Override
    public String getSearchableName() {
        return productName;
    }
    }

