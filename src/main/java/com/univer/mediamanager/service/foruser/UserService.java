package com.univer.mediamanager.service.foruser;

import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.dto.request.UserRequestDto;
import com.univer.mediamanager.model.foruser.dto.response.UserResponseDto;
import com.univer.mediamanager.repository.foruser.UserRepository;
import com.univer.mediamanager.service.foruser.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.univer.mediamanager.exception.ResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        log.info("Getting all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findById(Long id) {
        log.info("Getting user by id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findByEmail(String email) {
        log.info("Getting user by email: {}", email);
        return userRepository.findByEmail(email)
                .map(userMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findByUsername(String username) {
        log.info("Getting user by username: {}", username);
        return userRepository.findByUsername(username)
                .map(userMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserResponseDto create(UserRequestDto dto) {
        log.info("Creating new user with username: {}", dto.getUsername());
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        log.info("Updating user with id: {}", id);
        User user = getById(id);
        userMapper.updateEntityFromDto(dto, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }

    public void delete(Long id) {
        log.info("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}