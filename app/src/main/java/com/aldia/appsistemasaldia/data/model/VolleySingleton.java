package com.aldia.appsistemasaldia.data.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton InstanceVolley;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleySingleton (Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstanceVolley(Context context) {

        if (InstanceVolley == null){
            InstanceVolley = new VolleySingleton(context);
        }
        return InstanceVolley;
    }

    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue ( Request<T> request){
        getRequestQueue().add(request);
    }

}
