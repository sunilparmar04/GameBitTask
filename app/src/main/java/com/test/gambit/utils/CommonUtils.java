package com.test.gambit.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.gambit.R;


public class CommonUtils {

    public static CommonUtils mInstance;
    public static int RED_COLOR = Color.argb(0xFF, 0xE1, 0x43, 0x1f);

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }


    public static synchronized CommonUtils getInstance() {
        if (mInstance == null) {
            mInstance = new CommonUtils();
        }
        return mInstance;
    }

    public void showRedToast(String textToShow, Context ctx, int topOffset) {

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.custome_toast, null);
        layout.setBackgroundColor(RED_COLOR);

        TextView text = (TextView) layout.findViewById(R.id.textToShow);
        text.setPadding(10, 0, 10, 0);
        // Set the Text to show in TextView
        text.setText(textToShow);

        Toast toast = new Toast(ctx);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, topOffset);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

}