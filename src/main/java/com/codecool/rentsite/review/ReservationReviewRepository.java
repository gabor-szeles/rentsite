package com.codecool.rentsite.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationReviewRepository extends JpaRepository<ReservationReview, Long> {

    @Query(value = "SELECT r FROM ReservationReview r " +
            "INNER JOIN r.reservation " +
            "WHERE r.reservation.rentable.id=?1")
    List<ReservationReview> findByReservationId(Long id);


}
