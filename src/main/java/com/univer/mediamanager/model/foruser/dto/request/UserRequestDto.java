package com.univer.mediamanager.model.foruser.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserRequestDto {
    @NotBlank(message = "Логин не может отсутствовать")
    @Size(max = 30, message = "Логин не может превышать 30 символов")
    private String username;

    @NotBlank(message = "Пароль не может отсутствовать")
    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    @NotBlank(message = "Почта не может отсутствовать")
    @Email(message = "Некорректный формат email")
    private String email;
}
