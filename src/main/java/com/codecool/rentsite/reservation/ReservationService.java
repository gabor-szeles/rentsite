package com.codecool.rentsite.reservation;

import com.codecool.rentsite.rentable.Rentable;
import com.codecool.rentsite.rentable.RentableService;
import com.codecool.rentsite.rentable.Status;
import com.codecool.rentsite.user.User;
import com.codecool.rentsite.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentableService rentableService;

    public void createNewReservation(String rentableId, String userId) {
        Reservation newReservation = new Reservation();
        Rentable targetRentable = rentableService.getRentableById(rentableId);
        User rentingUser = userRepository.findOne(Long.parseLong(userId));
        targetRentable.setStatus(Status.RENTED);
        newReservation.setRentable(targetRentable);
        newReservation.setUser(rentingUser);
        newReservation.setRentFrom(ZonedDateTime.now());
        newReservation.setRentTo(ZonedDateTime.now().plusSeconds(5));
        rentingUser.addToReservationSet(newReservation);
        reservationRepository.save(newReservation);
        userRepository.save(rentingUser);
    }
    @Scheduled(fixedRate = 10000)
     public void updateReservations() {
        List<Long> outdated = reservationRepository.findExpiredReservation(ZonedDateTime.now());
        System.out.println("scheduled list "+ outdated);
        rentableService.resetRentablesToActive(outdated);
    }
}
