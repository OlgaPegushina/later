package ru.practicum.item;

import lombok.experimental.NonFinal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepositoryInMemory implements ItemRepository {
    Map<Long, Item> items = new HashMap();

    @NonFinal
    Long globalItemId = 0L;

    @Override
    public List<Item> findByUserId(long userId) {
        return items.values().stream()
                .filter(item -> item.getUserId() == userId)
                .toList();
    }

    @Override
    public Item save(Item item) {
        Long id = generateId();
        item.setId(id);
        items.put(id, item);
        return items.get(id);
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        Item item = items.get(itemId);
        if (item.getUserId() == userId) {
            items.remove(itemId);
        }
    }

    private Long generateId() {
        return ++globalItemId;
    }
}
