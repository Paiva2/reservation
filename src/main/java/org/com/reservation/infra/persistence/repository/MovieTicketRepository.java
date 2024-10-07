package org.com.reservation.infra.persistence.repository;

import org.com.reservation.infra.persistence.entity.MovieTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTicketRepository extends JpaRepository<MovieTicketEntity, Long> {
}
