package etu.ihm.myactivity.restaurants;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.home.RestaurantsList;

import androidx.fragment.app.Fragment;
//import android.app.Fragment;

public class RestaurantFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private Button back;
    private Button putInFavorites;
    private Button seeComments;
    private Button showOnMap;

    private Lieux restaurant;

    public RestaurantFragment() {
    }

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);


        //int position = getArguments().getInt("position");
        restaurant = (Restaurant) getArguments().getSerializable("resto");

        rootView.findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go back on restaurants list
            }
        });

        rootView.findViewById(R.id.favoriteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put in favorites
            }
        });

        rootView.findViewById(R.id.commentsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //see comments
            }
        });

        rootView.findViewById(R.id.showOnMapButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //see on map
            }
        });

        TextView name = rootView.findViewById(R.id.restaurantName);
        name.setText(restaurant.getName());

        TextView grade = rootView.findViewById(R.id.restaurantGrade);
        grade.setText(""+restaurant.getGrade()+"/5");

        TextView distance = rootView.findViewById(R.id.restaurantDistance);
        distance.setText("xkm");

        TextView description = rootView.findViewById(R.id.restaurantDescription);
        //description.setText(restaurant.getDescription());


        return rootView;
    }

}
