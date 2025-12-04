package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Table(name = "books")
@PrimaryKeyJoinColumn(name = "media_item_id")
public class Book extends MediaItem {
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "books_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> authors;

    @Column(name = "page_count")
    private int pageCount;

    private String publisher;

    private String series;

    @Column(name = "binding_type")
    private String bindingType;

    private int circulating;
}
