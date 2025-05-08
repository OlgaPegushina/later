package ru.practicum.item;

import java.util.List;

public interface ItemService {
    List<ItemDto> getItems(Long userId);

    ItemDto addNewItem(Long userId, Item item);

    void deleteItem(Long userId, Long itemId);
}
