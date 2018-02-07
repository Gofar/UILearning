package com.gofar.uilearning;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gofar.uilearning.keyboard.KeyboardView;
import com.gofar.uilearning.keyboard.PayInputView;

/**
 * @author lcf
 * @date 2018/2/7 15:02
 * @since 1.0
 */
public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvClose;
    private TextView mTvForgetPsd;
    private PayInputView mPayInputView;
    private KeyboardView mKeyboardView;
    private TextView mTvLoading;
    private FrameLayout mLayPay;
    private LinearLayout mLayLoading;
    private LinearLayout mLayRoot;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.activity_pay);
        mIvClose = findViewById(R.id.iv_close);
        mTvForgetPsd = findViewById(R.id.tv_forget_psd);
        mPayInputView = findViewById(R.id.pay_input_view);
        mKeyboardView = findViewById(R.id.keyboard_view);
        mTvLoading = findViewById(R.id.tv_loading);
        mLayPay = findViewById(R.id.lay_pay);
        mLayLoading = findViewById(R.id.lay_loading);
        mLayRoot = findViewById(R.id.lay_root);

        mIvClose.setOnClickListener(this);
        mTvForgetPsd.setOnClickListener(this);
        mPayInputView.setOnClickListener(this);
        mPayInputView.setKeyboardView(mKeyboardView);
        mPayInputView.setOnPassWordChangeListener(new PayInputView.OnPassWordChangeListener() {
            @Override
            public void complete(String password) {
                submit(password);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bottom_slide_in_f);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayRoot.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLayRoot.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_forget_psd:
                break;
            case R.id.pay_input_view:
                if (mKeyboardView.getVisibility() != View.VISIBLE) {
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.bottom_slide_in_f);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mKeyboardView.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mKeyboardView.startAnimation(animation);
                }
                break;
            default:
                break;
        }
    }

    private void submit(String psd) {
        mLayPay.setVisibility(View.GONE);
        mLayLoading.setVisibility(View.VISIBLE);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTvLoading.setText("支付成功");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 200);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        if (mLayPay.getVisibility() == View.VISIBLE && mKeyboardView.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.bottom_slide_out_f);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mKeyboardView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mKeyboardView.startAnimation(animation);
        } else {
            super.onBackPressed();
        }
    }
}
