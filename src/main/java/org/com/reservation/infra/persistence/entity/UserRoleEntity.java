package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_USER_ROLE")
public class UserRoleEntity {
    @EmbeddedId
    private KeyId id;

    @Column(name = "UR_CREATED_AT", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "UR_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "UR_USER_ID")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "UR_ROLE_ID")
    private RoleEntity role;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Embeddable
    public static class KeyId implements Serializable {
        @Column(name = "UR_USER_ID")
        private Long userId;

        @Column(name = "UR_ROLE_ID")
        private Long roleId;
    }
}
