package com.example.tlin7877.assignment_1.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tlin7877.assignment_1.entity.User;

import java.util.List;

/**
 * Created by tlin7877 on 12/14/2017.
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE Email LIKE :userEmail LIMIT 1")
    User findByEmail(String userEmail);

    @Query("SELECT COUNT(*) from User")
    int countUsers();

    @Update
    void update(User user);

    @Insert
    void insert(User user);

    @Insert
    void insertAll(List<User> users);

    @Delete
    void delete(User user);
}
