/**
    FILE: SummaryShoppingFragment
    OVERVIEW:
        This file contains the class definition for the
        activity fragment that displays the shopping list

        It gets it's display data from the database room that manages the app's SQlite Database
        The Shopping list is empty until Item(s) are added from the ShopManageFragment

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

import com.example.advprojectparty.DatabaseElements.PartyListDB;
import com.example.advprojectparty.DatabaseElements.PartyListDatabase;
import com.example.advprojectparty.DatabaseElements.ShopList;
import com.example.advprojectparty.R;

import java.util.ArrayList;

public class SummaryShoppingFragment extends Fragment {

    ListView myShoppingList = null;

    public SummaryShoppingFragment() {
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
        ArrayList<ShopList> dummyShopping = new ArrayList<>(db.getShopDao().getShopList());

        // Started inflating the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_summary_shopping, container, false);
        myShoppingList = thisView.findViewById(R.id.shoppingList);
        setShoppingScreen(dummyShopping);
        getShoppingFromRoom();
        // Inflated the layout for this fragment previously, so lets return it
        return thisView;
    }

    private void setShoppingScreen(ArrayList<ShopList> shopLists) {
        String[] columns = {
                PartyListDB.SHOP_LIST_ITEM, PartyListDB.SHOP_LIST_TYPE,
                PartyListDB.SHOP_LIST_QTY, PartyListDB.SHOP_LIST_PRIZE
        };
        int[] to = {
                R.id.shoppingItem, R.id.shoppingType,
                R.id.shoppingQty, R.id.shoppingQPrize
        };
        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        "_id", PartyListDB.SHOP_LIST_ITEM, PartyListDB.SHOP_LIST_TYPE,
                        PartyListDB.SHOP_LIST_QTY, PartyListDB.SHOP_LIST_PRIZE
                }
        );
        for (ShopList shopList : shopLists) {
            cursor.newRow()
                    .add("_id",0)
                    .add(PartyListDB.SHOP_LIST_ITEM, shopList.getItem())
                    .add(PartyListDB.SHOP_LIST_TYPE, shopList.getType())
                    .add(PartyListDB.SHOP_LIST_QTY, shopList.getQty())
                    .add(PartyListDB.SHOP_LIST_PRIZE, shopList.getQ_prize());
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getContext(), R.layout.shopping_list_item,cursor,columns,to,0
        );

        myShoppingList.setAdapter(adapter);
    }

    private void getShoppingFromRoom() {
        PartyListDatabase database = PartyListDatabase.getInstance(getContext());
        new PopulateShopping().execute(database);
    }

    private class PopulateShopping extends AsyncTask<PartyListDatabase, Void, PartyListDatabase> {

        @Override
        protected PartyListDatabase doInBackground(PartyListDatabase... d) {
            PartyListDatabase db = d[0];
            if (db != null) {
                db.getShopDao().getShopList();
            }
            return db;
        }
    }
}