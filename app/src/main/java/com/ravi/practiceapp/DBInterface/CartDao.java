package com.ravi.practiceapp.DBInterface;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ravi.practiceapp.DBentities.CartProducts;

import java.util.List;

/**
 * Created by ravi on 01/10/17.
 */

@Dao
public interface CartDao {

    @Insert
    void insertSingleRecord(CartProducts cartProducts);

    @Query("SELECT * FROM CartProducts")
    LiveData<List<CartProducts>> fetchAllCart();

    @Delete
    int deleteRecords(CartProducts cartProducts);
}
