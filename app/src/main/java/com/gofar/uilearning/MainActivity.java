package com.gofar.uilearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

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
//
        EditText editText= (EditText) findViewById(R.id.edit_text);
//        editText.setKeyListener();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
