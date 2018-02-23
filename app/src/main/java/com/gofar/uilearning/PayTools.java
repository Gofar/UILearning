package com.gofar.uilearning;

/**
 * @author lcf
 * @date 2018/2/23 11:41
 * @since 1.0
 */
public class PayTools {
    private static PayTools INSTANCE;
    private PayBaseCompat mPayBaseCompat;

    private PayTools() {

    }

    public static synchronized PayTools getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PayTools();
        }
        return INSTANCE;
    }

    public void setPayCompat(PayBaseCompat compat) {
        this.mPayBaseCompat = compat;
    }

    public PayBaseCompat getPayBaseCompat() {
        return mPayBaseCompat;
    }
}
