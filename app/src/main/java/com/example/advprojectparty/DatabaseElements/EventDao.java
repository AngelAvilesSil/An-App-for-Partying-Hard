/**
 * FILE: EventDap
 *
 * This is the Dao from the event_list table,
 * it will provide query access when called.
 *
 * At the moment, it possess only one access, and
 * this access gets all the rows from the event_list table
 */

package com.example.advprojectparty.DatabaseElements;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(Event... events);
    @Update
    void update(Event... events);
    @Delete
    void delete(Event event);

    @Query("SELECT * FROM event_list")
    List<Event> getEvents();
}
