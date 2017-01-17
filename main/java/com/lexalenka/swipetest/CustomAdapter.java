package com.lexalenka.swipetest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by v622721 on 1/9/17.
 */
public class CustomAdapter extends PagerAdapter{


    private int[] img = {R.drawable.pik1,R.drawable.pik2, R.drawable.pik3, R.drawable.pik4};
    private LayoutInflater inflater;
    private Context ctx;


    public CustomAdapter(Context ctx){
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe,container,false);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        imageView.setImageResource(img[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }

}
