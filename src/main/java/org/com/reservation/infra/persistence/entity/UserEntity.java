package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_USERS")
@SequenceGenerator(name = "user_local_seq", sequenceName = "tb_users_us_id_seq", allocationSize = 1)
public class UserEntity {
    @Id
    @Column(name = "US_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_local_seq")
    private Long id;

    @Column(name = "US_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "US_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "US_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "US_PASSWORD", nullable = false)
    private String password;

    @Column(name = "US_PROFILE_PICTURE", nullable = true)
    private String profilePicture;

    @Column(name = "US_CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "US_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "US_DISABLED_AT", nullable = true)
    private Date disabledAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ReservationEntity> reservations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRoleEntity> userRoles;
}
