package com.univer.mediamanager.model.foruser.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserResponseDto {
    private Long id;

    private String username;

    private String password;

    private String email;
}