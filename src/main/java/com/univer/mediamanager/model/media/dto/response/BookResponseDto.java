package com.univer.mediamanager.model.media.dto.response;

import com.univer.mediamanager.model.common.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookResponseDto extends MediaItemResponseDto {
    private List<Person> authors;

    private int pageCount;

    private String publisher;

    private String series;

    private String bindingType;

    private int ageRating;

    private int circulating;
}
