package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePicture;
    private Date createdAt;
    private Date updatedAt;
    private Date disabledAt;
    private List<Reservation> reservations;
    private List<UserRole> roles;
}
