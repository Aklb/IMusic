package com.zhang.music.activitys;


import android.content.Intent;
import android.os.Bundle;

import com.zhang.music.R;

import java.util.Timer;
import java.util.TimerTask;

/*
* 1.延迟三秒
* 2.转跳页面
* */
public class WelcomeActivity extends BaseActivity {
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

//  初始化
    private void init(){
       timer=new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
//               Log.e("WelcomeActivity","当前线程为 :" + Thread.currentThread());
//               toMain();
               toLogin();
           }
       },3*1000);
    }

//  跳转到MainActivity
    private void toMain(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    //  跳转到LoginActivity
    private void toLogin(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}



