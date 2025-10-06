package com.univer.mediamanager.model.foruser.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;

    private String username;

    private String password;

    private String email;
}