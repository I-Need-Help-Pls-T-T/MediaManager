package com.univer.mediamanager.service.foruser;

import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.UserMediaStatus;
import com.univer.mediamanager.model.foruser.UserMediaStatusId;
import com.univer.mediamanager.model.foruser.dto.request.UserMediaStatusRequestDto;
import com.univer.mediamanager.model.foruser.dto.response.UserMediaStatusResponseDto;
import com.univer.mediamanager.model.media.MediaItem;
import com.univer.mediamanager.repository.foruser.UserMediaStatusRepository;
import com.univer.mediamanager.service.foruser.mapper.UserMediaStatusMapper;
import com.univer.mediamanager.service.media.MediaItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.univer.mediamanager.exception.ResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserMediaStatusService {

    private final UserMediaStatusRepository userMediaStatusRepository;
    private final UserMediaStatusMapper userMediaStatusMapper;
    private final UserService userService;
    private final MediaItemService mediaItemService;

    @Transactional(readOnly = true)
    public List<UserMediaStatusResponseDto> findAll() {
        log.info("Getting all user media statuses");
        return userMediaStatusRepository.findAll()
                .stream()
                .map(userMediaStatusMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UserMediaStatusResponseDto> findById(Long userId, Long mediaItemId) {
        log.info("Getting user media status by user id: {} and media item id: {}", userId, mediaItemId);
        UserMediaStatusId id = new UserMediaStatusId();
        id.setUser(userId);
        id.setMediaItem(mediaItemId);
        return userMediaStatusRepository.findById(id)
                .map(userMediaStatusMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public List<UserMediaStatusResponseDto> findByUser(Long userId) {
        log.info("Getting all user media statuses for user id: {}", userId);
        User user = userService.getById(userId);
        return userMediaStatusRepository.findByUser(user)
                .stream()
                .map(userMediaStatusMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserMediaStatusResponseDto> findByUserAndIsFavorite(Long userId, boolean isFavorite) {
        log.info("Getting favorite user media statuses for user id: {}", userId);
        User user = userService.getById(userId);
        return userMediaStatusRepository.findByUserAndIsFavorite(user, isFavorite)
                .stream()
                .map(userMediaStatusMapper::toResponseDto)
                .collect(Collectors.toList());
    }

  //    public UserMediaStatusResponseDto createOrUpdate(UserMediaStatusRequestDto dto) {
//        log.info("Creating or updating user media status for user id: {} and media item id: {}", dto.getUserId(), dto.getMediaItemId());
//
//        User user = userService.getById(dto.getUserId());
//        MediaItem mediaItem = mediaItemService.getById(dto.getMediaItemId());
//
//        Optional<UserMediaStatus> existingStatus = userMediaStatusRepository.findByUserAndMediaItem(user, mediaItem);
//        UserMediaStatus status;
//
//        if (existingStatus.isPresent()) {
//            status = existingStatus.get();
//            log.info("Found existing status, updating...");
//        } else {
//            status = new UserMediaStatus();
//            UserMediaStatusId id = new UserMediaStatusId();
//            id.setUser(user.getId());
//            id.setMediaItem(mediaItem.getId());
//            status.setId(id);
//            status.setUser(user);
//            status.setMediaItem(mediaItem);
//            status.setAddedDate(LocalDate.now());
//            log.info("Creating new status...");
//        }
//
//        if (dto.getStatus() != null) {
//            status.setStatus(dto.getStatus());
//        }
//        if (dto.getRating() >= 0) {
//            status.setRating(dto.getRating());
//        }
//        if (dto.getNotes() != null) {
//            status.setNotes(dto.getNotes());
//        }
//        status.setFavorite(dto.isFavorite());
//
//        UserMediaStatus savedStatus = userMediaStatusRepository.save(status);
//        return userMediaStatusMapper.toResponseDto(savedStatus);
//    }

    public void delete(Long userId, Long mediaItemId) {
        log.info("Deleting user media status for user id: {} and media item id: {}", userId, mediaItemId);
        UserMediaStatusId id = new UserMediaStatusId();
        id.setUser(userId);
        id.setMediaItem(mediaItemId);

        if (!userMediaStatusRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserMediaStatus not found for user id: " + userId + " and media item id: " + mediaItemId);
        }
        userMediaStatusRepository.deleteById(id);
    }
}