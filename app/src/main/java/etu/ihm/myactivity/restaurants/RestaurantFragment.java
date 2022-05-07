package etu.ihm.myactivity.restaurants;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.home.MainActivity;
import etu.ihm.myactivity.home.RestaurantsList;

import androidx.fragment.app.Fragment;
//import android.app.Fragment;

public class RestaurantFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private Button putInFavoritesButton;
    private Button seeCommentsButton;
    private Button showOnMapButton;

    private Lieux restaurant;

    public RestaurantFragment() {
    }

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);


        //int position = getArguments().getInt("position");
        restaurant = (Lieux) getArguments().getSerializable("resto");


        putInFavoritesButton = rootView.findViewById(R.id.favoriteButton);
        putInFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put in favorites
            }
        });

        seeCommentsButton = rootView.findViewById(R.id.commentsButton);
        seeCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //see comments
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            showOnMapButton = rootView.findViewById(R.id.showOnMapButton);
            showOnMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //see on map
                }
            });
        }

        TextView name = rootView.findViewById(R.id.restaurantName);
        name.setText(restaurant.getName());

        TextView grade = rootView.findViewById(R.id.restaurantGrade);
        grade.setText(""+restaurant.getRate()+"/5");

        TextView distance = rootView.findViewById(R.id.restaurantDistance);
        distance.setText("xkm");

        return rootView;
    }

}
