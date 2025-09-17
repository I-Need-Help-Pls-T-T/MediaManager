package com.univer.mediamanager.model.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credit {
    private Long mediaItemId;

    private Long personId;

    private String role; // directors, screenwriters, authors
}
