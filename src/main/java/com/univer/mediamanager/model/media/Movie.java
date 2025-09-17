package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Person;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Movie extends MediaItem {
    private String country;

    private int runtime;

    private int budget;

    private int boxOffice;

    private int ageRating;

    private List<Person> directors;

    private List<Person> screenwriters;

    private List<Person> producers;

    private List<Person> actors;

    private List<Person> cinematographers;

    private List<Person> composers;

    private List<Person> editors;
}
