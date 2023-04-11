/**
 * FILE: InviteDao
 *
 * This is the Dao from the invite_list table,
 * it will provide query access when called.
 *
 * At the moment, it possess only one access, and
 * this access gets all the rows from the invite_list table
 */
package com.example.advprojectparty.DatabaseElements;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InviteeDao {
    @Insert
    void insert(Invitee... invitees);
    @Update
    void update(Invitee... invitees);
    @Delete
    void delete(Invitee invitee);

    @Query("SELECT * FROM invitee_list ORDER BY last_name")
    List<Invitee> getInvitees();
}
