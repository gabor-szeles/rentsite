package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.rentable.Item;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
                @NamedQuery(name = "itemCategory.all", query = "SELECT ic FROM ItemCategory ic "),
                @NamedQuery(name= "itemCategory.getItemCategory", query = "SELECT ic FROM ItemCategory ic WHERE ic.id = :id")
                })

public class ItemCategory extends Category {

    @OneToMany(mappedBy = "itemCategory")
    private Set<Item> itemSet = new HashSet<>();


    public ItemCategory(String name) {
        super(name);
    }

    public ItemCategory() {
    }
}
