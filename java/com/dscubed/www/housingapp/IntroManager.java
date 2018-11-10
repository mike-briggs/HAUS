package com.dscubed.www.housingapp;

import android.content.SharedPreferences;
import android.content.Context;

/**
 * Created by mbrig_000 on 2017-02-03.
 */

public class IntroManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context){

        this.context = context;
        pref = context.getSharedPreferences("first",0);
        editor = pref.edit();

    }

    public void setFirst(boolean isFirst){

        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    public boolean Check(){
        return pref.getBoolean("check",true);
    }
}
