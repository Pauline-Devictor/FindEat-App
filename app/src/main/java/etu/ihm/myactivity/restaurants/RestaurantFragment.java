package etu.ihm.myactivity.restaurants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import etu.ihm.myactivity.R;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;

public class RestaurantFragment extends Fragment {

    private Restaurant restaurant;

    public RestaurantFragment(){}

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);
        return rootView;
    }

}
