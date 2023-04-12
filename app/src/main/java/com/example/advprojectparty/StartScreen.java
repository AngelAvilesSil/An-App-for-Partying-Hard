/**
 *   FILE: StartScreen
 *  OVERVIEW:
 *       This file contains the only an activity in all the app, its only function is
 *       holding the menu for all the app and inflating start the fragments when
 *       they are called by the menu.
 *
 */

package com.example.advprojectparty;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;

import com.example.advprojectparty.DatabaseElements.PartyListDB;
import com.example.advprojectparty.ManagementFragments.EventManageFragment;
import com.example.advprojectparty.ManagementFragments.InviteeManageFragment;
import com.example.advprojectparty.ManagementFragments.ShopManageFragment;
import com.example.advprojectparty.SummaryFragments.SummaryEventFragment;
import com.example.advprojectparty.SummaryFragments.SummaryInviteeFragment;
import com.example.advprojectparty.SummaryFragments.SummaryShoppingFragment;

import java.util.List;
import java.util.Objects;

public class StartScreen extends AppCompatActivity {

    PartyListDB database = null;
    private static boolean grantedContacts = false;
    private static Context contextOfApplication;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    // Accessor that will serve as a switch for the
    // contacts addition
    public static boolean getGrantedContacts(){
        return grantedContacts;
    }
    public static Context getContextOfApplication() { return contextOfApplication; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        // This is the title bar which will contain the name of the
        // fragment in every section of the app
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.start_title));
        contextOfApplication = getApplicationContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            grantedContacts = true;
        }

        // This should pull the database or create it
        // with all its components if it does not exists
        database = new PartyListDB(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    grantedContacts = true;
                } else {
                    // permission denied, nothing else to do
                    // actually, the bool is the only thing that
                    // will permit the button to add the contacts
                    // to work, otherwise it will do nothing
                    Log.d("My App", "permission denied");
                }
            }
        }
    }

    /* This is what will inflate the menu from this activity */
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.party_menu, menu);
        MenuBuilder m = (MenuBuilder)menu;
        m.setOptionalIconsVisible(true);
        return true;
    }

    /* This will handle the action on every selection from the menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // From here and below all the fragment are called as per
        // choice from the menu.
        List<Fragment> listFrag;
        switch (item.getItemId()) {
            case R.id.menu_invided_manage:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.invited_title));
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new InviteeManageFragment()).commit();
                    return true;
                } catch (Exception e){ e.printStackTrace(); return super.onOptionsItemSelected(item);}
            case R.id.menu_shopping_manage:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.shopping_title));
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new ShopManageFragment()).commit();
                    return true;
                } catch (Exception e){ e.printStackTrace(); return super.onOptionsItemSelected(item);}
            case R.id.menu_event_manage:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.event_title);
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new EventManageFragment()).commit();
                    return true;
                } catch (Exception e){ e.printStackTrace(); return super.onOptionsItemSelected(item);}
            case R.id.menu_invited_list:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.summary_invitee_title));
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new SummaryInviteeFragment()).commit();
                    return true;
                } catch (Exception e) { e.printStackTrace(); return super.onOptionsItemSelected(item);}
            case R.id.menu_shopping_list:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.summary_shopping_title));
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new SummaryShoppingFragment()).commit();
                    return true;
                } catch (Exception e) { e.printStackTrace(); return super.onOptionsItemSelected(item);}
            case R.id.menu_event_list:
                try {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.summary_event_title);
                    listFrag = getSupportFragmentManager().getFragments();
                    for (Fragment frag : listFrag) {
                        getSupportFragmentManager().beginTransaction().hide(frag).commit();
                    }
                    getSupportFragmentManager().beginTransaction().replace(android.R.id.content,
                            new SummaryEventFragment()).commit();
                    return true;
                } catch (Exception e) { e.printStackTrace(); return super.onOptionsItemSelected(item);}
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}