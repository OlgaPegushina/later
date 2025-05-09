package ru.practicum.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable {
    private Long id;
    private Long userId;
    private String url;
    private Set<String> tags;

    public static ItemDto mapToItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getUser().getId(),
                item.getUrl(),
                new HashSet<>(item.getTags())
        );
    }

    public static List<ItemDto> mapToItemDto(Iterable<Item> items) {
        List<ItemDto> dtos = new ArrayList<>();
        for (Item item : items) {
            dtos.add(mapToItemDto(item));
        }
        return dtos;
    }

    public static Item mapToItem(ItemDto itemDto, User user) {
        Item item = new Item();
        item.setUser(user);
        item.setUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        return item;
    }
}
