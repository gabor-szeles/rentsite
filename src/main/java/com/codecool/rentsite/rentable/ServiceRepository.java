package com.codecool.rentsite.rentable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<? extends Rentable> findByServiceCategoryId(int idNumber);

    List<Service> findByStatus(Status available);
}
