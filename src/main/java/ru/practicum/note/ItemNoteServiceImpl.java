package ru.practicum.note;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.item.Item;
import ru.practicum.item.ItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemNoteServiceImpl implements ItemNoteService {
    ItemNoteRepository itemNoteRepository;
    ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto) {
        Item item = itemRepository.findById(itemNoteDto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item не найден"));
        ItemNote itemNote = itemNoteRepository.save(ItemNoteMapper.mapToItemNote(itemNoteDto, item));
        return ItemNoteMapper.mapToItemNoteDto(itemNote);
    }

    @Override
    public List<ItemNoteDto> searchNotesByUrl(String url, Long userId) {
        List<ItemNote> itemNotes = itemNoteRepository.findAllByItemUrlContainingAndItemUserId(url, userId);
        return ItemNoteMapper.mapToItemNoteDto(itemNotes);
    }

    @Override
    public List<ItemNoteDto> searchNotesByTag(long userId, String tag) {
        List<ItemNote> itemNotes = itemNoteRepository.findByTag(userId, tag);
        return ItemNoteMapper.mapToItemNoteDto(itemNotes);
    }

    @Override
    public List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size) {
        PageRequest page = PageRequest.of(from > 0 ? from / size : 0, size);
        return itemNoteRepository.findAllByItemUserId(userId, page)
                .map(ItemNoteMapper::mapToItemNoteDto)
                .getContent();
    }
}

