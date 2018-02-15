package com.codecool.rentsite.rentable.category;

import java.util.List;

public interface CategoryDAOInterface {

    List<ItemCategory> getItemCategories();
    List<ServiceCategory> getServiceCategories();
    ItemCategory findItemCategory(int id);
    ServiceCategory findServiceCategory(int id);
}
