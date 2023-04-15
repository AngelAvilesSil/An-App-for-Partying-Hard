/**
    FILE: SummaryEventFragment
    OVERVIEW:
        This file contains the class definition for the
        activity fragment that displays the list of EVENTS that will take place at the party

        It gets it's display data from the database room that manages the app's SQlite Database
        It is empty and does not display any items until added from the EventManageFragment screen

        The items can be scrolled and viewed, but at the moment that is all
 */
package com.example.advprojectparty.SummaryFragments;

import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.Fragment;

import com.example.advprojectparty.DatabaseElements.Event;
import com.example.advprojectparty.DatabaseElements.PartyListDB;
import com.example.advprojectparty.DatabaseElements.PartyListDatabase;
import com.example.advprojectparty.R;

import java.util.ArrayList;

public class SummaryEventFragment extends Fragment {

    ListView myEventList = null;

    public SummaryEventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PartyListDatabase db = PartyListDatabase.getInstance(getContext());
        ArrayList<Event> dummyEvent = new ArrayList<>(db.getEventDao().getEvents());

        // Starting to inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_summary_event, container, false);
        myEventList = thisView.findViewById(R.id.eventList);
        myEventList.setAdapter(setEventScreen(dummyEvent));
        getEventsFromRoom();
        // Inflated the layout for this fragment
        return thisView;
    }
    public SimpleCursorAdapter setEventScreen(ArrayList<Event> events) {

        String[] columns = {
                PartyListDB.EVENT_NAME, PartyListDB.EVENT_MUSIC,
                PartyListDB.EVENT_FOOD, PartyListDB.EVENT_SNACK,
                PartyListDB.EVENT_ALCOHOL
        };
        int[] to = {
                R.id.eventName, R.id.eventMusic, R.id.eventFood,
                R.id.eventSnack, R.id.eventAlcohol
        };
        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        "_id", PartyListDB.EVENT_NAME, PartyListDB.EVENT_MUSIC,
                        PartyListDB.EVENT_FOOD, PartyListDB.EVENT_SNACK,
                        PartyListDB.EVENT_ALCOHOL
                }
        );
        for (Event event : events) {
            cursor.newRow()
                    .add("_id",0)
                    .add(PartyListDB.EVENT_NAME, event.getEvent())
                    .add(PartyListDB.EVENT_MUSIC, event.getMusic())
                    .add(PartyListDB.EVENT_FOOD, event.getFood())
                    .add(PartyListDB.EVENT_SNACK, event.getSnack())
                    .add(PartyListDB.EVENT_ALCOHOL, event.getAlcohol());
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(), R.layout.event_list_item,cursor,columns,to,0
        );

        return adapter;
    }

    private void getEventsFromRoom() {
        PartyListDatabase database = PartyListDatabase.getInstance(getContext());
        new PopulateEvent().execute(database);
    }

    private class PopulateEvent extends AsyncTask<PartyListDatabase, Void, PartyListDatabase> {

        @Override
        protected PartyListDatabase doInBackground(PartyListDatabase... d) {
            PartyListDatabase db = d[0];
            if (db != null) {
                db.getEventDao().getEvents();
            }
            return db;
        }
    }
}