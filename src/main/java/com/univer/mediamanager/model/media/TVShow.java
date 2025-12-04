package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tv_show")
@PrimaryKeyJoinColumn(name = "media_item_id")
public class TVShow extends MediaItem {
    private String country;

    @ManyToMany
    @JoinTable(
            name = "tv_show_directors",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> directors;

    @ManyToMany
    @JoinTable(
            name = "tv_show_screenwriters",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> screenwriters;

    @ManyToMany
    @JoinTable(
            name = "tv_show_producers",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> producers;

    @ManyToMany
    @JoinTable(
            name = "tv_show_actors",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> actors;

    @ManyToMany
    @JoinTable(
            name = "tv_show_cinematographers",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> cinematographers;

    @ManyToMany
    @JoinTable(
            name = "tv_show_composers",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> composers;

    @ManyToMany
    @JoinTable(
            name = "tv_show_editors",
            joinColumns = @JoinColumn(name = "tv_show_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> editors;

    @Column(name = "number_of_episodes")
    private int numberOfEpisodes;

    private int runtime;
}
