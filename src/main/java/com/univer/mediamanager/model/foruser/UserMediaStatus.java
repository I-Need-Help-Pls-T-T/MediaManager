package com.univer.mediamanager.model.foruser;

import com.univer.mediamanager.model.enums.Status;
import com.univer.mediamanager.model.media.MediaItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_media_status")
public class UserMediaStatus {
    @EmbeddedId
    private UserMediaStatusId id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("mediaItem")
    @JoinColumn(name = "media_item_id", nullable = false)
    private MediaItem mediaItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    private double rating;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "added_date")
    private LocalDate addedDate = LocalDate.now();

    @Column(name = "is_favorite")
    private boolean isFavorite;

    public UserMediaStatus() {
    }
}