/**
 * FILE: ShopListDao
 *
 * This is the Dao from the shop_list table,
 * it will provide query access when called.
 *
 * At the moment, it possess only one access, and
 * this access gets all the rows from the shop_list table
 */
package com.example.advprojectparty;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShopListDao {
    @Insert
    void insert(ShopList... shopLists);
    @Update
    void update(ShopList... shopLists);
    @Delete
    void delete(ShopList shopList);

    @Query("SELECT * FROM shop_list ORDER BY item")
    List<ShopList> getShopList();
}
