package com.example.swipeback;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by pocktynox on 2015/6/23.
 */
public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textContent = (TextView) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        button = (Button) findViewById(R.id.button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String content = getIntent().getStringExtra("content");
        textContent.setText(content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textContent.setText("lalala");
            }
        });

        activityGestureDetector = new GestureDetectorCompat(this, new ActivityGestureListener());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.anim_content_slide_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean r = activityGestureDetector.onTouchEvent(event);
        Log.d("ContentActivity", "activity view on touch event result: " + Boolean.toString(r));
        return r;
//        return super.onTouchEvent(event);
    }

    private Toolbar toolbar;
    private TextView textContent;
    private ScrollView scrollView;
    private Button button;

    private GestureDetectorCompat activityGestureDetector;
    private GestureDetectorCompat scrollGestureDetector;

//    private static float ORIGINAL_X = 0;
//    private static float ORIGINAL_Y = 0;
//    private static boolean isMoving = false;

    private class ActivityGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("ContentActivity", "activity gesture listener on fling");
//            return super.onFling(e1, e2, velocityX, velocityY);
            float moving_x = e2.getX() - e1.getX();
            float moving_y = Math.abs(e2.getY() - e1.getY());
            if (moving_x > moving_y && moving_x > 10f) {
                onBackPressed();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("ContentActivity", "activity gesture listener on scroll");
            Log.d("ContentActivity", "e1 is null: " + Boolean.toString(e1 == null) + ", e2 is null: " + Boolean.toString(e2 == null));
//            return super.onScroll(e1, e2, distanceX, distanceY);
            float moving_x = e2.getX() - e1.getX();
            float moving_y = Math.abs(e2.getY() - e1.getY());
            if (moving_x > moving_y && moving_x > 10f) {
                onBackPressed();
                return true;
            } else {
                return false;
            }
        }
    }
}
