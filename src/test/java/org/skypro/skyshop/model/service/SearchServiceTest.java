package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.searchengine.SearchResult;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {
    private final StorageService storageService;
    StorageService mockStorageService = Mockito.mock(StorageService.class);
    public SearchServiceTest(StorageService mockStorageService) {
        this.storageService = mockStorageService;
    }
    @Test
    void search_NoObjectsInStorage_ReturnsEmptyList() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);
        when(mockStorageService.getAllSearchables()).thenReturn(Collections.emptyList());

        SearchService searchService = new SearchService(mockStorageService);

        // Act
        Collection<SearchResult> result = searchService.search("test");

        // Assert
        assertTrue(result.isEmpty(), "Результат поиска должен быть пустым");
        verify(mockStorageService, times(1)).getAllSearchables();
    }
    @Test
    void search_MatchingObjectExists_ReturnsMatchingObjects() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);

        Article article1 = new Article(UUID.randomUUID(), "Title1", "Text about Java");
        Article article2 = new Article(UUID.randomUUID(), "Title2", "Text about JavaScript");

        when(mockStorageService.getAllSearchables()).thenReturn(Arrays.asList(article1, article2));

        SearchService searchService = new SearchService(mockStorageService);

        // Act
        Collection<SearchResult> result = searchService.search("JavaScript");

        // Assert
        assertEquals(1, result.size(), "Должен быть найден один объект");
        assertEquals(article2, result, "Найденный объект должен совпадать с article2");
    }
    @Test
    void getUserBasket_ReturnsValidBasket_WhenProductBasketHasItems() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);
        ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);

        UUID productId = UUID.randomUUID();
        Product product = new SimpleProduct(productId, "Яблоки", 50.0);
        Map<UUID, Integer> basketContents = Map.of(productId, 2);

        when(mockProductBasket.getProducts()).thenReturn(basketContents);
        when(mockStorageService.getProductById(productId)).thenReturn(Optional.of(product));

        BasketService basketService = new BasketService(mockProductBasket, mockStorageService);

        // Act
        UserBasket result = basketService.getUserBasket();

        // Assert
        assertNotNull(result, "Результат не должен быть null");
        assertEquals(1, result.getItems().size(), "Корзина должна содержать один элемент");
        assertEquals(100.0, result.getTotal(), "Общая стоимость должна быть 100.0");

        verify(mockProductBasket, times(1)).getProducts();
        verify(mockStorageService, times(1)).getProductById(productId);
    }
}
