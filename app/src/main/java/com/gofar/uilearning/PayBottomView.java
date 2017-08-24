package com.gofar.uilearning;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/24 16:59
 */
public class PayBottomView extends PopupWindow {

    public PayBottomView(Context context) {
        this(context, null);
    }

    public PayBottomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
//        FrameLayout frameLayout = new FrameLayout(context);
//        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        View view = LayoutInflater.from(context).inflate(R.layout.view_bottom_pay, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        PsdAdapter adapter = new PsdAdapter(createData());
        recyclerView.setAdapter(adapter);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.BOTTOM;
//        view.setLayoutParams(params);
//        frameLayout.addView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    private List<Integer> createData() {
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        data.add(5);
        data.add(6);
        data.add(7);
        data.add(8);
        data.add(9);
        data.add(null);
        data.add(0);
        data.add(-1);
        return data;
    }
}
