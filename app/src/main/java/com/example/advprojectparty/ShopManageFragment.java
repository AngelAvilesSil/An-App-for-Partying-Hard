/**
 *   FILE: EventManageFragment
 *   OVERVIEW:
 *       This file contains the class definition for the activity fragment for
 *       the SHOPPING MANAGER
 *
 *       The shopping manager can add items to the party shopping list in the internal app database
 *
 *   FEATURES:
 *       -A variety of input fields to catalogue:
 *           Item name
 *           Type (could be used in future improvements for sorting and event integration)
 *           Quantity, and
 *           Estimated cost
 *
 *       -Pressing the ENTER ITEM button will add these details to the Shopping List Summary View
 *       -Data validations makes sure no fields are left blank or invalid
 */

package com.example.advprojectparty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ShopManageFragment extends Fragment {

    Button addItemButton = null;
    EditText shopItemInput = null;
    Spinner typeSpinner = null;
    EditText shopQtyInput = null;
    EditText shopPrzInput = null;


    ArrayAdapter<CharSequence> typesAdapter = null;

    public ShopManageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflating the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_shop_manage, container, false);

        // Obtaining the widgets
        addItemButton = thisView.findViewById(R.id.button_enter_shop);
        shopItemInput = thisView.findViewById(R.id.item_input);
        typeSpinner = thisView.findViewById(R.id.type_spinner_input_old);
        shopQtyInput = thisView.findViewById(R.id.qty_input);
        shopPrzInput = thisView.findViewById(R.id.prize_input);

        // Setting an adapter for the spinner containing the types
        typesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.type_of_shopping,
                R.layout.spinner_item);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typesAdapter);

        // Strings to get the content from the spinner selection
        String[] theTypes = getResources().getStringArray(R.array.type_of_shopping);
        String[] theType = {""};

        // Getting what is contained in the spinner
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                theType[0] = theTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // The trigger of the action that should be completed in this
        // fragment once the user pushes the button
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theItem = shopItemInput.getText().toString();
                String theQty = shopQtyInput.getText().toString();
                String thePrize = shopPrzInput.getText().toString();

                if (!theItem.equals("") && !theQty.equals("") && !thePrize.equals("")) {
                    PartyListDatabase db = PartyListDatabase.getInstance(getContext());
                    db.getShopDao().insert(new ShopList(theItem,theType[0],Integer.parseInt(theQty),Float.parseFloat(thePrize)));

                    shopItemInput.setText(""); shopQtyInput.setText(""); shopPrzInput.setText("");

                    Toast t = Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Toast t = Toast.makeText(getContext(), "Nothing can be empty", Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });

        // Returning the previously inflated layout
        return thisView;
    }
}