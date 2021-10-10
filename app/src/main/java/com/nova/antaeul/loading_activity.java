package com.nova.antaeul;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class loading_activity extends AppCompatActivity {
    public static boolean timeline_go = false;
    public static boolean map_go = false;
    public static boolean mynote_go = false;
    public static boolean more_go = false;

    public static boolean map_modi = false;
    public static boolean mynote_modi = false;

    public static boolean modi_finish = false;

    ProgressBar progressBar;
    int count = 0;

    Handler handler = new Handler(Looper.myLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("start runnable run");
            count++;
            if(count == 2){
                handler.removeCallbacks(runnable);
                handler_pro.removeCallbacks(runnable_pro);
                Intent intent = new Intent(loading_activity.this, loginpage_activity.class);
                startActivity(intent);
                finish();
            }
            else{
                handler.postDelayed(runnable, 1000);
            }
            System.out.println("end runnable run");
        }
    };

    Handler handler_pro = new Handler(Looper.myLooper());
    Runnable runnable_pro = new Runnable() {
        int value = 0;
        @Override
        public void run() {
            value++;
            progressBar.setProgress(value) ;
            handler_pro.postDelayed(runnable_pro, 100);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        progressBar = findViewById(R.id.progressBar) ;
        handler.post(runnable);
        handler_pro.post(runnable_pro);

        getHashKey();
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null) {
            Log.e("KeyHash", "KeyHash:null");
        }
        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}