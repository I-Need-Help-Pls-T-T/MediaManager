package com.univer.mediamanager.model.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "media_item_id")
    private Long mediaItemId;

    @Column(name = "person_id")
    private Long personId;

    @Column(nullable = false)
    private String role; // directors, screenwriters, authors
}
