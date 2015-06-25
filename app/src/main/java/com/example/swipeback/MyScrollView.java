package com.example.swipeback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by pocktynox on 2015/6/24.
 */
public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        if (MOVING_X > MOVING_Y && MOVING_X > 10f) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                ORIGINAL_X = ev.getX();
                ORIGINAL_Y = ev.getY();
                super.onTouchEvent(ev);
                return false;
            case MotionEvent.ACTION_MOVE:
                MOVING_X = ev.getX() - ORIGINAL_X;
                MOVING_Y = Math.abs(ev.getY() - ORIGINAL_Y);
                if (MOVING_X > MOVING_Y && MOVING_X > 10f) {
                    return false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private static float ORIGINAL_X = 0f;
    private static float ORIGINAL_Y = 0f;
    private static float MOVING_X = 0f;
    private static float MOVING_Y = 0f;
}
