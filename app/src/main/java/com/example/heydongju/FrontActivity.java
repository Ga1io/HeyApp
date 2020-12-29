package com.example.heydongju;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

    public void jointoLogin(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.action_nav_sign_to_nav_login);
    }

    TimerTask m_task = new TimerTask(){
        @Override
        public void run() {
            Intent intent = new Intent(FrontActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}