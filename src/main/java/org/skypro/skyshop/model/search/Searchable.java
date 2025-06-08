package org.skypro.skyshop.model.search;

public interface Searchable {
    String getSearchTerm(); // Термин поиска
    boolean isSpecial();

    String getProductName();

    String getType(); // Тип контента

    String getSearchableName(); // Имя объекта
    // Дефолтный метод для строкового представления
    default String getStringRepresentation() {
        return getSearchableName() + " — " + getType();
    }

}
