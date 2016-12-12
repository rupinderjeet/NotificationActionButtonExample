package com.rupinderjeet.notificationactionbuttonexample;
// Author  : RUPINDERJEET (rupinderjeet@yandex.com)
// Date    : 12/12/2016.
// Project : NotificationActionButtonExample 

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {

    private NotificationManager notificationManager;
    private MyBroadcastReceiver myBroadcastReceiver;
    private int notificationId = 12002;

    @Nullable @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public void onCreate() {
        super.onCreate();

        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(getString(R.string.broadcast_id));
        registerReceiver(myBroadcastReceiver, myIntentFilter);

        showNotification();
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification() {

        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent actionIntent = new Intent(this, MyBroadcastSender.class);
        PendingIntent actionPendingIntent = PendingIntent
                .getBroadcast(MyService.this, 12001, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // prepare the notification
        mNotificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mNotificationBuilder.setContentTitle(getString(R.string.notification_title));
        mNotificationBuilder.setContentText(getString(R.string.notification_text));
        mNotificationBuilder.setOngoing(true);
        mNotificationBuilder.setAutoCancel(false);

        // execute onButtonClick
        mNotificationBuilder.addAction(R.mipmap.ic_launcher, getString(R.string.perform_action), actionPendingIntent);

        // show the notification
        notificationManager.notify(notificationId, mNotificationBuilder.build());
    }

    // BroadcastReceiver
    public class MyBroadcastReceiver extends BroadcastReceiver {

        public MyBroadcastReceiver(){
            super();
        }

        @Override public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null && intent.getAction().equals(getString(R.string.broadcast_id))) {

//                Intent mainIntent = new Intent(context, MainActivity.class);
//                startActivity(mainIntent);
                Toast.makeText(context, "Broadcast received by MyBroadcastReceiver. Now, you can perform actions.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Intent is null.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(notificationId);
        unregisterReceiver(myBroadcastReceiver);
        Toast.makeText(MyService.this, "Service stopped", Toast.LENGTH_SHORT).show();
    }
}