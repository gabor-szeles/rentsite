package com.codecool.rentsite.DAO;

import com.codecool.rentsite.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RentableDAO {

    private static RentableDAO rentableDAOInstance = null;

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
    private final EntityManager em = emf.createEntityManager();

    private RentableDAO(){}

    public static RentableDAO getInstance() {
        if(rentableDAOInstance == null) {
            rentableDAOInstance = new RentableDAO();
        }
        return rentableDAOInstance;
    }

    public List<User> executeQuery(){
        TypedQuery<User> query = em.createNamedQuery("user.all",User.class);
        List<User> result = query.getResultList();
        return result;
    }
}
