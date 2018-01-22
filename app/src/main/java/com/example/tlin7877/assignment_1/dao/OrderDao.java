package com.example.tlin7877.assignment_1.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tlin7877.assignment_1.entity.Order;
import com.example.tlin7877.assignment_1.entity.User;

import java.util.List;

/**
 * Created by tlin7877 on 12/14/2017.
 */

@Dao
public interface OrderDao {
    @Query("SELECT * FROM Order_T")
    List<Order> getAll();

    @Query("SELECT DrinkID FROM Order_T WHERE UserEmail LIKE :userEmail")
    List<Integer> getAllDrinkIDs(String userEmail);

    @Query("SELECT Quantity FROM Order_T WHERE UserEmail LIKE :userEmail")
    List<Integer> getAllQuantitys(String userEmail);

    @Query("SELECT * FROM Order_T WHERE OrderID LIKE :orderID LIMIT 1")
    Order findByOrderID(int orderID);

    @Query("SELECT COUNT(*) from Order_T")
    int countOrders();

    @Update
    void update(Order order);

    @Insert
    void insert(Order order);

    @Insert
    void insertAll(List<Order> orders);

    @Delete
    void delete(Order order);
}
