package com.zhang.music;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

/**
 * Author by Zhang on 2019/7/15 17:27
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Utils.init(this);
        Realm.init(this);
    }
}
