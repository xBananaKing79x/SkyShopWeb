package org.skypro.skyshop.model.searchengine;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    // Конструктор
    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    // Геттеры
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    // Статический фабричный метод для создания объекта из Searchable
    public static SearchResult fromSearchable(Searchable searchable) {
        String contentType = searchable instanceof Article ? "ARTICLE" : "PRODUCT";
        return new SearchResult(searchable.getId(), searchable.getSearchableName(), contentType);
    }
}
