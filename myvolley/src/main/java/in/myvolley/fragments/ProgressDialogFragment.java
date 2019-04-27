package in.myvolley.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.myvolley.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressDialogFragment extends DialogFragment {
    private RelativeLayout relativeLayout;
    private TextView textView;

    public ProgressDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_progress_dialog, container, false);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        relativeLayout = getView().findViewById(R.id.relativeLayout);
        textView = getView().findViewById(R.id.textViewDescription);
        relativeLayout.setMinimumWidth(getActivity().getWindowManager().getDefaultDisplay().getWidth());

        String title = getArguments().getString("TITLE", "loading...");
        textView.setText(title);

    }
}
