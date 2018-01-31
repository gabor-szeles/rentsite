package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.user.User;

public class Item extends Rentable {

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
