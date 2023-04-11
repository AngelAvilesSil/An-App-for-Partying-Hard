/**
 *   FILE: PartyListDB
 *  OVERVIEW:
 *       This file contains static final definition of variable names, the
 *       variable names will be used in some function definitions/
 *
 *       It contain some other methods which were not used in the app, but are
 *       still useful for future applications and references. These where commented
 *       out, but still present in these files..
 *
 *  REFERENCES
 *        Igor Pustylnick, Retrieved from eConestoga, MAD course Week 7 contents
 *        FILE: DirectAndRoom
 */

package com.example.advprojectparty.DatabaseElements;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class PartyListDB {

    // Database constants
    public static final String DATABASE_NAME = "party.db";
    public static final int DATABASE_VERSION = 1;

    // Invitee table constants
    public static final String INVITEE_TABLE = "invitee_list";

    public static final String INVITEE_ID = "id";
    public static final int    INVITEE_ID_COL = 0;

    public static final String INVITEE_NAME = "name";
    public static final int    INVITEE_NAME_COL = 1;

    public static final String INVITEE_LASTNAME = "last_name";
    public static final int    INVITEE_LASTNAME_COL = 2;

    public static final String INVITEE_PHONE = "phone";
    public static final int    INVITEE_PHONE_COL = 3;

    public static final String INVITEE_EMAIL = "email";
    public static final int    INVITEE_EMAIL_COL = 4;

    // Shop list table constants
    public static final String SHOP_LIST_TABLE = "shop_list";

    public static final String SHOP_LIST_ID = "id";
    public static final int    SHOP_LIST_ID_COL = 0;

    public static final String SHOP_LIST_ITEM = "item";
    public static final int    SHOP_LIST_ITEM_COL = 1;

    public static final String SHOP_LIST_TYPE = "type";
    public static final int    SHOP_LIST_TYPE_COL = 2;

    public static final String SHOP_LIST_QTY = "qty";
    public static final int    SHOP_LIST_QTY_COL = 3;

    public static final String SHOP_LIST_PRIZE = "quot_p";
    public static final int    SHOP_LIST_PRIZE_COL = 4;

    // Event list table constants
    public static final String EVENT_TABLE = "event_list";

    public static final String EVENT_ID = "id";
    public static final int EVENT_ID_COL = 0;

    public static final String EVENT_NAME = "eventname";
    public static final int EVENT_NAME_COL = 1;

    public static final String EVENT_MUSIC = "eventmusic";
    public static final int EVENT_MUSIC_COL = 2;

    public static final String EVENT_FOOD = "event-food";
    public static final int EVENT_FOOD_COL = 3;

    public static final String EVENT_SNACK = "event-snack";
    public static final int EVENT_SNACK_COL = 4;

    public static final String EVENT_ALCOHOL = "event-alcohol";
    public static final int EVENT_ALCOHOL_COL = 5;

    // Create table statements
    public static final String CREATE_INVITEE_TABLE =
            "CREATE TABLE " + INVITEE_TABLE + " (" +
            INVITEE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            INVITEE_NAME + " TEXT NOT NULL, " +
            INVITEE_LASTNAME + " TEXT NOT NULL, " +
            INVITEE_PHONE + " TEXT NOT NULL, " +
            INVITEE_EMAIL + " TEXT NOT NULL);";

    public static final String CREATE_SHOP_LIST_TABLE =
            "CREATE TABLE " + SHOP_LIST_TABLE + " (" +
            SHOP_LIST_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            SHOP_LIST_ITEM + " TEXT NOT NULL, " +
            SHOP_LIST_TYPE + " TEXT NOT NULL, " +
            SHOP_LIST_QTY + " INTEGER NOT NULL, " +
            SHOP_LIST_PRIZE + " REAL NOT NULL);";

    public static final String CREATE_EVENT_TABLE =
            "CREATE TABLE " + EVENT_TABLE + " (" +
            EVENT_ID + "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            EVENT_NAME + " TEXT NOT NULL, " +
            EVENT_MUSIC + " TEXT NOT NULL, " +
            EVENT_FOOD + " TEXT NOT NULL, " +
            EVENT_SNACK + " TEXT NOT NULL, " +
            EVENT_ALCOHOL + " TEXT NOT NULL);";

    // Drop table statements
    public static final String DROP_INVITEE_TABLE =
            "DROP TABLE IF EXISTS " + INVITEE_TABLE;

    public static final String DROP_SHOP_LIST_TABLE =
            "DROP TABLE IF EXISTS " + SHOP_LIST_TABLE;

    public static final String DROP_EVENT_LIST_TABLE =
            "DROP TABLE IF EXISTS " + EVENT_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create tables
            db.execSQL(CREATE_INVITEE_TABLE);
            db.execSQL(CREATE_SHOP_LIST_TABLE);
            db.execSQL(CREATE_EVENT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Task Party", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(PartyListDB.DROP_INVITEE_TABLE);
            db.execSQL(PartyListDB.DROP_SHOP_LIST_TABLE);
            db.execSQL(PartyListDB.DROP_EVENT_LIST_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    // Constructor
    public PartyListDB(Context context) {
        dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* Bellow and all code which is very useful and correctly built,
     * unfortunately, these are not used explicitly in this application
     * though they are kept here for future use and reference */
    /*
    // Private methods
    private void openReadableDB() { database = dbHelper.getReadableDatabase(); }

    private  void openWritableDB() { database = dbHelper.getWritableDatabase(); }

    public void closeDataBase() {
        if (database != null)
            database.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    // Public methods for Invitee table
    public ArrayList<Invitee> getInvitees() {
        ArrayList<Invitee> invitees = new ArrayList<Invitee>();

        try {
            openReadableDB();
            Cursor cursor = database.query(INVITEE_TABLE,
                    null,null,null,
                    null,null,INVITEE_LASTNAME);
            while (cursor.moveToNext()) {
                Invitee invitee = new Invitee();
                invitee.setId(cursor.getInt(INVITEE_ID_COL));
                invitee.setName(cursor.getString(INVITEE_NAME_COL));
                invitee.setLastName(cursor.getString(INVITEE_LASTNAME_COL));
                invitee.setPhone(cursor.getString(INVITEE_PHONE_COL));
                invitee.setEmail(cursor.getString(INVITEE_EMAIL_COL));

                invitees.add(invitee);
            }
            closeCursor(cursor);
            closeDataBase();

            return invitees;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ShopList> getShopLists() {
        ArrayList<ShopList> shopLists = new ArrayList<ShopList>();

        try {
            openReadableDB();
            Cursor cursor = database.query(SHOP_LIST_TABLE, null,
                    null,null,null,null,SHOP_LIST_TYPE);
            while (cursor.moveToNext()) {
                ShopList shopList = new ShopList();
                shopList.setId(cursor.getInt(SHOP_LIST_ID_COL));
                shopList.setItem(cursor.getString(SHOP_LIST_ITEM_COL));
                shopList.setType(cursor.getString(SHOP_LIST_TYPE_COL));
                shopList.setQty(cursor.getInt(SHOP_LIST_QTY_COL));
                shopList.setQ_prize(cursor.getFloat(SHOP_LIST_PRIZE_COL));

                shopLists.add(shopList);
            }
            closeCursor(cursor);
            closeDataBase();

            return shopLists;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Event> getEventList() {
        ArrayList<Event> events = new ArrayList<Event>();

        try {
            openReadableDB();
            Cursor cursor = database.query(EVENT_TABLE, null,
                    null,null,null,null,EVENT_NAME);
            while (cursor.moveToNext()) {
                Event event = new Event();
                event.setId(cursor.getInt(EVENT_ID_COL));
                event.setEvent(cursor.getString(EVENT_NAME_COL));
                event.setMusic(cursor.getString(EVENT_MUSIC_COL));
                event.setFood(cursor.getString(EVENT_FOOD_COL));
                event.setSnack(cursor.getString(EVENT_SNACK_COL));
                event.setAlcohol(cursor.getString(EVENT_ALCOHOL_COL));

                events.add(event);
            }
            closeCursor(cursor);
            closeDataBase();

            return events;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Invitee getInvite(String name, String lastName) {
        String where = INVITEE_NAME + "= ? AND " +
                INVITEE_LASTNAME + "= ?";
        String[] whereArgs = { name, lastName };

        try {
            openReadableDB();
            Cursor cursor = database.query(INVITEE_TABLE, null,
                    where,whereArgs,null,null,null);
            Invitee invitee = null;
            cursor.moveToFirst();
            invitee = new Invitee(cursor.getInt(INVITEE_ID_COL), cursor.getString(INVITEE_NAME_COL),
                    cursor.getString(INVITEE_LASTNAME_COL), cursor.getString(INVITEE_PHONE_COL),
                    cursor.getString(INVITEE_EMAIL_COL));
            this.closeCursor(cursor);
            this.closeDataBase();

            return invitee;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public ShopList getShopList(String item, String type) {
        String where = SHOP_LIST_ITEM + "= ? AND " +
                SHOP_LIST_TYPE + "= ?";
        String[] whereArgs = { item, type };

        try {
            openReadableDB();
            Cursor cursor = database.query(SHOP_LIST_TABLE, null,
                    where,whereArgs,null,null,null);
            ShopList shopList = null;
            cursor.moveToFirst();
            shopList = new ShopList(cursor.getInt(SHOP_LIST_ID_COL), cursor.getString(SHOP_LIST_ITEM_COL),
                    cursor.getString(SHOP_LIST_TYPE_COL), cursor.getInt(SHOP_LIST_QTY_COL),
                    cursor.getFloat(SHOP_LIST_PRIZE_COL));
            this.closeCursor(cursor);
            this.closeDataBase();

            return shopList;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long insertInvitee(Invitee invitee) {
        ContentValues cv = new ContentValues();
        cv.put(INVITEE_ID, invitee.getId());
        cv.put(INVITEE_NAME, invitee.getName());
        cv.put(INVITEE_LASTNAME, invitee.getLastName());
        cv.put(INVITEE_PHONE, invitee.getPhone());
        cv.put(INVITEE_LASTNAME, invitee.getEmail());

        this.openReadableDB();
        long rowID = database.insert(INVITEE_TABLE,null, cv);
        this.closeDataBase();

        return rowID;
    }

    public long insertShopList(ShopList shopList) {
        ContentValues cv = new ContentValues();
        cv.put(SHOP_LIST_ID, shopList.getId());
        cv.put(SHOP_LIST_ITEM, shopList.getItem());
        cv.put(SHOP_LIST_TYPE, shopList.getType());
        cv.put(SHOP_LIST_QTY, shopList.getQty());
        cv.put(SHOP_LIST_PRIZE, shopList.getQ_prize());

        this.openWritableDB();
        long rowID = database.insert(SHOP_LIST_TABLE, null, cv);
        this.closeDataBase();

        return rowID;
    }

    public long insertEvent(Event event) {
        ContentValues cv = new ContentValues();
        cv.put(EVENT_ID, event.getId());
        cv.put(EVENT_NAME, event.getEvent());
        cv.put(EVENT_MUSIC, event.getMusic());
        cv.put(EVENT_FOOD, event.getFood());
        cv.put(EVENT_SNACK, event.getSnack());
        cv.put(EVENT_ALCOHOL, event.getAlcohol());

        this.openWritableDB();
        long rowID = database.insert(EVENT_TABLE, null, cv);
        this.closeDataBase();

        return rowID;
    }

    public int updateInvitee(Invitee invitee) {
        ContentValues cv = new ContentValues();
        cv.put(INVITEE_ID, invitee.getId());
        cv.put(INVITEE_NAME, invitee.getName());
        cv.put(INVITEE_LASTNAME, invitee.getLastName());
        cv.put(INVITEE_PHONE, invitee.getPhone());
        cv.put(INVITEE_LASTNAME, invitee.getEmail());

        String where = INVITEE_ID + "= ?";
        String[] whereArgs = { String.valueOf(invitee.getId()) };

        this.openWritableDB();
        int rowCount = database.update(INVITEE_TABLE, cv, where, whereArgs);
        this.closeDataBase();

        return rowCount;
    }

    public int updateShopList(ShopList shopList) {
        ContentValues cv = new ContentValues();
        cv.put(SHOP_LIST_ID, shopList.getId());
        cv.put(SHOP_LIST_ITEM, shopList.getItem());
        cv.put(SHOP_LIST_TYPE, shopList.getType());
        cv.put(SHOP_LIST_QTY, shopList.getQty());
        cv.put(SHOP_LIST_PRIZE, shopList.getQ_prize());

        String where = SHOP_LIST_ID + "= ?";
        String[] whereArgs = { String.valueOf(shopList.getId()) };

        this.openWritableDB();
        int rowCount = database.update(SHOP_LIST_TABLE, cv, where, whereArgs);
        this.closeDataBase();

        return  rowCount;
    }
    
    public int updateEvent(Event event) {
        ContentValues cv = new ContentValues();
        cv.put(EVENT_ID, event.getId());
        cv.put(EVENT_NAME, event.getEvent());
        cv.put(EVENT_MUSIC, event.getMusic());
        cv.put(EVENT_FOOD, event.getFood());
        cv.put(EVENT_SNACK,event.getSnack());
        cv.put(EVENT_ALCOHOL, event.getAlcohol());
        
        String where = EVENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(event.getId()) };
        
        this.openWritableDB();
        int rowCount = database.update(EVENT_TABLE, cv, where,whereArgs);
        this.closeDataBase();

        return rowCount;
    }

    public int deleteInvitee(int id) {
        String where = INVITEE_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = database.delete(INVITEE_TABLE, where, whereArgs);
        this.closeDataBase();

        return rowCount;
    }

    public int deleteShopList(int id) {
        String where = SHOP_LIST_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();
        int rowCount = database.delete(SHOP_LIST_TABLE, where, whereArgs);
        this.closeDataBase();

        return rowCount;
    }

    public int deleteEvent(int id) {
        String where = EVENT_ID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWritableDB();;
        int rowCount = database.delete(EVENT_TABLE, where, whereArgs);
        this.closeDataBase();

        return rowCount;
    }
    */
}
