package in.myvolley.util;

import android.app.Application;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

import java.net.CookieHandler;
import java.net.CookieManager;


public class DoorCoreController extends Application {

    public static final String TAG = DoorCoreController.class.getName();
    private static DoorCoreController doorAppController;
    public DefaultHttpClient mDefaultHttpClient;
    private RequestQueue mRequestQueue;

//    public static Tracker tracker;
//    public static GoogleAnalytics analytics;
//    public FirebaseRemoteConfig nbRemoteConfig;


    @Override
    public void onCreate() {
        super.onCreate();


        doorAppController = this;



    }

    public <T> void addToRequestQueue(Request<T> req) {



        req.setRetryPolicy(new DefaultRetryPolicy(20000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    @SuppressWarnings("deprecation")
    public RequestQueue getRequestQueue() {


        Log.v("deekshant","getRequestQueue mRequestQueue "+mRequestQueue);

        if (mRequestQueue == null) {

            Log.v("deekshant","getRequestQueue mRequestQueue 222 "+mRequestQueue);

            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);


            // this we used before but we got error of only one request at a
            // time
            // DefaultHttpClient httpclient = new DefaultHttpClient();
            //
            // CookieStore cookieStore = new BasicCookieStore();
            // httpclient.setCookieStore( cookieStore );
            //
            // HttpStack httpStack = new HttpClientStack( httpclient );

            mDefaultHttpClient = new DefaultHttpClient();

            final ClientConnectionManager mClientConnectionManager = mDefaultHttpClient.getConnectionManager();
            final HttpParams mHttpParams = mDefaultHttpClient.getParams();
            final ThreadSafeClientConnManager mThreadSafeClientConnManager = new ThreadSafeClientConnManager(mHttpParams, mClientConnectionManager.getSchemeRegistry());

            mDefaultHttpClient = new DefaultHttpClient(mThreadSafeClientConnManager, mHttpParams);


            // CookieStore cookieStore = new BasicCookieStore();
            //
            // Cookie cookie = new
            // BasicClientCookie("SPRING_SECURITY_REMEMBER_ME_COOKIE",
            // "cEpIekNEMDhSYmRTYmE0OXFTVWN3UT09OndIZGVtbTdOQVlUK3Jvb2VMc0Fqbnc9PQ");
            // // Cookie cookie1 = new BasicClientCookie("JSESSIONID",
            // "t2b91t3tuji4n3bkdgahidwq");
            //
            // // HttpCookie cookie = new
            // HttpCookie("SPRING_SECURITY_REMEMBER_ME_COOKIE",
            // "akg4WEtiaEhublFQYjA4TXRzdm90dz09OjN2d0hNSkpPeGFEMGVEb2VQK1BBOFE9PQ");
            // cookieStore.addCookie(cookie);
            // // cookieStore.addCookie(cookie1);
            //
            // mDefaultHttpClient.setCookieStore(cookieStore);

            final HttpStack httpStack = new HttpClientStack(mDefaultHttpClient);
            //  HurlStack

            //uncomment
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), httpStack);

//            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),
//                    new HurlStack());

            Log.v("deekshant","getRequestQueue mRequestQueue 333 "+mRequestQueue);

            // mRequestQueue.
            // RequestQueue(new DiskBasedCache(cacheDir), network);
            // new RequestQueue(cache, network)
            //
        }

        return mRequestQueue;
    }

    public static DoorCoreController getInstance() {
        return doorAppController;
    }

}
