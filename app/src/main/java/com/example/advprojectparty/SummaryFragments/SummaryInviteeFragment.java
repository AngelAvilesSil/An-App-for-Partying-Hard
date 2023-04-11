/**
    FILE: SummaryInviteeFragment
    OVERVIEW:
        This file contains the class definition for the
        activity fragment that displays the list of ATTENDEES to the party

        It gets it's display data from the database room that manages the app's SQlite Database
        It is empty and does not display any items until added from the InviteeManageFragment screen

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

import com.example.advprojectparty.DatabaseElements.Invitee;
import com.example.advprojectparty.DatabaseElements.PartyListDB;
import com.example.advprojectparty.DatabaseElements.PartyListDatabase;
import com.example.advprojectparty.R;

import java.util.ArrayList;

public class SummaryInviteeFragment extends Fragment {

    ListView myInviteeList = null;


    public SummaryInviteeFragment() {
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
        ArrayList<Invitee> dummyInvite = new ArrayList<>(db.getInviteeDao().getInvitees());

        // Start inflating the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_summary_invitee_screen, container, false);
        myInviteeList = thisView.findViewById(R.id.inviteeList);
        setInviteeScreen(dummyInvite);
        getInviteesFromRoom();
        // Inflated previously the layout for this fragment, returning it
        return thisView;
    }

    private void setInviteeScreen(ArrayList<Invitee> invitees) {

        String[] columns = {
                PartyListDB.INVITEE_NAME, PartyListDB.INVITEE_LASTNAME,
                PartyListDB.INVITEE_PHONE, PartyListDB.INVITEE_EMAIL
        };
        int[] to = {
                R.id.inviteeName, R.id.inviteeLastName,
                R.id.inviteePhone, R.id.inviteeEmail
        };
        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        "_id", PartyListDB.INVITEE_NAME, PartyListDB.INVITEE_LASTNAME,
                        PartyListDB.INVITEE_PHONE, PartyListDB.INVITEE_EMAIL
                }
        );
        for (Invitee invitee : invitees) {
            cursor.newRow()
                    .add("_id",0)
                    .add(PartyListDB.INVITEE_NAME, invitee.getName())
                    .add(PartyListDB.INVITEE_LASTNAME, invitee.getLastName())
                    .add(PartyListDB.INVITEE_PHONE, invitee.getPhone())
                    .add(PartyListDB.INVITEE_EMAIL, invitee.getEmail());
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(), R.layout.invite_list_item,cursor,columns,to,0
        );

        myInviteeList.setAdapter(adapter);
    }

    private void getInviteesFromRoom() {
        PartyListDatabase database = PartyListDatabase.getInstance(getContext());
        new PopulateInvitee().execute(database);
    }

    private class PopulateInvitee extends AsyncTask<PartyListDatabase, Void, PartyListDatabase> {

        @Override
        protected PartyListDatabase doInBackground(PartyListDatabase... d) {
            PartyListDatabase db = d[0];
            if (db != null) {
                db.getInviteeDao().getInvitees();
            }
            return db;
        }
    }
}