package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.converter.PlatformListConverter;
import com.univer.mediamanager.model.enums.MultiplayerMode;
import com.univer.mediamanager.model.common.Person;
import com.univer.mediamanager.model.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "games")
@PrimaryKeyJoinColumn(name = "media_item_id")
public class Game extends MediaItem {

    @Enumerated(EnumType.STRING)
    @Column(name = "multiplayer_mode")
    private MultiplayerMode multiplayerMode;

    @Convert(converter = PlatformListConverter.class)
    @Column(name = "platforms", columnDefinition = "TEXT")
    private List<Platform> platforms;

    @Column(name = "completion_time")
    private int completionTime;

    @Column(name = "time_in_game")
    private int timeInGame;

    private String publisher;

    @ManyToMany
    @JoinTable(
            name = "game_developers",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> developer;
}