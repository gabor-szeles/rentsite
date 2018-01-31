package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ServiceCategory;
import com.codecool.rentsite.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Service extends Rentable {
    @ManyToOne
    private ServiceCategory serviceCategory;

    public Service(String name, User user, ServiceCategory serviceCategory) {
        super(name, user);
        this.serviceCategory = serviceCategory;
    }

    public Service() {
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
}
