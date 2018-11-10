package com.dscubed.www.housingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Michael Briggs on 2017-02-03.
 */

public class Main2 extends AppCompatActivity {
    public static String john = "john";


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);

        final Button loginLink = (Button) findViewById(R.id.bLogin);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        final TextView registerLink = (TextView) findViewById(R.id.bRegisterLink);


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(Main2.this, RegisterActivity.class);
                Main2.this.startActivity(registerIntent);
                overridePendingTransition(R.animator.slide_from_right,R.animator.slide_to_left);
            }


        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  etUsername.getText().toString();
                String password = etPassword.getText().toString();
                UserAreaActivity.setDetails(name, password);
                Intent loginIntent = new Intent(Main2.this, UserAreaActivity.class);
                Main2.this.startActivity(loginIntent);
                overridePendingTransition(R.animator.slide_from_right,R.animator.slide_to_left);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
}
