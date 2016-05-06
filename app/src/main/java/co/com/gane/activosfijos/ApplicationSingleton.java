package co.com.gane.activosfijos;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

//import co.com.gane.activosfijos.BluetoothReader.AsciiCommander;

/**
 * Created by Egonzalias on 11/04/2016.
 */
public class ApplicationSingleton extends Application{

    //private static AsciiCommander commander = null;
    public static final String TAG = ApplicationSingleton.class.getSimpleName();

    private static ApplicationSingleton mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized ApplicationSingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        //req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /// Returns the current AsciiCommander
    //public AsciiCommander getCommander() { return commander; }

    /// Sets the current AsciiCommander
    //public void setCommander(AsciiCommander _commander ) { commander = _commander; }

}
