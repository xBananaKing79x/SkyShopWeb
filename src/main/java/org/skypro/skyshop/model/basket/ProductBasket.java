package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope// Указываем, что бин создается для каждой сессии пользователя
public class ProductBasket {

    private final Map<UUID, Integer> products; // Карта для хранения товаров и их количества

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    // Метод добавления продукта в корзину
    public void addProduct(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID товара не может быть null.");
        }
        products.put(id, products.getOrDefault(id, 0) + 1); // Увеличиваем количество на 1
    }

    // Метод получения всех продуктов в корзине
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products); // Возвращаем неизменяемую копию мапы
    }
}
