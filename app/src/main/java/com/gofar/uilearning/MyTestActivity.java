package com.gofar.uilearning;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gofar.uilearning.requestbutton.OnRequestCallback;
import com.gofar.uilearning.requestbutton.RequestButtonF;

/**
 * @author lcf
 * @date 2018/2/8 16:19
 * @since 1.0
 */
public class MyTestActivity extends AppCompatActivity {
    private RequestButtonF requestButtonF;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psd);
        requestButtonF = findViewById(R.id.start0);
        requestButtonF.setOnRequestCallback(new OnRequestCallback() {
            @Override
            public boolean beforeRequest() {
                return true;
            }

            @Override
            public void onRequest() {

            }

            @Override
            public void onFinish(boolean isSuccess) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestButtonF.startRequest();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestButtonF.requestSuccess();
                    }
                }, 3000);

            }
        }, 3000);

    }
}
