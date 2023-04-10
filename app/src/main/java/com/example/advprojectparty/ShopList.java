/**
 *   FILE: ShopList
 *  OVERVIEW:
 *       This file contains the class definition for the ShopList object, this one
 *       is used in conjunction with its DAO pair to represent a record in the
 *       shop_list table.
 *
 *
 *   FEATURES:
 *       -Accessors and setters for all the fields in the table
 *           id, item, type, qtr, and q_prize (wrong word intentionally)
 *       -This class will be created every time a row from the shop_list table is entered.
 *
 */
package com.example.advprojectparty;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="shop_list")
public class ShopList {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name="item")
    private String item;
    @NonNull
    private String type;
    private int qty;
    private float q_prize;

    // default constructor
    public ShopList() {
        item = "";
        type = "";
    }

    // Custom constructors
    public ShopList(@NonNull String item, @NonNull String type,
                    int qty, float q_prize) {
        this.item = item; this.type = type; this.qty = qty;
        this.q_prize = q_prize;
    }
    public ShopList(int id, @NonNull String item, @NonNull String type,
                    int qty, float q_prize) {
        this.id = id; this.item = item; this.type = type; this.qty = qty;
        this.q_prize = q_prize;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setItem(@NonNull String item) {
        this.item = item;
    }
    @NonNull
    public String getItem() {
        return item;
    }
    public void setType(@NonNull String type) {
        this.type = type;
    }
    @NonNull
    public String getType() {
        return type;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getQty() {
        return qty;
    }
    public void setQ_prize(float q_prize) {
        this.q_prize = q_prize;
    }
    public float getQ_prize() {
        return q_prize;
    }
}
