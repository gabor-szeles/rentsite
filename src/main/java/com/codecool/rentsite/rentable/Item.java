package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(@NamedQuery(name = "item.all", query = "SELECT i FROM Item i "))
public class Item extends Rentable {

    @ManyToOne
    private ItemCategory itemCategory;



    public Item(String name, User user, Price price, ItemCategory itemCategory) {
        super(name, user, price);
        this.itemCategory = itemCategory;
    }

    public Item() {
    }


    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }
}
