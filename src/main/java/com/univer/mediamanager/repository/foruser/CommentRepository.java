package com.univer.mediamanager.repository.foruser;

import com.univer.mediamanager.model.foruser.Comment;
import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.media.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMediaItemOrderByCreatedAtDesc(MediaItem mediaItem);

    List<Comment> findByUserAndMediaItem(User user, MediaItem mediaItem);
}