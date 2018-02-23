package com.gofar.uilearning;

import com.gofar.uilearning.requestbutton.RequestButton;

/**
 * @author lcf
 * @date 2018/2/23 11:28
 * @since 1.0
 */
public abstract class PayBaseCompat {
    public void pay(RequestButton requestButton, String psd) {
        requestButton.startRequest();
        onPay(psd);
    }

    /**
     * 发起支付
     *
     * @param psd 支付密码
     */
    public abstract void onPay(String psd);

    /**
     * 支付成功
     *
     * @param msg 支付信息
     */
    public abstract void onSuccess(String msg);

    /**
     * 支付失败
     *
     * @param msg 支付信息
     */
    public abstract void onFailed(String msg);
}
