package ru.practicum.item;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {
    ItemService itemService;

    @GetMapping
    public List<ItemDto> get(@RequestHeader("X-Later-User-Id") long userId,
                             @RequestParam(name = "tags", required = false) Set<String> tags) {
        if(tags == null || tags.isEmpty()) {
            return itemService.getItems(userId);
        } else {
            return itemService.getItems(userId, tags);
        }
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
                    @RequestBody ItemDto item) {
        return itemService.addNewItem(userId, item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader("X-Later-User-Id") long userId,
                           @PathVariable(name = "itemId") long itemId) {
        itemService.deleteItem(userId, itemId);
    }
}