package etu.ihm.myactivity.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import etu.ihm.myactivity.R;

public class FilterFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    public FilterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "cration of filterFragment");

        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);

        return rootView;
    }


}
