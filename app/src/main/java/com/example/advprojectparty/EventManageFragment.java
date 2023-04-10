/**
 *   FILE: EventManageFragment
 *  OVERVIEW:
 *       This file contains the class definition for the activity fragment for
 *       the EVENT MANAGER
 *
 *       The event manager can add events to the app's internal sqlite database
 *
 *   FEATURES:
 *       -A variety of input fields to define events at the party
 *           event name
 *           music played
 *           Whether Food Snacks, and Alcohol should be expected
 *       -When the ADD EVENT button is pressed, it is stored in the app's database
 *       -There is no way to remove or edit events at the moment
 *           In the future it would be possible by tapping on the event in the event summary view
 *
 *      -Data validations makes sure no fields are left blank or invalid
 */
package com.example.advprojectparty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class EventManageFragment extends Fragment {

    Button addEventButton = null;
    EditText eventNameInput = null;
    Switch eventMusicInput = null;
    CheckBox eventFoodInput = null;
    CheckBox eventSnackInput = null;
    CheckBox eventAlcoholInput = null;

    public EventManageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Starting to inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_event_manage, container, false);

        // Obtaining the widgets
        addEventButton = thisView.findViewById(R.id.button_enter_event);
        eventNameInput = thisView.findViewById(R.id.event_input);
        eventMusicInput = thisView.findViewById(R.id.music_switch);
        eventFoodInput = thisView.findViewById(R.id.food_input);
        eventSnackInput = thisView.findViewById(R.id.snack_input);
        eventAlcoholInput = thisView.findViewById(R.id.alcohol_input);
        eventMusicInput.setText(R.string.music_off);

        String[] theMusic = { "" };

        // Getting the current state of the switch widget
        eventMusicInput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    theMusic[0] = "Playing music";
                    eventMusicInput.setText(R.string.music_on);
                }
                else {
                    theMusic[0] = "Not playing music";
                    eventMusicInput.setText(R.string.music_off);
                }
            }
        });

        // The trigger of the action that should be completed in this
        // fragment once the user pushes the button
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieving everything what was input by the user
                String theFood = "";
                String theSnack = "";
                String theAlcohol = "";
                String theName = eventNameInput.getText().toString();
                if (eventFoodInput.isChecked()) {
                    theFood = "Serving food";
                } else {
                    theFood = "Not serving food";
                }
                if (eventSnackInput.isChecked()) {
                    theSnack = "Serving snacks";
                } else {
                    theSnack = "Not serving snacks";
                }
                if (eventAlcoholInput.isChecked()) {
                    theAlcohol = "Serving alcohol";
                } else {
                    theAlcohol = "Not serving alcohol";
                }

                if (!theName.equals("")) {
                    PartyListDatabase db = PartyListDatabase.getInstance(getContext());
                    db.getEventDao().insert(new Event(theName, theMusic[0], theFood,
                            theSnack, theAlcohol));

                    eventNameInput.setText(""); eventMusicInput.setChecked(false);
                    eventFoodInput.setChecked(false); eventSnackInput.setChecked(false);
                    eventAlcoholInput.setChecked(false);

                    Toast t = Toast.makeText(getContext(), "Event added", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Toast t = Toast.makeText(getContext(), "Nothing can be empty", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        // The view was previously inflated, so let just return it
        return thisView;
        }
}