package in.myvolley.util;

import android.os.Build;

import java.util.HashMap;
import java.util.Map;

import in.myvolley.BuildConfig;

public class Utility {

    public static Map<String, String> getHeaders() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization","prj_test_sk_bf0f51d233d00360e32ebdfd4e6e6443e6172547");
      //  headerMap.put("User-Agent", "" + Build.MANUFACTURER + "-android-" + Build.VERSION.SDK_INT + " " + BuildConfig.VERSION_NAME + " - " + "language " + DoorCoreSharedUtility.getInstance().readPreferenceData(FieldController.getInstance(), Constants.SELECTED_LANGUAGE, "en"));
        return headerMap;
    }
}
