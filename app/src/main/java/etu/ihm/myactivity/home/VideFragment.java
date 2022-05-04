package etu.ihm.myactivity.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import etu.ihm.myactivity.R;

public class VideFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"creation of vide fragment");
        View rootView = inflater.inflate(R.layout.fragment_vide, container, false);
        return rootView;
    }

}
