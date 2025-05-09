package ru.practicum.item;

import java.util.List;
import java.util.Set;

public interface ItemService {
    List<ItemDto> getItems(Long userId);

    List<ItemDto> getItems(long userId, Set<String> tags);

    ItemDto addNewItem(Long userId, ItemDto item);

    void deleteItem(Long userId, Long itemId);
}
