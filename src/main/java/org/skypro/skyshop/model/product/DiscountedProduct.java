package org.skypro.skyshop.model.product;

public class DiscountedProduct extends Product {
    private double basePrice;//Базовая цена товара
    private int discountPercentage;//Величина скидки

    public DiscountedProduct(String productName, double basePrice, int discountPercentage) {
        super(productName);
        if (basePrice > 0) {
            this.basePrice = basePrice;
        } else throw new IllegalArgumentException("Цена товара должна быть положительным числом.");
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            this.discountPercentage = discountPercentage;
        } else throw new IllegalArgumentException("Базовая скидка должна быть в диапазоне от 0 до 100.");
    }
//Переопределение родительского метода получения цены
@Override
public double getProductPrice() {
    return basePrice * (1 - discountPercentage / 100.0);//Учет скидки
}

@Override
public boolean isSpecial() {
    return true;//Товар со скидкой является специальным
}

// Переопределение метода toString
@Override
public String toString() {
    return getProductName() + ": " + getProductPrice() + " (" + discountPercentage + "%)";
}

//Переопределение методов интерфейса Searchable
@Override
public String getSearchableName() {
    return productName;
}
}
