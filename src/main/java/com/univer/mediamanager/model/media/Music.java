package com.univer.mediamanager.model.media;

import com.univer.mediamanager.model.common.Person;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Music extends MediaItem {
    private List<Person> artists;

    private int duration;

    private Long albumId;
}
