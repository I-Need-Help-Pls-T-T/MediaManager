package com.univer.mediamanager.model.media.dto.request;

import com.univer.mediamanager.model.enums.MultiplayerMode;
import com.univer.mediamanager.model.enums.Platform;
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

    private int completionTime;

    @Min(value = 0, message = "Время не может быть меньше 0")
    private int timeInGame;

    private String publisher;

    @NotBlank(message = "Разработчик не может отсутствовать")
    private List<Person> developer;
}
