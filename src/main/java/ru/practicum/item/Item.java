package ru.practicum.item;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.user.User;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String url;

    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "name")
    private Set<String> tags = new HashSet<>();
}