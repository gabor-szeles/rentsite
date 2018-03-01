package com.codecool.rentsite.review;

import com.codecool.rentsite.rentable.*;
import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserRepository;


import javax.servlet.http.HttpSession;
import java.util.Map;

@org.springframework.stereotype.Service
public class ReviewService {

    @Autowired
    private ReservationReviewRepository reservationReviewRepository;

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public ReviewService(UserReviewRepository userReviewRepository, UserRepository userRepository){
        this.userRepository = userRepository;
        this.userReviewRepository = userReviewRepository;
    }


    public List<ReservationReview> getAllReviewsByRentableId(String id) {
        return reservationReviewRepository.findByReservationId(Long.parseLong(id));
    }

    public void add(Map<String, String> reqPar, String receiverId, String writerId) {
        String description = reqPar.get("description");
        int rate =Integer.parseInt(reqPar.get("rate"));

        User author = userRepository.getOne(Long.parseLong(writerId));
        User receiver = userRepository.getOne(Long.parseLong(receiverId));

        UserReview userReview = new UserReview();

        userReview.setDescription(description);
        userReview.setRate(rate);
        userReview.setAuthor(author);
        userReview.setUser(receiver);

        author.getWrittenReviews().add(userReview);
        receiver.getRecievedReviews().add(userReview);

        userReviewRepository.save(userReview);
    }

    public void addReservationReview(String rentableID, Map<String, String> reqPar, HttpSession session) {
        String description = reqPar.get("description");
        int rate =Integer.parseInt(reqPar.get("rate"));
        User author = userRepository.getOne(Long.parseLong(session.getAttribute("userId").toString()));
        ReservationReview reservationReview = new ReservationReview();
        reservationReview.setDescription(description);
        reservationReview.setRate(rate);
        reservationReview.setAuthor(author);
        author.getWrittenReviews().add(reservationReview);
        Reservation reservation = reservationRepository.findByRentableIdAndUserId(Long.parseLong(rentableID), author.getId());
        reservationReview.setReservation(reservation);
        reservation.setReservationReview(reservationReview);
        reservationReviewRepository.save(reservationReview);
        System.out.println("ok");
    }

    public boolean rented(int userId, String id) {
        return (reservationRepository.findByRentableIdAndUserId(Long.parseLong(id), (long) userId)) != null;
    }
}
