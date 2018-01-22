package com.example.tlin7877.assignment_1.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tlin7877.assignment_1.entity.Drink;
import com.example.tlin7877.assignment_1.entity.User;

import java.util.List;

/**
 * Created by tlin7877 on 12/14/2017.
 */

@Dao
public interface DrinkDao {
    @Query("SELECT * FROM Drink")
    List<Drink> getAll();

    @Query("SELECT * FROM Drink WHERE DrinkID LIKE :drinkID LIMIT 1")
    Drink findByDrinkID(int drinkID);

    @Query("SELECT Name from Drink WHERE DrinkID LIKE :drinkID LIMIT 1")
    String getDrinkName(int drinkID);

    @Query("SELECT COUNT(*) from Drink")
    int countDrinks();

    @Update
    void update(Drink drink);

    @Insert
    void insert(Drink drink);

    @Insert
    void insertAll(List<Drink> drinks);

    @Delete
    void delete(Drink drink);
}
