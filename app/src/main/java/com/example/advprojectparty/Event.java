/**
 *   FILE: Event
 *  OVERVIEW:
 *       This file contains the class definition for the Event object, this one
 *       is used in conjunction with its DAO pair to represent a record in the
 *       event_list table.
 *
 *
 *   FEATURES:
 *       -Accessors and setters for all the fields in the table
 *           id, event, music, food, snack, and alcohol
 *       -This class will be created every time a row from the event_list table is entered.
 *
 */
package com.example.advprojectparty;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "event_list")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NotNull
    @ColumnInfo(name = "event_name")
    private String event;
    @NotNull
    private String music;
    @NotNull
    private String food;
    @NotNull
    private String snack;
    @NotNull
    private String alcohol;

    // Default constructor
    public Event() {
        this.event = ""; this.music = "No"; this.food = "No";
        this.snack = "No"; this.alcohol = "No";
    }

    // Some custom constructors
    public Event(@NotNull String event,  @NotNull String music,
            @NotNull String food, @NotNull String snack, @NotNull String alcohol) {
        this.event = event; this.music = music; this.food = food; this.snack = snack;
        this. alcohol = alcohol;
    }
    public Event(int id, @NotNull String event, @NotNull String music,
                 @NotNull String food, @NotNull String snack, @NotNull String alcohol) {
        this.id = id; this.event = event; this.music = music; this.food = food;
        this.snack = snack; this.alcohol = alcohol;
    }

    // Accessors and setters
    public void setId(int id) { this.id = id; }
    public int getId() {
        return this.id;
    }
    public void setEvent(@NotNull String event) {
        this.event = event;
    }
    @NonNull
    public String getEvent() {
        return this.event;
    }
    public void setMusic(String music) {
        this.music = music;
    }
    public String getMusic() {
        return this.music;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public String getFood() {
        return this.food;
    }
    public void setSnack(String snack) {
        this.snack = snack;
    }
    public String getSnack() {
        return this.snack;
    }
    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }
    public String getAlcohol() {
        return this.alcohol;
    }
}
