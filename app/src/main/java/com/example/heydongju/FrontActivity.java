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

    TimerTask m_task = new TimerTask(){
        @Override
        public void run() {
            Intent intent = new Intent(FrontActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
//    public void tofindPw() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_login_to_nav_findpw);
//    }
//
//    public void joinfragmentChange(Bundle bundle) {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_login_to_nav_joinafter, bundle);
//    }
//    public void frontfragmentChange() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_front_to_nav_login);
//    }
//
//    public void loginfragmentChange() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_login_to_nav_join);
//    }
//    public void joinafterfragmentChange() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_joinafter_to_nav_login);
//    }
//
//    public void tofindId() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_login_to_nav_findid);
//    }
//    public void fromfindId(){
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_findid_to_nav_login);
//    }
//    public void jointoLogin(){
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_nav_join_to_nav_login);
//    }
}