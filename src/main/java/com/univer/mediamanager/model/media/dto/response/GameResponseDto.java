package com.univer.mediamanager.model.media.dto.response;

import com.univer.mediamanager.model.enums.MultiplayerMode;
import com.univer.mediamanager.model.enums.Platform;
import com.univer.mediamanager.model.common.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GameResponseDto extends MediaItemResponseDto {
    private int ageRating;

    private MultiplayerMode multiplayerMode;

    private List<Platform> platforms;

    private int completionTime;

    private int timeInGame;

    private String publisher;

    private List<Person> developer;
}
