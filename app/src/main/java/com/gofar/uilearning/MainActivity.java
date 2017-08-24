package com.gofar.uilearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private PayBottomView mPayview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psd);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPayview == null) {
                    mPayview = new PayBottomView(MainActivity.this);
                }
                if (mPayview.isShowing()) {
                    return;
                }
                mPayview.showAtLocation(findViewById(R.id.layout_root), Gravity.BOTTOM, 0, 0);
                //mPayview.showAsDropDown(v);
            }
        });

    }
}
