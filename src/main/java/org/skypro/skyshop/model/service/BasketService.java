package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    // Конструктор с внедрением зависимостей
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Метод добавления товара в корзину
    public void addProductToBasket(UUID productId) {
        Product product = storageService.getProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с ID " + productId + " не найден."));
        productBasket.addProduct(productId);
    }

    // Метод отображения корзины пользователя
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketContents = productBasket.getProducts();

        // Преобразуем мапу в список BasketItem
        var basketItems = basketContents.entrySet().stream()
                .map(entry -> {
                    UUID productId = entry.getKey();
                    int quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new IllegalStateException("Продукт с ID " + productId + " отсутствует в хранилище."));
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());

        // Возвращаем UserBasket
        return new UserBasket(basketItems);
    }
}
