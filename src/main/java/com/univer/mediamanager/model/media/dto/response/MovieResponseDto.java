package com.univer.mediamanager.model.media.dto.response;

import com.univer.mediamanager.model.common.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieResponseDto extends MediaItemResponseDto {
    private String country;

    private int runtime;

    private int budget;

    private int ageRating;

    private List<Person> directors;

    private List<Person> screenwriters;

    private List<Person> producers;

    private List<Person> actors;

    private List<Person> cinematographers;

    private List<Person> composers;

    private List<Person> editors;
}
