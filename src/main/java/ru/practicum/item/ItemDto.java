package ru.practicum.item;

import lombok.Data;

@Data
public class ItemDto {
    private String url;

    public static ItemDto mapToItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setUrl(item.getUrl());
        return dto;
    }
}
