package com.univer.mediamanager.service.foruser.mapper;

import com.univer.mediamanager.model.foruser.User;
import com.univer.mediamanager.model.foruser.dto.request.UserRequestDto;
import com.univer.mediamanager.model.foruser.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDto toResponseDto(User entity) {
        if (entity == null) {
            return null;
        }

        return UserResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public void updateEntityFromDto(UserRequestDto dto, User entity) {
        if (dto == null || entity == null) return;

        setIfNotNull(entity::setUsername, dto.getUsername());
        setIfNotNull(entity::setEmail, dto.getEmail());
    }

    private <T> void setIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}