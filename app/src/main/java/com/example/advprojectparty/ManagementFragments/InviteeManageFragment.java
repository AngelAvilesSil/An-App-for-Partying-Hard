/**
    FILE: InviteeManageFragment
    OVERVIEW:
        This file contains the class definition for the activity fragment for
        the INVITEE MANAGER

        The event manager can add events to the app's internal sqlite database

    FEATURES:
        -A variety of input fields to track people invited to the party
            First and Last name
            RSVP Phone number,
            and Email
        -When the ADD INVITEE button is pressed, it is stored in the app's database
        -There is no way to remove Invitees at the moment
            In the future it would be possible by tapping on the invitee in the invitee List view

        -Data validations makes sure no fields are left blank or invalid
 */
package com.example.advprojectparty.ManagementFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.advprojectparty.DatabaseElements.Invitee;
import com.example.advprojectparty.DatabaseElements.PartyListDatabase;
import com.example.advprojectparty.R;

public class InviteeManageFragment extends Fragment {

    Button insertButton = null;
    EditText invNameInput = null;
    EditText invLastNameInput = null;
    EditText invPhoneInput = null;
    EditText invEmailInput = null;


    public InviteeManageFragment() {
        /** Required empty public constructor */
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_invited_manage,
                container, false);
        insertButton = thisView.findViewById(R.id.button_enter_invitee);
        invNameInput = thisView.findViewById(R.id.name_input);
        invLastNameInput = thisView.findViewById(R.id.lastname_input);
        invPhoneInput = thisView.findViewById(R.id.phone_input);
        invEmailInput = thisView.findViewById(R.id.email_input);

        // The trigger of the action that should be completed in this
        // fragment once the user pushes the button
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theName = invNameInput.getText().toString();
                String theLastname = invLastNameInput.getText().toString();
                String thePhone = invPhoneInput.getText().toString();
                String theEmail = invEmailInput.getText().toString();

                if (!theName.equals("") && !theLastname.equals("")
                && !thePhone.equals("") && !theEmail.equals(""))
                {
                    PartyListDatabase db = PartyListDatabase.getInstance(getContext());
                    db.getInviteeDao().insert(new Invitee(theName,theLastname,thePhone,theEmail));

                    invNameInput.setText(""); invLastNameInput.setText("");
                    invPhoneInput.setText(""); invEmailInput.setText("");

                    Toast t = Toast.makeText(getContext(), "You have a new invitee!", Toast.LENGTH_SHORT);
                    t.show();
                }
                else
                {
                    Toast t = Toast.makeText(getContext(), "Nothing can be empty", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        return thisView;
    }
}