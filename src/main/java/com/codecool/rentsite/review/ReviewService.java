package com.codecool.rentsite.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserRepository;


import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReservationReviewRepository reservationReviewRepository;

    @Autowired
    private UserReviewRepository userReviewRepository;

    @Autowired
    private UserRepository userRepository;

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
}
