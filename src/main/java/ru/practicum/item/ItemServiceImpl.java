package ru.practicum.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.user.User;
import ru.practicum.user.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    UserRepository userRepository;

    @Override
    public List<ItemDto> getItems(Long userId) {
        List<Item> userItems = itemRepository.findByUserId(userId);
        return ItemDto.mapToItemDto(userItems);
    }

    @Override
    public List<ItemDto> getItems(long userId, Set<String> tags) {
        BooleanExpression byUserId = QItem.item.user.id.eq(userId);
        BooleanExpression byAnyTag = QItem.item.tags.any().in(tags);
        Iterable<Item> foundItems = itemRepository.findAll(byUserId.and(byAnyTag));
        return ItemDto.mapToItemDto(foundItems);
    }
    @Override
    @Transactional
    public ItemDto addNewItem(Long userId, ItemDto itemDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User не найден"));
        Item item = itemRepository.save(ItemDto.mapToItem(itemDto, user));
        return ItemDto.mapToItemDto(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long userId, Long itemId) {
        if (userId == null || itemId == null) {
            throw new IllegalArgumentException("UserId и ItemId должны быть не null");
        }
        itemRepository.deleteByUserIdAndId(userId, itemId);
    }
}
