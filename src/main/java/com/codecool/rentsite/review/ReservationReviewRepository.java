package com.codecool.rentsite.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationReviewRepository extends JpaRepository<ReservationReview, Long> {

    List<ReservationReview> findByReservationId(Long id);
}
