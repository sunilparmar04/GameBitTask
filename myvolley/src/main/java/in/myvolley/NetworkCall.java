package in.myvolley;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.myvolley.fragments.ProgressDialogFragment;
import in.myvolley.interfaces.NetworkCallback;
import in.myvolley.util.DoorCoreVolley;
import in.myvolley.util.Logger;
import in.myvolley.util.Utility;


public class NetworkCall<T> implements Parcelable {
    public static final Creator<NetworkCall> CREATOR = new Creator<NetworkCall>() {
        @Override
        public NetworkCall createFromParcel(Parcel source) {
            return new NetworkCall(source);
        }

        @Override
        public NetworkCall[] newArray(int size) {
            return new NetworkCall[size];
        }
    };
    private static final String TAG = NetworkCall.class.getName();
    private String url;
    private Map<String, String> params = new HashMap<>();
    private int requestMethod;
    private NetworkCallback networkCallback;
    private Class<T> clazz;

    public NetworkCall(String url, Map<String, String> params, int requestMethod, NetworkCallback networkCallback, Class<T> clazz) {
        this.url = url;
        if (params == null)
            this.params = new HashMap<>();
        else
            this.params = params;
        this.requestMethod = requestMethod;
        this.networkCallback = networkCallback;
        this.clazz = clazz;
    }

    protected NetworkCall(Parcel in) {
        this.url = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, String>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.params.put(key, value);
        }
        this.requestMethod = in.readInt();
        this.clazz = (Class<T>) in.readSerializable();
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    private void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    private void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    private NetworkCallback getNetworkCallback() {
        return networkCallback;
    }

    private void setNetworkCallback(NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
    }

    public void initiateCall(String title, FragmentManager fragmentManager) {
        //DoorCoreUtilities.getInstance().showProgressDialog(fragmentManager, TAG, title);

        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        final ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.setArguments(bundle);
        progressDialogFragment.show(fragmentManager, TAG);

        DoorCoreVolley<T> doorCoreVolley = new DoorCoreVolley<T>(Utility.getHeaders()) {
            @Override
            public String getURL() {
                return url;
            }

            @Override
            public void onResponseString(String response) {
                Logger.dd("Pawan", response);
                //addNotification(response);
            }

            @Override
            public void onResponseJsonObject(JSONObject response) {

            }

            @Override
            public void handleError(VolleyError error) {

                try {

                    progressDialogFragment.dismiss();
                    super.handleError(error);
                    networkCallback.onError(error);

                    if (error != null) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            String jsonError = new String(networkResponse.data);
                            Log.e("Pawan", jsonError);
                            //addNotification(jsonError);
                        }

                        if (error.getClass().equals(TimeoutError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Timeout error!", DoorAppController.getInstance());
                        }

                        if (error.getClass().equals(AuthFailureError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Auth Failure error!", DoorAppController.getInstance());
                          //  Utility.getInstance().autoLogin();
                        }

                        if (error.getClass().equals(NoConnectionError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Network issue!", DoorAppController.getInstance());
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Crashlytics.logException(e);
                }
            }

            @Override
            public void JSONModel(T jsonModel) {
                try {
                    progressDialogFragment.dismiss();
                    networkCallback.onSuccess(jsonModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Crashlytics.logException(e);
                }
            }

            @Override
            public Map<String, String> getAdditionalParams() {
                HashMap<String, String> params = (HashMap<String, String>) super.getAdditionalParams();
                if (getParams() != null)
                    params.putAll(getParams());

                return params;
            }
        };

        doorCoreVolley.sendStringRequest(requestMethod, clazz);
    }

    /**
     * used for simple network call
     */
    public void initiateCall() {
        DoorCoreVolley<T> doorCoreVolley = new DoorCoreVolley<T>(Utility.getHeaders()) {
            @Override
            public String getURL() {
                return url;
            }

            @Override
            public void onResponseString(String response) {
                //addNotification(response);

            }

            @Override
            public void onResponseJsonObject(JSONObject response) {

            }

            @Override
            public void handleError(VolleyError error) {
                try {
                    super.handleError(error);
                    networkCallback.onError(error);

                    if (error != null) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.data != null) {
                            String jsonError = new String(networkResponse.data);
                            Log.e("Pawan", jsonError);
                            //addNotification(jsonError);
                        }

                        if (error.getClass().equals(TimeoutError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Timeout error!", DoorAppController.getInstance());
                        }

                        if (error.getClass().equals(AuthFailureError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Auth Failure error!", DoorAppController.getInstance());
                          //  Utility.getInstance().autoLogin();
                        }

                        if (error.getClass().equals(NoConnectionError.class)) {
                            //CommonUtil.getInstance().showRedToast("Oops. Network issue!", DoorAppController.getInstance());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //Crashlytics.logException(e);
                }

            }

            @Override
            public void JSONModel(T jsonModel) {
                try {
                    networkCallback.onSuccess(jsonModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Crashlytics.logException(e);
                }

            }

            @Override
            public Map<String, String> getAdditionalParams() {
                HashMap<String, String> params = (HashMap<String, String>) super.getAdditionalParams();
                if (getParams() != null)
                    params.putAll(getParams());
                return params;
            }
        };

        doorCoreVolley.sendStringRequest(requestMethod, clazz);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeInt(this.requestMethod);
        dest.writeSerializable(this.clazz);
    }


}
