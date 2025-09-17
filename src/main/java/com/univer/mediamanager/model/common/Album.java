package com.univer.mediamanager.model.common;

import com.univer.mediamanager.model.media.Music;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Album {
    private Long id;

    private String name;

    private String coverURL;

    private int releaseYear;

    private List<Person> artists;

    private List<Music> tracks;
}
