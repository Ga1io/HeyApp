package com.example.heydongju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class FrontActivity extends AppCompatActivity {



    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        Timer timer =new Timer();
        timer.schedule(m_task, 2000);

    }

    TimerTask m_task = new TimerTask(){
        @Override
        public void run() {
            Intent intent = new Intent(FrontActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}