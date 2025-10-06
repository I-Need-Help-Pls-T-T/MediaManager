package com.univer.mediamanager.model.media.dto.request;

import com.univer.mediamanager.model.common.Person;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookRequestDto extends MediaItemRequestDto {
    private List<Person> authors;

    @Min(value = 1, message = "Количество страниц не может быть меньше 1")
    private int pageCount;

    private String publisher;

    private String series;

    private String bindingType;

    @Min(value = 0, message = "Возрастной рейтинг не может быть меньше 0")
    private int ageRating;

    private int circleRating;
}
