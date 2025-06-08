package org.skypro.skyshop.model.product;

import org.skypro.skyshop.SearchEngine.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {
    String productName;

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название не может быть пустой строкой или состоять только из пробелов.");
        } else this.productName = productName;
    }
    @Override
    public String getProductName() {
        return productName;
    }
    public abstract double getProductPrice();
    public abstract boolean isSpecial();
    public String toString() {
        return productName + ": " + getProductPrice();
    }
    //Переопределяем методы интерфейса Searchable
    @Override
    public String getType () {
        return "PRODUCT";
    }
    @Override
    public String getSearchTerm() {
        return productName; //Термин поиска - имя товара
    }
    @Override
    public String getSearchableName() {
        return productName;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Product) {
        Product other = (Product) obj;
            return Objects.equals(productName, ((Product) obj).productName);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}

