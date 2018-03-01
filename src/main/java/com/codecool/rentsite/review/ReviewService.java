package com.codecool.rentsite.review;

import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;


    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository){
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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

        reviewRepository.save(userReview);
    }
}
