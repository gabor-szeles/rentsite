package com.codecool.rentsite.reservation;

import com.codecool.rentsite.reservation.Reservation;
import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationDAO {


    private EntityManager entityManager;

    public ReservationDAO(EntityManagerFactory emf){
        this.entityManager = emf.createEntityManager();
    }


    public List<Reservation> executeQuery(){
        TypedQuery<Reservation> query = entityManager.createNamedQuery("reservation.all",Reservation.class);
        List<Reservation> result = query.getResultList();
        return result;
    }

}
