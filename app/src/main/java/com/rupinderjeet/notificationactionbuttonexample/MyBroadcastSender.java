package com.rupinderjeet.notificationactionbuttonexample;
// Author  : RUPINDERJEET (rupinderjeet@yandex.com)
// Date    : 12/12/2016.
// Project : NotificationActionButtonExample 

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastSender extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Broadcast Received by MyBroadcastSender.", Toast.LENGTH_SHORT).show();

        // send back to your class
        Intent newIntent = new Intent();
        newIntent.setAction(context.getString(R.string.broadcast_id));
        context.sendBroadcast(newIntent);
        Toast.makeText(context, "Broadcast sent back.", Toast.LENGTH_SHORT).show();

    }
}
