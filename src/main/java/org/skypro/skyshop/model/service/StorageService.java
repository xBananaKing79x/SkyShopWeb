package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products; // Хранилище продуктов
    private final Map<UUID, Article> articles; // Хранилище статей
    private final Map<UUID, Product> availableProducts;
    // Конструктор
    public StorageService(Map<UUID, Product> availableProducts) {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        this.availableProducts = availableProducts;
        populateTestData(); // Заполняем тестовыми данными
    }

    // Метод для получения всех продуктов
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    // Метод для получения всех статей
    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    // Приватный метод для заполнения тестовыми данными
    private void populateTestData() {
        // Добавляем продукты
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Яблоки", 50.0));
        products.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Молоко", 100.0, 20));
        products.put(UUID.randomUUID(), new FixPriceProduct(UUID.randomUUID(), "Хлеб"));

        // Добавляем статьи
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Как выбрать яблоки", "Статья о том, как правильно выбирать яблоки."));
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Польза молока", "Статья о пользе молока."));
    }

    // Метод для получения всех объектов Searchable
    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> allSearchables = new ArrayList<>();
        allSearchables.addAll(products.values());
        allSearchables.addAll(articles.values());
        return allSearchables;
    }
    // Метод получения продукта по ID
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }
}
