package com.codecool.rentsite.rentable;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    List<? extends Rentable> findByItemCategoryId(long idNumber);

    List<Item> findByStatus(Status available);

    @Modifying
    @Transactional
    @Query(value = "update Item i set i.status=?2 where i.id=?1")
    void refreshStatusById(Long id, Status status);
}
