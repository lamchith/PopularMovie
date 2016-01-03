package com.lmc.popularmovie.utility;

import android.app.Application;
import android.content.Context;

/**
 * Created by lmarathchathu on 1/1/2016.
 */
public class MovieApplication extends Application {

    public static Context context=null;

    public MovieApplication(){

    }


    @Override
    public void onCreate() {
        super.onCreate();
        if(null==context)
        {
            context=getApplicationContext();

        }
    }
}
