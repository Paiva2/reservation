package org.com.reservation.application.controller.dto.input.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginInput {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public static User toUser(UserLoginInput input) {
        return User.builder()
            .email(input.getEmail())
            .password(input.getPassword())
            .build();
    }
}
