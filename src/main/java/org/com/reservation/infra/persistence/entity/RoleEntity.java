package org.com.reservation.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.reservation.domain.enumeration.EnumRole;
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
@Table(name = "TB_ROLES")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RL_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "RL_NAME", nullable = false, unique = true)
    private EnumRole name;

    @Column(name = "RL_CREATED_AT", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "RL_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRoleEntity> userRoles;
}
