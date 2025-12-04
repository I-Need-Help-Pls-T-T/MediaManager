package com.univer.mediamanager.service.foruser;

import com.univer.mediamanager.model.foruser.Comment;
import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.dto.request.CommentRequestDto;
import com.univer.mediamanager.model.foruser.dto.response.CommentResponseDto;
import com.univer.mediamanager.model.media.MediaItem;
import com.univer.mediamanager.repository.foruser.CommentRepository;
import com.univer.mediamanager.service.foruser.mapper.CommentMapper;
import com.univer.mediamanager.service.media.MediaItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.univer.mediamanager.exception.ResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final MediaItemService mediaItemService;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll() {
        log.info("Getting all comments");
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CommentResponseDto> findById(Long id) {
        log.info("Getting comment by id: {}", id);
        return commentRepository.findById(id)
                .map(commentMapper::toResponseDto);
    }

//    @Transactional(readOnly = true)
//    public List<CommentResponseDto> findByMediaItem(Long mediaItemId) {
//        log.info("Getting comments for media item id: {}", mediaItemId);
//        // Предполагается, что mediaItemService.getById возвращает сущность MediaItem
//        MediaItem mediaItem = mediaItemService.getById(mediaItemId);
//        return commentRepository.findByMediaItemOrderByCreatedAtDesc(mediaItem)
//                .stream()
//                .map(commentMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }

//    @Transactional(readOnly = true)
//    public List<CommentResponseDto> findByUserAndMediaItem(Long userId, Long mediaItemId) {
//        log.info("Getting comments by user id: {} and media item id: {}", userId, mediaItemId);
//        User user = userService.getById(userId);
//        MediaItem mediaItem = mediaItemService.getById(mediaItemId);
//        return commentRepository.findByUserAndMediaItem(user, mediaItem)
//                .stream()
//                .map(commentMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }

//    public CommentResponseDto create(CommentRequestDto dto) {
//        log.info("Creating new comment for media item id: {} by user id: {}", dto.getMediaId(), dto.getUserId());
//
//        User user = userService.getById(dto.getUserId());
//        MediaItem mediaItem = mediaItemService.getById(dto.getMediaId());
//
//        Comment comment = new Comment();
//        comment.setText(dto.getText());
//        comment.setUser(user);
//        comment.setMediaItem(mediaItem);
//        comment.setCreatedAt(LocalDateTime.now());
//
//        Comment savedComment = commentRepository.save(comment);
//        return commentMapper.toResponseDto(savedComment);
//    }

    public CommentResponseDto update(Long id, CommentRequestDto dto) {
        log.info("Updating comment with id: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));

        comment.setText(dto.getText());

        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toResponseDto(updatedComment);
    }

    public void delete(Long id) {
        log.info("Deleting comment with id: {}", id);
        if (!commentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}