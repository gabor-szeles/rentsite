package com.codecool.rentsite.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT r.rentable.id FROM Reservation r " +
                    "INNER JOIN r.rentable " +
                    "WHERE r.rentable.status='RENTED' AND r.rentTo<?1")
    List<Long> findExpiredReservation(ZonedDateTime currentTime);

    Reservation findByRentableId(long l);

    List<Reservation> findByRentableIdAndUserId(long id, long userId);
}
