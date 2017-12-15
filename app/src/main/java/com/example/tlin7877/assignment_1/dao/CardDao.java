package com.example.tlin7877.assignment_1.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tlin7877.assignment_1.entity.Card;
import com.example.tlin7877.assignment_1.entity.User;

import java.util.List;

/**
 * Created by tlin7877 on 12/14/2017.
 */

@Dao
public interface CardDao {
    @Query("SELECT * FROM Card")
    List<Card> getAll();

    @Query("SELECT * FROM Card WHERE CardNumber LIKE :cardNum LIMIT 1")
    Card findByCardNumber(int cardNum);

    @Query("SELECT COUNT(*) from Card")
    int countCards();

    @Update
    void update(Card card);

    @Insert
    void insert(Card card);

    @Insert
    void insertAll(List<Card> cards);

    @Delete
    void delete(Card card);
}
