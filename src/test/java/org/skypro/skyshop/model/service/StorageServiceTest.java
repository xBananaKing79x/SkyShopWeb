package org.skypro.skyshop.model.service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.searchengine.SearchResult;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StorageServiceTest {
    private final Map<UUID, Product> mockMap;
    public StorageServiceTest(Map<UUID, Product> mockMap) {
        this.mockMap = new HashMap<>();
    }

    @Test
    void getProductById_ProductExists_ReturnsProduct() {
        // Arrange
        Product mockProduct = new SimpleProduct(UUID.randomUUID(), "Яблоки", 50.0);
        Map<UUID, Product> mockMap = (Map<UUID, Product>) Mockito.mock(Product.class);

        when(mockMap.get(mockProduct.getId())).thenReturn(mockProduct);

        StorageServiceTest storageServiceTest = new StorageServiceTest(mockMap);

        // Act
        Optional<Product> result = storageServiceTest.getProductByIdTest(mockProduct.getId());

        // Assert
        assertTrue(result.isPresent(), "Продукт должен быть найден");
        assertEquals(mockProduct, result.get(), "Найденный продукт должен совпадать с mockProduct");
    }

    private Optional<Product> getProductByIdTest(UUID id) {
        return null;
    }

    @Test
    void getProductById_ProductDoesNotExist_ReturnsEmptyOptional() {
        // Arrange
        Map<UUID, Product> mockMap = (Map<UUID, Product>) Mockito.mock(Product.class);

        when(mockMap.get(any(UUID.class))).thenReturn(null);

        StorageServiceTest storageServiceTest = new StorageServiceTest(mockMap);

        // Act
        Optional<Product> result = storageServiceTest.getProductByIdTest(UUID.randomUUID());

        // Assert
        assertFalse(result.isPresent(), "Продукт не должен быть найден");
    }
}
