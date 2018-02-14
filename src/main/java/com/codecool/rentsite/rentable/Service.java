package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ServiceCategory;
import com.codecool.rentsite.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
                @NamedQuery(name = "service.all", query = "SELECT s FROM Service s "),
                @NamedQuery(name = "service.getByServiceCategory", query = "SELECT s FROM Service s WHERE s.serviceCategory.id = :categoryId ")
                })
public class Service extends Rentable {
    @ManyToOne
    private ServiceCategory serviceCategory;

    public Service(String name, User user, Price price, ServiceCategory serviceCategory) {
        super(name, user, price);
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
