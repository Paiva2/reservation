package org.com.reservation.application.controller.input.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserInput {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private String profilePicture;

    public User toUser(RegisterUserInput input) {
        return User.builder()
            .firstName(input.getFirstName())
            .lastName(input.getLastName())
            .password(input.getPassword())
            .email(input.getEmail())
            .profilePicture(input.getProfilePicture())
            .build();
    }
}
