package com.codecool.rentsite.rentable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    List<? extends Rentable> findByItemCategoryId(int idNumber);

    List<Item> findByStatus(Status available);
}
