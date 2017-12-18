package com.DC.android.mywidget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import com.DC.android.R;

import static android.R.attr.button;

/**
 * Created by XZJ on 2017/11/6.
 */

public class PlanItem extends LinearLayout {
    private int touchSlop;
    public PlanItem(Context context) {
        super(context, null);
    }

    public PlanItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public  PlanItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);

        LayoutInflater.from(context).inflate(R.layout.plan_item, this);

        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }




}

