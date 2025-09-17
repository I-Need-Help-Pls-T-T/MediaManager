package com.univer.mediamanager.model.media.dto.request;

import com.univer.mediamanager.model.MultiplayerMode;
import com.univer.mediamanager.model.Platform;
import com.univer.mediamanager.model.common.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GameRequestDto extends MediaItemRequestDto {
    private int ageRating;

    private MultiplayerMode multiplayerMode;

    private List<Platform> platforms;

    @Min(value = 0, message = "Не может быть меньше 0")
    private int completionTime;

    @Min(value = 0, message = "Time in game cannot be negative")
    private int timeInGame;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    private List<Person> developer;
}
