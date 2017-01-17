package com.lexalenka.swipetest;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    CustomAdapter adapter;
    ViewPager viewPager;
    GestureDetector mGestureDetector;
    String TAG = "DirectionCatch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);

        mGestureDetector = new GestureDetector(this, mGestureListener);

        viewPager.setOnTouchListener(new ViewPager.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return mGestureDetector.onTouchEvent(event);
            }


        });

    }

    private GestureDetector.SimpleOnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        int swipe_Min_Distance = 100;
        int swipe_Max_Distance = 350;
        int swipe_Min_Velocity = 100;
        private static final int SWIPE_MAX_OFF_PATH = 400;


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {

            Log.e("onSingleTapUp", "Activated");


            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "[CALLBACK_GL] boolean onSingleTapConfirmed(e:" + e + ")");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "[CALLBACK_GL] boolean onDoubleTap(e:" + e + ")");


            return super.onDoubleTap(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "[CALLBACK_GL] boolean onFling(e1:" + e1 + ", e2:" + e2 + ", velocityX:" + velocityX
                    + ", velocityY:" + velocityY + "");

            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH || Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                return false;

            final float xDistance = Math.abs(e1.getX() - e2.getX());
            final float yDistance = Math.abs(e1.getY() - e2.getY());


            // ToBottomLeft
            if((e1.getX() > e2.getX()) && (e1.getY() < e2.getY())) {
                Log.e("Motion","To Bottom Left");
            }

            // ToTopRight
            if((e1.getX() < e2.getX()) && (e1.getY() > e2.getY())) {
                Log.e("Motion","To Top Right");
            }

            // ToTopLeft
            if((e1.getX() > e2.getX()) && (e1.getY() > e2.getY())) {
                Log.e("Motion","To Top Left");
            }


            // ToBottomRight
            if((e1.getX() < e2.getX()) && (e1.getY() < e2.getY())) {
                Log.e("Motion","To Bottom Right");
            }

            //To Down
            if((e1.getX() == e2.getX()) && (e1.getY() < e2.getY())) {
                Log.e("Motion","Down");
            }

            //To Up
            if((e1.getX() == e2.getX()) && (e1.getY() > e2.getY())) {
                Log.e("Motion","Up");
            }

            //To Left
            if((e1.getX() > e2.getX()) && (e1.getY() == e2.getY())) {
                Log.e("Motion","Horizontal Left");
            }

            //To Right
            if((e1.getX() < e2.getX()) && (e1.getY() == e2.getY())) {
                Log.e("Motion","Horizontal Right");
            }

            if(xDistance > this.swipe_Max_Distance || yDistance > this.swipe_Max_Distance)
                return false;

            velocityX = Math.abs(velocityX);
            velocityY = Math.abs(velocityY);
            boolean result = false;

            if(velocityX > this.swipe_Min_Velocity && xDistance > this.swipe_Min_Distance){
                if(e1.getX() > e2.getX()) // right to left
                    Log.i(TAG, "Swipe Left");

                else
                    Log.i(TAG, "Swipe Right");
            }


            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "[CALLBACK_GL] void onShowPress(e:" + e + ")");
            super.onShowPress(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(TAG, "[CALLBACK_GL] void onLongPress(e:" + e + ")");

            super.onLongPress(e);
        }
    };

}
