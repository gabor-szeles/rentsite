package com.codecool.rentsite.rentable;

import com.codecool.rentsite.rentable.category.ItemCategory;
import com.codecool.rentsite.rentable.category.ItemCategoryRepository;
import com.codecool.rentsite.rentable.category.ServiceCategory;
import com.codecool.rentsite.rentable.category.ServiceCategoryRepository;
import com.codecool.rentsite.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class RentableServiceTest {

    private static RentableService testRentableService;
    private static ItemCategory mockItemCategory;
    private static ServiceCategory mockServiceCategory;
    private static List<Item> mockItemList;
    private static List<Service> mockServiceList;
    private static List<ItemCategory> itemCategoryList;
    private static List<ServiceCategory> serviceCategoryList;
    private static int userId;
    Map<String, List<Rentable>> testMap;
    private static Item mockItem;
    private static Service mockService;
    private static ItemRepository mockItemRepository;
    private static ServiceRepository mockServiceRepository;
    private static ItemCategoryRepository mockItemCategoryRepository;
    private static ServiceCategoryRepository mockServiceCategoryRepository;
    private static UserRepository mockUserRepository;


    @BeforeAll
    public static void setUp() {
        mockItemCategory = mock(ItemCategory.class);
        mockServiceCategory = mock(ServiceCategory.class);
        mockItemRepository = mock(ItemRepository.class);
        mockServiceRepository = mock(ServiceRepository.class);
        mockItemCategoryRepository = mock(ItemCategoryRepository.class);
        mockServiceCategoryRepository = mock(ServiceCategoryRepository.class);
        mockUserRepository = mock(UserRepository.class);
        itemCategoryList = new ArrayList<>();
        itemCategoryList.add(mockItemCategory);
        serviceCategoryList = new ArrayList<>();
        serviceCategoryList.add(mockServiceCategory);
        userId = 1;
        mockItemList = new ArrayList<>();
        mockItem = mock(Item.class);
        mockItemList.add(mockItem);
        mockServiceList = new ArrayList<>();
        mockService = mock(Service.class);
        mockServiceList.add(mockService);
        testRentableService = new RentableService(mockServiceRepository, mockItemCategoryRepository, mockServiceCategoryRepository, mockUserRepository, mockItemRepository);
    }

    @Test
    public void testGetAllRentablesReturnsRentable() {
        when(mockItemCategoryRepository.findAll()).thenReturn(itemCategoryList);
        when(mockServiceCategoryRepository.findAll()).thenReturn(serviceCategoryList);
        when(mockItemRepository.findAll()).thenReturn(mockItemList);
        when(mockServiceRepository.findAll()).thenReturn(mockServiceList);
        testMap = testRentableService.getAllRentables(userId);
        assertEquals(mockItem, testMap.get("rentableList").get(0));
    }

    @Test
    public void testGetAllRentablesReturnsCategoryList() {
        when(mockItemCategoryRepository.findAll()).thenReturn(itemCategoryList);
        when(mockServiceCategoryRepository.findAll()).thenReturn(serviceCategoryList);
        when(mockItemRepository.findAll()).thenReturn(mockItemList);
        when(mockServiceRepository.findAll()).thenReturn(mockServiceList);
        testMap = testRentableService.getAllRentables(userId);
        assertEquals(itemCategoryList, testMap.get("itemCategories"));
        assertEquals(serviceCategoryList, testMap.get("serviceCategories"));
    }

    @Test
    public void testGetAllRentablesThrowsError() {
        when(mockItemCategoryRepository.findAll()).thenReturn(itemCategoryList);
        when(mockServiceCategoryRepository.findAll()).thenReturn(serviceCategoryList);
        List<ItemCategory> testEmptyList = new ArrayList<>();
        when(mockItemCategoryRepository.findAll()).thenReturn(testEmptyList);
        testMap = testRentableService.getAllRentables(userId);
        assertThrows(IndexOutOfBoundsException.class, () -> testMap.get("rentableList").get(0));

    }

   /* @Test
    public void testGetUpdateDataWorksCorrectly(){
        String testString = "item_0";
        List<? extends Rentable> fuck = new ArrayList<>();
        fuck.add(mockItem);
        when(mockItemRepository.findByItemCategoryId(any(Integer.class))).thenReturn();
        List<? extends Rentable> testItemList = testRentableService.getUpdatedData(testString);
        assertEquals(mockItem, testItemList.get(0));
    }


    @Test
    public void testGetUpdateDataThrowsError(){
        String testString = "item_3";
        when(mockRentableService.getByItemCategory(any(Integer.class))).thenReturn(mockItemList);
        List<? extends Rentable> testItemList = testRentableService.getUpdatedData(testString);
        assertThrows(IndexOutOfBoundsException.class, () -> testItemList.get(4));
    }*/

}