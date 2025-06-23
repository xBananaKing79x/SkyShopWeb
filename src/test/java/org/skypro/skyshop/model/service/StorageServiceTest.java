package org.skypro.skyshop.model.service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.product.Product;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StorageServiceTest {

    @Test
    void getProductById_ProductExists_ReturnsProduct() {
        // Arrange
        Product mockProduct = new SimpleProduct(UUID.randomUUID(), "Яблоки", 50.0);
        Map<UUID, Product> mockMap = Mockito.mock(Map.class);

        when(mockMap.get(mockProduct.getId())).thenReturn(mockProduct);

        StorageService storageService = new StorageService(mockMap);

        // Act
        Optional<Product> result = storageService.getProductById(mockProduct.getId());

        // Assert
        assertTrue(result.isPresent(), "Продукт должен быть найден");
        assertEquals(mockProduct, result.get(), "Найденный продукт должен совпадать с mockProduct");
        verify(mockMap, times(1)).get(mockProduct.getId());
    }

    @Test
    void getProductById_ProductDoesNotExist_ReturnsEmptyOptional() {
        // Arrange
        Map<UUID, Product> mockMap = Mockito.mock(Map.class);

        when(mockMap.get(any(UUID.class))).thenReturn(null);

        StorageService storageService = new StorageService(mockMap);

        // Act
        Optional<Product> result = storageService.getProductById(UUID.randomUUID());

        // Assert
        assertFalse(result.isPresent(), "Продукт не должен быть найден");
        verify(mockMap, times(1)).get(any(UUID.class));
    }
}
