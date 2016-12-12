package com.rupinderjeet.notificationactionbuttonexample;

// Author  : RUPINDERJEET (rupinderjeet@yandex.com)
// Date    : 12/12/2016.
// Project : NotificationActionButtonExample

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this, MyService.class));
    }
}
