package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.enumeration.EnumRole;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {
    private Long id;
    private EnumRole name;
    private Date createdAt;
    private Date updatedAt;
    private List<UserRole> userRoles;
}
