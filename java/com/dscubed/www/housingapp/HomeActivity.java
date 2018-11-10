package com.dscubed.www.housingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mbrig_000 on 2017-01-23.
 */

public class HomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //final Button settingsLink = (Button) findViewById(R.id.bSettings);
        //settingsLink.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {

           //     Intent settingsIntent = new Intent(HomeActivity.this, Settings.class);
           //     HomeActivity.this.startActivity(settingsIntent);
          //  }
       // });
    }
}
