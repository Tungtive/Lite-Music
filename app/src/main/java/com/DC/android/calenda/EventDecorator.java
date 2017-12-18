package com.DC.android.calenda;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;


import com.DC.android.PlanActivity;
import com.DC.android.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by XZJ on 2017/11/28.
 */

public class EventDecorator implements DayViewDecorator {

    private int color;
    private HashSet<CalendarDay> dates;
    private Context context;

        public EventDecorator(int color, Collection<CalendarDay> dates, Context context) {
        this.color = color;
        this.dates = new HashSet<>(dates);
        this.context=context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

      //  view.addSpan(new DotSpan(5, color));
        view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_aim));

    }
}
