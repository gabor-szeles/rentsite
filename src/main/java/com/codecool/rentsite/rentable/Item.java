package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Item extends Rentable {

    @ManyToOne
    private ItemCategory itemCategory;

    public Item(String name, User user, ItemCategory itemCategory) {
        super(name, user);
        this.itemCategory = itemCategory;
    }


    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
