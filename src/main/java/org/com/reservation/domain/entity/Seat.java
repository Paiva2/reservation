package org.com.reservation.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.com.reservation.domain.enumeration.EnumSeatRow;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Seat {
    private Long id;
    private EnumSeatRow row;
    private Integer number;
    private Boolean accessibility;
    private Date createdAt;
    private Date updatedAt;
    private List<RoomSeat> roomSeats;

    private Boolean available;
}
