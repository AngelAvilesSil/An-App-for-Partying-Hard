/**
 *   FILE: Invitee
 *  OVERVIEW:
 *       This file contains the class definition for the Invitee object, this one
 *       is used in conjunction with its DAO pair to represent a record in the
 *       invitee_list table.
 *
 *
 *   FEATURES:
 *       -Accessors and setters for all the fields in the table
 *           id, name, lastName, phone, and email
 *       -This class will be created every time a row from the invite_list table is entered.
 *
 */
package com.example.advprojectparty;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="invitee_list")
public class Invitee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    @NonNull
    @ColumnInfo(name="last_name")
    private String lastName;
    @NonNull
    private String phone;
    @NonNull
    private String email;

    public Invitee() {
        name = "";
        lastName = "";
        phone = "";
        email = "";
    }
    @Ignore
    public Invitee(@NonNull String name, @NonNull String lastName,
                   @NonNull String phone, @NonNull String email)
    {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public Invitee(int id, @NonNull String name, @NonNull String lastName,
                   @NonNull String phone, @NonNull String email)
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    @NonNull
    public String getName() {
        return name;
    }
    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }
    @NonNull
    public String getLastName() {
        return lastName;
    }
    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }
    @NonNull
    public String getPhone() {
        return phone;
    }
    public void setEmail(@NonNull String email) {
        this.email = email;
    }
    @NonNull
    public String getEmail() {
        return email;
    }

}
