package ru.practicum.note;

import jakarta.persistence.*;
import lombok.*;
import ru.practicum.item.Item;

import java.time.Instant;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@Table(name = "item_notes")
public class ItemNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    // исключаем все поля с отложенной загрузкой из
    // метода toString, чтобы не было случайных обращений к
    // базе данных, например при выводе в лог.
    @ToString.Exclude
    private Item item;

    private String text;

    @Column(name = "note_date")
    private Instant dateOfNote = Instant.now();
}
