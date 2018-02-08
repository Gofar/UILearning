package com.gofar.uilearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ccl.perfectisshit.payview.widget.PayView;
import com.gofar.uilearning.keyboard.KeyboardView;
import com.gofar.uilearning.keyboard.PayInputView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/9/15 10:07
 */
public class MyShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay);
        PayInputView payInputView = (PayInputView) findViewById(R.id.pay_input_view);
        KeyboardView keyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        payInputView.setKeyboardView(keyboardView);
        PayView payView=findViewById(R.id.pay_view);

        payInputView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyShowActivity.this, PayActivity.class));
                //overridePendingTransition(0,0);
            }
        });
        payView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MyShowActivity.this);
                bottomSheetDialog.setContentView(R.layout.layout_pay_input);
                bottomSheetDialog.show();
            }
        });
    }
}
