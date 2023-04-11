/**
 *   FILE: StartScreen
 *  OVERVIEW:
 *       This file contains the only an activity in all the app, its only function is
 *       holding the menu for all the app and inflating start the fragments when
 *       they are called by the menu.
 *
 */

package com.example.advprojectparty;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        getSupportActionBar().setTitle(getString(R.string.start_title));

        // This should pull the database or create it
        // with all its components if it does not exists
        database = new PartyListDB(this);
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
        List<Fragment> listFrag = null;
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