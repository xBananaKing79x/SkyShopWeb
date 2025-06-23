package org.skypro.skyshop.model.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.searchengine.SearchResult;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {
    private final StorageService storageService;

 public SearchServiceTest(StorageService storageService) {
        this.storageService = storageService;
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
        verify(mockStorageService, times(1)).getAllSearchables();
    }
}
