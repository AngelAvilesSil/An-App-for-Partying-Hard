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

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
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
import com.example.advprojectparty.StartScreen;

import java.util.Locale;

public class InviteeManageFragment extends Fragment {

    private static String INVITE_UPDATE = "invite_update";
    Button insertButton = null;
    Button contactButton = null;
    EditText invNameInput = null;
    EditText invLastNameInput = null;
    EditText invPhoneInput = null;
    EditText invEmailInput = null;
    Boolean contactsPermitted = false;


    public InviteeManageFragment() {
        /* Required empty public constructor */
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
        contactButton = thisView.findViewById(R.id.button_contacts_invitee);
        invNameInput = thisView.findViewById(R.id.name_input);
        invLastNameInput = thisView.findViewById(R.id.lastname_input);
        invPhoneInput = thisView.findViewById(R.id.phone_input);
        invEmailInput = thisView.findViewById(R.id.email_input);
        contactsPermitted = StartScreen.getGrantedContacts();

        if (contactsPermitted.equals(false)) {
            contactButton.setVisibility(View.INVISIBLE);
        }

        // The trigger actions that should add a single contact
        // entered manually in the invitee list
        insertButton.setOnClickListener(v -> {
            String theName = invNameInput.getText().toString();
            String theLastname = invLastNameInput.getText().toString();
            String countryCode = Locale.getDefault().getCountry();
            String thePhone = PhoneNumberUtils.formatNumber(invPhoneInput.getText().toString(), countryCode);
            if (thePhone == null) { thePhone = "Invalid number"; }
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

                Intent intent = new Intent(INVITE_UPDATE);
                // broadcast the completion
                getActivity().sendBroadcast(intent);
            }
            else
            {
                Toast t = Toast.makeText(getContext(), "Nothing can be empty", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        // The trigger of the actions that should add all the contacts
        // from the phone in the invitee list
        contactButton.setOnClickListener(v -> getContacts());

        return thisView;
    }

    public void getContacts() {
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        Uri CONTACTS_URI = ContactsContract.Data.CONTENT_URI;
        String CONTACTS_MIME = ContactsContract.Data.MIMETYPE;
        String _ID = ContactsContract.Contacts._ID;
        String NAME_ID = ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID;
        String GIVEN_NAME = ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME;
        String FAMILY_NAME = ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String EMAIL = ContactsContract.CommonDataKinds.Email.DATA;
        // getting the content resolver is a bit different, since we are in a fragment
        Context applicationContext = StartScreen.getContextOfApplication();
        ContentResolver contentResolver = applicationContext.getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null,
                                    null,null);

        // Loop for all contacts in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndexOrThrow(_ID));
                String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, contact_id };
                Cursor nameCursor = contentResolver.query(CONTACTS_URI, null, CONTACTS_MIME + " = ? AND " + NAME_ID + " = ?",
                                                            whereNameParams, GIVEN_NAME);
                nameCursor.moveToFirst();
                String name = nameCursor.getString(nameCursor.getColumnIndexOrThrow(GIVEN_NAME));
                String lastName = nameCursor.getString(nameCursor.getColumnIndexOrThrow(FAMILY_NAME));
                nameCursor.close();

                String phoneNumber = "";
                String email = "";
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    // Getting the first phone number from the contact,
                    // it will be the main phone number which we are
                    // supposing it is the first one
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
                                                                new String[] { contact_id }, null);
                    // We are taking the immediate
                    phoneCursor.moveToFirst();
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(NUMBER));
                    if (phoneNumber.equals("")) { phoneNumber = "No phone number"; }
                    phoneCursor.close();

                    // Getting the first phone number from the contact,
                    // it will be the main phone number which we are
                    // supposing it is the first one
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?",
                                                                new String[] { contact_id }, null);
                    // We are taking the immediate
                    emailCursor.moveToFirst();
                    email = emailCursor.getString(emailCursor.getColumnIndexOrThrow(EMAIL));
                    if (email.equals("")) { email = "No email"; }
                    emailCursor.close();
                }
                PartyListDatabase db = PartyListDatabase.getInstance(getContext());
                db.getInviteeDao().insert(new Invitee(name,lastName,phoneNumber,email));
            }
            cursor.close();
        }
    }
}