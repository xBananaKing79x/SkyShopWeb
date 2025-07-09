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
    // Конструктор
    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
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
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId3 = UUID.randomUUID();
        SimpleProduct simpleProduct = new SimpleProduct(productId1, "Яблоки", 50.0);
        DiscountedProduct discountedProduct = new DiscountedProduct(productId2, "Молоко", 100.0, 20);
        FixPriceProduct fixPriceProduct = new FixPriceProduct(productId3, "Хлеб");
        products.put(productId1,simpleProduct);
        products.put(productId2, discountedProduct);
        products.put(productId3, fixPriceProduct);

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
        return Optional.ofNullable(products.get(id));
    }
}
