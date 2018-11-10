package com.dscubed.www.housingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;



public class MainActivity extends AppCompatActivity {
    public static String john = "john";

    private ViewPager viewPager;
    Button skip,next;
    private IntroManager intromanager;
    private ViewPagerAdapter ViewPagerAdapter;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private GoogleApiClient client;
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_slides);
        intromanager = new IntroManager(this);
        if(!intromanager.Check()){
            intromanager.setFirst(false);
            Intent i = new Intent(MainActivity.this, Main2.class);
            startActivity(i);
            finish();
        }
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        skip = (Button)findViewById(R.id.btn_skip);
        next = (Button)findViewById(R.id.btn_next);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);

        layouts = new int[]{R.layout.activity_screen1, R.layout.activity_screen2,
                R.layout.activity_screen3,R.layout.activity_screen4,R.layout.activity_screen5};
        addBottomDots(0);
        changeStatusBarColor();
        ViewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(ViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent skipIntent = new Intent(MainActivity.this, Main2.class);
                MainActivity.this.startActivity(skipIntent);
                overridePendingTransition(R.animator.slide_from_right,R.animator.slide_to_left);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int current = getItem(+1);
                if(current<layouts.length){
                    viewPager.setCurrentItem(current);
                }
                else
                {
                    Intent nextIntent = new Intent(MainActivity.this, Main2.class);
                    MainActivity.this.startActivity(nextIntent);
                    overridePendingTransition(R.animator.slide_from_right,R.animator.slide_to_left);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.animator.slide_from_left, R.animator.slide_to_right);
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }



    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    } */

    private void addBottomDots(int position)
    {

        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(colorActive[position]);
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

        }
        @Override
        public void onPageSelected(int position){

                addBottomDots(position);
            if(position==layouts.length-1){
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);
            }
            else{
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }
        }
        @Override
        public void onPageScrollStateChanged(int state){

        }
    };

private void changeStatusBarColor(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position],container,false);
            container.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v =(View)object;
            container.removeView(v);
        }
    }
}
