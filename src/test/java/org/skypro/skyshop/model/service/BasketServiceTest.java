package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exeptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
    private final ProductBasket productBasket;
    private final StorageService storageService;
    StorageService mockStorageService = Mockito.mock(StorageService.class);
    ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);
    public BasketServiceTest(ProductBasket mockProductBasket, StorageService mockStorageService) {
        this.productBasket = mockProductBasket;
        this.storageService = mockStorageService;
    }
    @Test
    void addNonExistentProduct_ThrowsNoSuchProductException() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);
        ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);

        UUID productId = UUID.randomUUID();
        when(mockStorageService.getProductById(productId)).thenReturn(Optional.empty());

        BasketService basketService = new BasketService(mockProductBasket, mockStorageService);

        // Act & Assert
        assertThrows(NoSuchProductException.class,() -> basketService.addProductToBasket(productId));
    }
    @Test
    void addExistingProduct_CallsAddProductOnProductBasket() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);
        ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);

        Product product = new SimpleProduct(UUID.randomUUID(), "Яблоки", 50.0);
        when(mockStorageService.getProductById(product.getId())).thenReturn(Optional.of(product));

        BasketService basketService = new BasketService(mockProductBasket, mockStorageService);

        // Act
        basketService.addProductToBasket(product.getId());

        // Assert
        verify(mockProductBasket, times(1)).addProduct(product.getId()); // Проверяем вызов addProduct
    }
    @Test
    void getUserBasket_ReturnsEmptyBasket_WhenProductBasketIsEmpty() {
        // Arrange
        StorageService mockStorageService = Mockito.mock(StorageService.class);
        ProductBasket mockProductBasket = Mockito.mock(ProductBasket.class);

        when(mockProductBasket.getProducts()).thenReturn(Collections.emptyMap());

        BasketService basketService = new BasketService(mockProductBasket, mockStorageService);

        // Act
        UserBasket result = basketService.getUserBasket();

        // Assert
        assertNotNull(result, "Результат не должен быть null");
        assertTrue(result.getItems().isEmpty(), "Корзина должна быть пустой");
        assertEquals(0.0, result.getTotal(), "Общая стоимость должна быть 0.0");
        verify(mockProductBasket, times(1)).getProducts();
    }
}
