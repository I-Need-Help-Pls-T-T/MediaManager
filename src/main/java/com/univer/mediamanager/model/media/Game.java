package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.enums.MultiplayerMode;
import com.univer.mediamanager.model.common.Person;
import com.univer.mediamanager.model.enums.Platform;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Game extends MediaItem {
    private int ageRating;

    private MultiplayerMode multiplayerMode;

    private List<Platform> platforms;

    private int completionTime;

    private int timeInGame;

    private String publisher;

    private List<Person> developer;
}
