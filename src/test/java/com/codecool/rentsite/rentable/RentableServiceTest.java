package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.CategoryDAO;
import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.rentable.category.ServiceCategory;
import com.codecool.rentsite.user.UserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RentableServiceTest {

    private static RentableService testRentableService;
    private static RentableDAO mockRentableDao;
    private static CategoryDAO mockCategoryDao;
    private static UserDao mockUserDao;
    private static ItemCategory mockItemCategory;
    private static ServiceCategory mockServiceCategory;
    private static Rentable mockRentable;
    private static List<Item> mockItemList;
    private static List<ItemCategory> itemCategoryList;
    private static List<ServiceCategory> serviceCategoryList;
    private static List<Rentable> rentableList;
    private static int userId;
    Map<String, List<Rentable>> testMap;
    private static Item mockItem;


    @BeforeAll
    public static void setUp() {
        mockCategoryDao = mock(CategoryDAO.class);
        mockRentableDao = mock(RentableDAO.class);
        mockUserDao = mock(UserDao.class);
        testRentableService = new RentableService(mockRentableDao, mockCategoryDao, mockUserDao);
        mockItemCategory = mock(ItemCategory.class);
        mockServiceCategory = mock(ServiceCategory.class);
        mockRentable = mock(Rentable.class);
        itemCategoryList = new ArrayList<>();
        itemCategoryList.add(mockItemCategory);
        serviceCategoryList = new ArrayList<>();
        serviceCategoryList.add(mockServiceCategory);
        rentableList = new ArrayList<>();
        rentableList.add(mockRentable);
        userId = 1;
        mockItemList = new ArrayList<>();
        mockItem = mock(Item.class);
        mockItemList.add(mockItem);
    }

    @Test
    public void testGetAllRentablesReturnsRentable() {
        when(mockCategoryDao.getItemCategories()).thenReturn(itemCategoryList);
        when(mockCategoryDao.getServiceCategories()).thenReturn(serviceCategoryList);
        when(mockRentableDao.getAll()).thenReturn(rentableList);
        testMap = testRentableService.getAllRentables(userId);
        assertEquals(mockRentable, testMap.get("rentableList").get(0));
    }

    @Test
    public void testGetAllRentablesReturnsCategoryList() {
        when(mockCategoryDao.getItemCategories()).thenReturn(itemCategoryList);
        when(mockCategoryDao.getServiceCategories()).thenReturn(serviceCategoryList);
        when(mockRentableDao.getAll()).thenReturn(rentableList);
        testMap = testRentableService.getAllRentables(userId);
        assertEquals(itemCategoryList, testMap.get("itemCategories"));
        assertEquals(serviceCategoryList, testMap.get("serviceCategories"));
    }

    @Test
    public void testGetAllRentablesThrowsError() {
        when(mockCategoryDao.getItemCategories()).thenReturn(itemCategoryList);
        when(mockCategoryDao.getServiceCategories()).thenReturn(serviceCategoryList);
        List<Rentable> testEmptyList = new ArrayList<>();
        when(mockRentableDao.getAll()).thenReturn(testEmptyList);
        testMap = testRentableService.getAllRentables(userId);
        assertThrows(IndexOutOfBoundsException.class, () -> testMap.get("rentableList").get(0));

    }

    @Test
    public void testGetUpdateDataWorksCorrectly(){
        String testString = "item_0";
        when(mockRentableDao.getByItemCategory(any(Integer.class))).thenReturn(mockItemList);
        List<? extends Rentable> testItemList = testRentableService.getUpdatedData(testString);
        assertEquals(mockItem, testItemList.get(0));
    }


    @Test
    public void testGetUpdateDataThrowsError(){
        String testString = "item_3";
        when(mockRentableDao.getByItemCategory(any(Integer.class))).thenReturn(mockItemList);
        List<? extends Rentable> testItemList = testRentableService.getUpdatedData(testString);
        assertThrows(IndexOutOfBoundsException.class, () -> testItemList.get(4));
    }

}