package org.skypro.skyshop.model.searchengine;

import org.skypro.skyshop.model.service.StorageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {

        this.storageService = storageService;
    }

    // Метод поиска
    public Collection<SearchResult> search(String pattern) {
        // Получаем все объекты типа Searchable
        return storageService.getAllSearchables().stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable) // Преобразуем в SearchResult
                .collect(Collectors.toList());
    }
}
