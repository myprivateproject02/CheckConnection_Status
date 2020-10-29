package com.sevenseas.checkconnectionstatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;


import com.sevenseas.checkconnectionstatus.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.NetworkInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    boolean isCommected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        isConnectedR();
    }

    public void isConnectedR() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                isCommected = isConnected();
                if (isCommected) {
                    binding.text.setText("Connected");
                } else {
                    binding.text.setText("Not Connected");
                }

            }
        });

    }


    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected() || mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }

    }


}