package com.univer.mediamanager.repository.foruser;

import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.UserMediaStatus;
import com.univer.mediamanager.model.foruser.UserMediaStatusId;
import com.univer.mediamanager.model.media.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMediaStatusRepository extends JpaRepository<UserMediaStatus, UserMediaStatusId> {

    Optional<UserMediaStatus> findByUserAndMediaItem(User user, MediaItem mediaItem);

    List<UserMediaStatus> findByUser(User user);

    List<UserMediaStatus> findByUserAndIsFavorite(User user, boolean isFavorite);
}