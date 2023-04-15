package com.example.advprojectparty.PartyWidget;

import static java.security.AccessController.getContext;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.advprojectparty.DatabaseElements.Event;
import com.example.advprojectparty.DatabaseElements.EventDao;
import com.example.advprojectparty.DatabaseElements.PartyListDatabase;
import com.example.advprojectparty.ManagementFragments.EventManageFragment;
import com.example.advprojectparty.R;
import com.example.advprojectparty.StartScreen;

import java.util.List;

public class PartyWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // loop through all app widgets for this provider
        for (int i = 0; i < appWidgetIds.length; i++) {

            // create a pending intent on the Start screen activity (that is our context)
            Intent intent = new Intent(context, StartScreen.class);
            //create the pending intent
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // get the layout and set the listener for the app widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.party_widget);

            //add the pending intent to the onclick event, so that it will go to the app when we touch the widget
            views.setOnClickPendingIntent(R.id.party_widget, pendingIntent);

            //access the database to display the most recent 3 events
            // get the names to display on the app widget

            //use the data access object to get a database
            PartyListDatabase db = PartyListDatabase.getInstance(context);
            List<Event> eventsList = db.getEventDao().getEvents();

            // update the textviews with the three most recent event names
            // if event names are not found, then displays blanks
            String[] top3 = {
                    eventsList.get(0).getEvent(),
                    eventsList.get(1).getEvent(),
                    eventsList.get(2).getEvent()
            };
            //if they don't exist, replace them with empty
            views.setTextViewText(R.id.eventText1, top3[0] == null ? "" : top3[0]);
            views.setTextViewText(R.id.eventText2, top3[1] == null ? "" : top3[1]);
            views.setTextViewText(R.id.eventText3, top3[2] == null ? "" : top3[2]);

            // update the current app widget
            int appWidgetId = appWidgetIds[i];
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        //if the party database has been updated, then update our widget
        if (intent.getAction().equals(EventManageFragment.EVENT_UPDATE)) {

            //get all current widgets of this type
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            ComponentName provider = new ComponentName(context, PartyWidget.class);
            int[] appWidgetIds = manager.getAppWidgetIds(provider);

            //update the widget
            onUpdate(context, manager, appWidgetIds);
        }
    }

}
