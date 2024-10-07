package org.com.reservation.domain.interfaces.dataprovider;

import org.com.reservation.domain.entity.MovieTicket;

public interface MovieTicketDataProvider {
    MovieTicket persist(MovieTicket movieTicket);
}
