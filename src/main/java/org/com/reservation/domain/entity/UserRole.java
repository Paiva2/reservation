package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRole {
    private KeyId id;
    private User user;
    private Role role;
    private Date createdAt;
    private Date updatedAt;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class KeyId {
        private Long userId;
        private Long roleId;
    }
}
