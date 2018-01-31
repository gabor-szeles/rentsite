package com.codecool.rentsite.rentable.category;

import com.codecool.rentsite.rentable.Service;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ServiceCategory extends Category {

    @OneToMany(mappedBy = "serviceCategory")
    private Set<Service> serviceSet = new HashSet<>();

    public ServiceCategory(String name) {
        super(name);
    }

    public ServiceCategory() {
    }
}
