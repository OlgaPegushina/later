package ru.practicum.item;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemServiceImpl implements ItemService {
    ItemRepository repository;

    @Override
    public List<ItemDto> getItems(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(ItemDto::mapToItemDto)
                .toList();
    }

    @Override
    public ItemDto addNewItem(Long userId, Item item) {
        item.setUserId(userId);
        return ItemDto.mapToItemDto(repository.save(item));
    }

    @Override
    public void deleteItem(Long userId, Long itemId) {
        if (userId == null || itemId == null) {
            throw new IllegalArgumentException("UserId и ItemId должны быть не null");
        }
        repository.deleteByUserIdAndItemId(userId, itemId);
    }
}
