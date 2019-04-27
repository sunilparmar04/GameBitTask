package com.test.gambit.interfaces;

import com.android.volley.VolleyError;

public interface NetworkCallback<T> {
    void onSuccess(T t);

    void onError(VolleyError error);
}
