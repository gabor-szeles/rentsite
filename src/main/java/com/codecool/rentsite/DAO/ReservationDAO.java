package com.codecool.rentsite.DAO;

import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationDAO {

    private static ReservationDAO reservationDAOInstance = null;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final EntityManager em = emf.createEntityManager();

    private ReservationDAO(){}

    public static ReservationDAO getInstance() {
        if(reservationDAOInstance == null) {
            reservationDAOInstance = new ReservationDAO();
        }
        return reservationDAOInstance;
    }

    public List<Reservation> executeQuery(){
        TypedQuery<Reservation> query = em.createNamedQuery("reservation.all",Reservation.class);
        List<Reservation> result = query.getResultList();
        return result;
    }

}
