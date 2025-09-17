package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Person;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Book extends MediaItem {
    private List<Person> authors;

    private int pageCount;

    private String publisher;

    private String series;

    private String bindingType;

    private int ageRating;

    private int circulating;
}
