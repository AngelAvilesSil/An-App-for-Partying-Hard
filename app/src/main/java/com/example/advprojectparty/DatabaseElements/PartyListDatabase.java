/**
 *   FILE: PartyListDataBase
 *  OVERVIEW:
 *       This file contains the abstract class definition of the PartyListDatabase
 *       This database will define the database file used in this app, as well as
 *       the abstract getters from the tables contained in the database.
 *
 *       The event manager can add events to the app's internal sqlite database
 *  REFERENCES
 *        Igor Pustylnick, Retrieved from eConestoga, MAD course Week 7 contents
 *        FILE: DirectAndRoom
 */

package com.example.advprojectparty.DatabaseElements;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Invitee.class, ShopList.class, Event.class}, version = 1)
public abstract class PartyListDatabase extends RoomDatabase {
    private static PartyListDatabase instance = null;
    public abstract InviteeDao getInviteeDao(); // Getting all rows from invitee_list table
    public abstract ShopListDao getShopDao();   // Getting all rows from shopping_list table
    public abstract EventDao getEventDao();     // Getting all rows from event_list table

    public static synchronized PartyListDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,PartyListDatabase.class,"party.db")
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
}
