package com.example.advprojectparty;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class AmazingService extends Service {
    public AmazingService() {
    }

    private Timer annoyTimer;
    private static final String NOTIF_CHANNEL = "Annoy User";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d("AmazingService", "Service has been created");
        annoyTimer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //start a timer that annoys the user to come back
        annoyTimer.scheduleAtFixedRate(new Annoy(), 10_000, 60_000); //wait 10 seconds, then annoy every 1 minute
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("AmazingService", "Service has been destroyed");
        //destroy the timer
        annoyTimer.cancel();
    }

    private class Annoy extends TimerTask {
        @Override
        public void run() {
            setNotification("Hey!", "Where did you go?", "Come and plan out your next party!");
        }
    }

    //code to build a notification
    protected void setNotification(String ticker, String title, String text) {
        Intent notificationIntent = new Intent(this, StartScreen.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int piFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent,piFlag);

        CharSequence tickerText = ticker;
        CharSequence contentTitle = title;
        CharSequence contentText = text;
        int icon = R.drawable.ic_launcher_foreground;

        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(this, NOTIF_CHANNEL)
                .setSmallIcon(icon)
                .setTicker(tickerText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Android 8.0 stuff. Probably a good idea to use with the latest SDK
            //If you need more, this URL has a good explanation of new things
            //https://developer.android.com/training/notify-user/build-notification.html

            CharSequence name = NOTIF_CHANNEL;
            String description = "Notifications that annoy the user if they are not using the app";
            NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);
            // Register the channel with the system
            manager.createNotificationChannel(channel);
            if (manager.areNotificationsEnabled()) {
                manager.notify(1,myBuilder.build());
            }
        }

    }

}