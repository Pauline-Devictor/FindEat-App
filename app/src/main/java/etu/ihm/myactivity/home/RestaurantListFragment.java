package etu.ihm.myactivity.home;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.restaurants.Restaurant;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

import androidx.fragment.app.Fragment;
//import android.app.Fragment;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * This is the view of the MVC
 */
public class RestaurantListFragment extends Fragment implements IListner, Observer {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private RestaurantsAdapter restaurantsAdapter;

    private OnRestaurantClickedListener mCallback;

    private FilterController filterController; //the controller

    public interface OnRestaurantClickedListener {
        void onRestaurantClicked(int position);
    }


    public RestaurantListFragment() {
    }

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"cration of RestaurantListFragment");
        RestaurantsList restaurantsList = (RestaurantsList) getArguments().getSerializable("restoList");
        Log.d(TAG,"récupération de restoList "+restaurantsList.get(0));

        filterController = new FilterController(this, restaurantsList);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        restaurantsAdapter = new RestaurantsAdapter(getActivity(), restaurantsList);

        ListView list = rootView.findViewById(R.id.restoList);

        list.setAdapter(restaurantsAdapter);

        restaurantsAdapter.addListener(this);

        rootView.findViewById(R.id.over3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clicked on filter over 3
                filterController.filterOver3();
            }
        });

        rootView.findViewById(R.id.over4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clicked on filter over 4
                filterController.filterOver4();
            }
        });

        return rootView;

    }

    @Override
    public void update(Observable observable, Object object) {
        Log.d(TAG,"updating observer");
        restaurantsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRestaurantClickedListener) {
            mCallback = (OnRestaurantClickedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onClickRestaurant(int position) {
        Toast toast = Toast.makeText(getActivity(), "restaurant " + position, Toast.LENGTH_SHORT);
        toast.show();
        mCallback.onRestaurantClicked(position);
    }


}
