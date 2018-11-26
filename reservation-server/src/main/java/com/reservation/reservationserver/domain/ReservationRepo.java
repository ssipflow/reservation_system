package com.reservation.reservationserver.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepo extends CrudRepository<Reservation, Object> {

    @Query("SELECT r FROM Reservation r WHERE start_time >= :start_time AND end_time <= :end_time")
    List<Reservation> findByReserveByMonth(@Param("start_time")LocalDateTime start_time, @Param("end_time")LocalDateTime end_time);

    @Query("SELECT r FROM Reservation r WHERE start_time >= :start_time AND end_time <= :end_time AND room = :room")
    List<Reservation> findByReserveRangeByRoom(@Param("start_time")LocalDateTime start_time, @Param("end_time")LocalDateTime end_time, @Param("room")String room);
}
