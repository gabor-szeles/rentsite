package com.codecool.rentsite.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReservationReviewRepository reservationReviewRepository;

    public List<ReservationReview> getAllReviewsByRentableId(String id) {
        return reservationReviewRepository.findByReservationId(Long.parseLong(id));
    }
}
