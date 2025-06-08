package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final double fixedPrice = 100;

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
        this.getId();
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
