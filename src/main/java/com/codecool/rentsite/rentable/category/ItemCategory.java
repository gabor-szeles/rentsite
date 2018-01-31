package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.rentable.Item;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ItemCategory extends Category {

    @OneToMany(mappedBy = "itemCategory")
    private Set<Item> itemSet = new HashSet<>();


    public ItemCategory(String name) {
        super(name);
    }
}
