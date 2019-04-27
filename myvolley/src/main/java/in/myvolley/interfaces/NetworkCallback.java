package in.myvolley.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by rahulsharma on 28/11/17.
 */

public interface NetworkCallback<T> {
    void onSuccess(T t);

    void onError(VolleyError error);
}
