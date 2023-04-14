package com.example.advprojectparty.PartyWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.advprojectparty.R;
import com.example.advprojectparty.StartScreen;

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


            // get the names to display on the app widget
            // access the events using a DAO then display them on the widget


            // update the current app widget
            int appWidgetId = appWidgetIds[i];
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        //if the party database has been updated, then update our widget
        if (intent.getAction().equals("Hello" /*This will be the database on-update broadcast*/)) {

            AppWidgetManager manager = AppWidgetManager.getInstance(context);

            ComponentName provider = new ComponentName(context, PartyWidget.class);

            int[] appWidgetIds = manager.getAppWidgetIds(provider);
            onUpdate(context, manager, appWidgetIds);
        }
    }

}
