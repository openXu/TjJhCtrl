package com.openxu.tjjh.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.tjjh.R;
import com.openxu.tjjh.service.TranService;
import com.openxu.tjjh.utils.Constant;
import com.openxu.tjjh.utils.DensityUtil;
import com.openxu.tjjh.utils.SpUtils;
import com.openxu.tjjh.utils.ToastUtil;
import com.openxu.tjjh.utils.Utils;

/**
 * Created by Administrator on 2016/8/7.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    protected String TAG;
    protected Context mContext;

    protected RelativeLayout rl_window;
    protected Button btn_status;

    protected SpUtils spUtils;
    /**
     * 悬浮窗体
     */
    protected PopupWindow popupWindow_sending;
    protected View contentView_sending;
    protected TextView tv_sending;
    //记录
    protected PopupWindow popupWindow_command;
    protected TextView tv_coommand;
    protected ScrollView sl;
    protected String COMMAND_STR="";
    protected boolean isPopShow = false;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case Constant.HANDMSG_CONNECTED:
                    ToastUtil.showToast(R.string.con_sec);
                    Utils.LOG_V(TAG, "连接成功...");
                    break;
                case Constant.HANDMSG_CONNECT_FAIL:
                    ToastUtil.showToast(R.string.con_fail);
                    Utils.LOG_V(TAG, "连接失败...");
                    break;
                case Constant.HANDMSG_NOTFOND_COMMAND:
                    dismissPopupWindow_sending();
                    ToastUtil.showToast(R.string.send_no_command);
                    Utils.LOG_V(TAG, "没有找到此命令文件");
                    break;
                case Constant.HANDMSG_SEND_NOCONNECT:
                    dismissPopupWindow_sending();
                    ToastUtil.showToast(R.string.con_cut1);
                    Utils.LOG_V(TAG, "连接已断开了...");
                    break;
                case Constant.HANDMSG_SENDDING:
                    showSending();
                    break;
                case Constant.HANDMSG_SEND_SEC:
                    dismissPopupWindow_sending();
                    ToastUtil.showToast(R.string.send_sec);
                    Utils.LOG_V(TAG, "命令发送成功...");
                    COMMAND_STR="";
                    break;
                case Constant.HANDMSG_SEND_NOFK:
                    ToastUtil.showToast(R.string.send_nofk);
                    COMMAND_STR += ("硬件无反馈\n");
                    if(isPopShow){
                        tv_coommand.setText(COMMAND_STR);
                        sl.scrollTo(0, 1000);
                    }
                    Utils.LOG_V(TAG, "命令发送无反馈...");
                    break;
                case Constant.HANDMSG_DO_FAIL:   //执行失败
                    ToastUtil.showToast( R.string.send_dofail);
                    COMMAND_STR += ("执行失败\n");
                    if(isPopShow){
                        tv_coommand.setText(COMMAND_STR);
                        sl.scrollTo(0, 1000);
                    }
                    Utils.LOG_V(TAG, "执行失败...");
                    break;
                case Constant.HANDMSG_SEND_COMMAND:   //命令显示
                    COMMAND_STR += (msg.obj+"\n");
                    if(isPopShow){
                        tv_coommand.setText(COMMAND_STR);
                        sl.scrollTo(0, 1000);
                    }
                    break;
                case Constant.HANDMSG_SEND_FAIL:
                    dismissPopupWindow_sending();
                    ToastUtil.showToast( R.string.send_fail);
                    Utils.LOG_V(TAG, "命令发送失败...");
                    COMMAND_STR="";
                    break;
                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
        spUtils = SpUtils.getInstance(this);
        initView();
        rl_window =(RelativeLayout) findViewById(R.id.rl_window);

        initData();
    }

    protected abstract void initView() ;

    protected void initView(int screen){}
    protected void initData() {
        serviceCon = new ServiceCon();
        bindService(new Intent(mContext, TranService.class), serviceCon, BIND_AUTO_CREATE);

        contentView_sending = View.inflate(getApplicationContext(), R.layout.popup_item_sendpro, null);
        tv_sending = (TextView) contentView_sending.findViewById(R.id.tv_sending);
        // 构造一个悬浮窗体：显示的内容，窗体的宽高
        popupWindow_sending = new PopupWindow(contentView_sending, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // ☆ 注意： 必须要设置背景，才能让悬浮窗体播放动画
        popupWindow_sending.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    protected void onStart() {
        super.onStart();
        COMMAND_STR="";
        if(binder!=null)
            binder.setUiHandler(handler);
    }


    /**
     * 如果不需要从新载入，可以在AndroidManifest.xml中加入配置 android:configChanges="orientation|keyboardHidden"
     * 这样在程序中Activity就不会重复的调用onCreate()甚至不会调用onPause、onResume
     * 只会调用一个 onConfigurationChanged(Configuration newConfig)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Utils.LOG_I(TAG, "横竖屏变化了");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //横屏
            initView(Constant.SCREEN_LAND);
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //竖屏
            initView(Constant.SCREEN_PORT);
        }else if(newConfig.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_NO){
            //键盘没关闭。屏幕方向为横屏
        }else if(newConfig.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_YES){
            //键盘关闭。屏幕方向为竖屏
        }

        btn_status = (Button) findViewById(R.id.btn_status);
        if(spUtils.getIdentity()==SpUtils.IDENTITY_ADMIN){
            btn_status.setVisibility(View.VISIBLE);
            btn_status.setOnClickListener(this);
        }else  if(spUtils.getIdentity()==SpUtils.IDENTITY_NORMAL){
            btn_status.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_status:
                if(isPopShow){
                    dismissPopupWindow_command();
                    isPopShow = false;
                }else{
                    showCommandPopupwindow();
                }
                break;

        }
    }
    protected ServiceCon serviceCon;
    protected TranService.ServiceBinder binder;
    class ServiceCon implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (TranService.ServiceBinder) service;
            binder.setUiHandler(handler);
            new Thread(){public void run() {
                binder.initThread();
                binder.connect();
            };}.start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    //所有*F打头的代码发送以后不需要做反馈重发判断
    public void sendOneCommand(final String command) {
        showSendPro();
        if(command.startsWith("*F")){
            new Thread() {
                public void run() {
                    binder.sendCommand(command);
                };
            }.start();
        }else{
            new Thread() {
                public void run() {
                    binder.sendCommandRead(command);
                };
            }.start();
        }
    }
    //所有*F打头的代码发送以后不需要做反馈重发判断
    public void startCommand(final String[] commands) {
        showSendPro();
        if(commands[0].startsWith("*F")){
            new Thread() {
                public void run() {
                    binder.sendCommand(commands);
                };
            }.start();
        }else{
            new Thread() {
                public void run() {
                    binder.sendCommandRead(commands);
                };
            }.start();
        }
    }


    public void cancle(View v){
        if (binder.getSendStatus()) {
            showSending();
            return;
        }
        finish();
    }
    public void showSending(){
        tv_sending.setText("命令还没发送完毕，请稍后...");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_sending.setText("正在发送...");
            }
        }, 1000);
    }

    public void showSendPro(){
        try{
            if(popupWindow_sending != null && popupWindow_sending.isShowing())
                return;
            tv_sending.setText("正在发送...");
            Display display = getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int hight = display.getHeight();
            //根据手机手机的分辨率 把60dip 转化成 不同的值 px
            int px = DensityUtil.dip2px(getApplicationContext(), 300);
            int py = DensityUtil.dip2px(getApplicationContext(), 100);

            //让悬浮窗体显示（显示的父窗体就是ListView；重心；显示的xy坐标）
            popupWindow_sending.showAtLocation(rl_window, Gravity.TOP+ Gravity.LEFT, width/2-px/2, hight/2-py/2);
            //让悬浮窗体播放动画
            AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);    //透明度动画
            aa.setDuration(200);                                   //执行时间
            //缩放动画：前四个参数（xy缩放前后比例）后四个参数（缩放时的中心点）
            ScaleAnimation sa = new ScaleAnimation(0.5f, 1.0f, 0.5f,
                    1.0f, Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            sa.setDuration(200);
            //让两个动画同时播放
            AnimationSet set = new AnimationSet(false);
            set.addAnimation(sa);
            set.addAnimation(aa);
            contentView_sending.startAnimation(set);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    protected void dismissPopupWindow_sending() {
        if (popupWindow_sending != null && popupWindow_sending.isShowing())
            popupWindow_sending.dismiss();
    }

    protected void showCommandPopupwindow() {
        try{
            if(popupWindow_command==null){
                View popupView = View.inflate(getApplicationContext(),R.layout.popup_command, null);
                // 构造一个悬浮窗体：显示的内容，窗体的宽高
                popupWindow_command = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow_command.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                sl =  (ScrollView) popupView.findViewById(R.id.sl);
                tv_coommand = (TextView) popupView.findViewById(R.id.tv_coommand);
                TextView tv_towo = (TextView) popupView.findViewById(R.id.tv_towo);

                tv_towo.setOnTouchListener(new View.OnTouchListener() {
                    int orgX, orgY;
                    int offsetX, offsetY;
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                orgX = (int) event.getX();
                                orgY = (int) event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                offsetX = (int) event.getRawX() - orgX;
                                offsetY = (int) event.getRawY() - orgY;
                                popupWindow_command.update(offsetX, offsetY, -1, -1, true);
                                break;
                        }
                        return true;
                    }
                });
            }
            Display display = getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int hight = display.getHeight();
            int px = DensityUtil.dip2px(getApplicationContext(), 300);
            int py = DensityUtil.dip2px(getApplicationContext(), 100);

            //让悬浮窗体显示（显示的父窗体就是ListView；重心；显示的xy坐标）
            popupWindow_command.showAtLocation(rl_window, Gravity.TOP+ Gravity.LEFT, width/2-px/2, hight/2+py/2+20);
            isPopShow = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void dismissPopupWindow_command() {
        if (popupWindow_command != null && popupWindow_command.isShowing())
            popupWindow_command.dismiss();
    }
    @Override
    public void onBackPressed() {
        if (binder.getSendStatus()) {
            showSending();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        try{
            unbindService(serviceCon);
            dismissPopupWindow_sending();
            popupWindow_sending= null;
            dismissPopupWindow_command();
            popupWindow_command= null;
        }catch (Exception e){
            e.printStackTrace();
        }


        super.onDestroy();
    }


}
