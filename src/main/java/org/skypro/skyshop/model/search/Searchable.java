package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    public int UUID = 0;
    String getSearchTerm(); // Термин поиска
    boolean isSpecial();

    String getProductName();

    String getType(); // Тип контента

    String getSearchableName(); // Имя объекта
    // Дефолтный метод для строкового представления
    default String getStringRepresentation() {
        return getSearchableName() + " — " + getType();
    }
    UUID getId();
}
