package com.codecool.rentsite.rentable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<? extends Rentable> findByServiceCategoryId(long idNumber);

    List<Service> findByStatus(Status available);

    @Modifying
    @Transactional
    @Query(value = "update Service s set s.status=?2 where s.id=?1")
    void refreshStatusById(Long id, Status status);
}
