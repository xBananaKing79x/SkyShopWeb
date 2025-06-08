package org.skypro.skyshop.model.product;

public class FixPriceProduct extends Product {
    private static final double fixedPrice = 100;

    public FixPriceProduct(String name) {
        super(name);
    }

    @Override
    public double getProductPrice() {
        return fixedPrice;
    }

    //Переопределение метода isSpecial
    public boolean isSpecial() {

        return true; // Товар с фиксированной ценой является специальным
    }
    //Переопределение методов интерфейса Searchable
    @Override
    public String getSearchableName() {
        return productName;
    }

}
