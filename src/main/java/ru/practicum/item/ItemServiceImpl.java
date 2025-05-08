package ru.practicum.item;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    ItemRepository repository;

    @Override
    public List<ItemDto> getItems(Long userId) {
        List<Item> userItems = repository.findByUserId(userId);
        return ItemDto.mapToItemDto(userItems);
    }

    @Override
    @Transactional
    public ItemDto addNewItem(Long userId, ItemDto itemDto) {
        Item item = repository.save(ItemDto.mapToItem(itemDto, userId));
        return ItemDto.mapToItemDto(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long userId, Long itemId) {
        if (userId == null || itemId == null) {
            throw new IllegalArgumentException("UserId и ItemId должны быть не null");
        }
        repository.deleteByUserIdAndItemId(userId, itemId);
    }
}
