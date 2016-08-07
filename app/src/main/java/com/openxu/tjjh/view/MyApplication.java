package com.openxu.tjjh.view;

import android.app.Application;

import com.openxu.tjjh.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/7.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ToastUtil.init(this);
    }
}
