package com.univer.mediamanager.model.media.dto.request;

import com.univer.mediamanager.model.common.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieRequestDto extends MediaItemRequestDto {
    @NotBlank(message = "Страна не может отсутствовать")
    private String country;

    @Min(value = 0, message = "Длительность не может быть меньше 0")
    private int runtime;

    private int budget;

    @Min(value = 0, message = "Возрастной рейтинг не может быть меньше 0")
    private int ageRating;

    @NotBlank(message = "Режиссёр не может отсутствовать")
    private List<Person> directors;

    @NotBlank(message = "Сценарист не может отсутствовать")
    private List<Person> screenwriters;

    private List<Person> producers;

    private List<Person> actors;

    @NotBlank(message = "Оператор не может отсутствовать")
    private List<Person> cinematographers;

    private List<Person> composers;

    private List<Person> editors;
}