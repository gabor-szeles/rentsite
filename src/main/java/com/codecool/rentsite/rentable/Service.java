package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ServiceCategory;
import com.codecool.rentsite.user.User;

public class Service extends Rentable {

    private ServiceCategory serviceCategory;

    public Service(String name, User user, ServiceCategory serviceCategory) {
        super(name, user);
        this.serviceCategory = serviceCategory;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
}
