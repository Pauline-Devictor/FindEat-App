package etu.ihm.myactivity.restaurants;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.favorites.IStorageActivity;
import etu.ihm.myactivity.favorites.StorageFragment;
import etu.ihm.myactivity.home.MainActivity;
import etu.ihm.myactivity.home.RestaurantsList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.osmdroid.util.GeoPoint;
//import android.app.Fragment;

public class RestaurantFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private double userLatitude;
    private double userLongitude;
    private double restaurantLatitude;
    private double restaurantLongitude;

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

        Log.d(TAG,"RestaurantFragment created");

        //int position = getArguments().getInt("position");
        restaurant = (Lieux) getArguments().getSerializable("resto");

        userLatitude = getArguments().getDouble("lat");
        Log.d(TAG,"userLat : "+userLatitude);
        userLongitude  = getArguments().getDouble("long");
        Log.d(TAG,"userLong : "+userLongitude);
        restaurantLatitude = this.restaurant.getLatitude();
        Log.d(TAG,"restoLat : "+restaurantLatitude);
        restaurantLongitude = this.restaurant.getLongitude();
        Log.d(TAG,"restoLong : "+restaurantLongitude);


        putInFavoritesButton = rootView.findViewById(R.id.favoriteButton);
        putInFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Faudrait checker si il est en favori ou pas pour savoir des le debut quel texte mettre
                Log.d(TAG,"On va me put en favori");

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            IStorageActivity.REQUEST_MEDIA_WRITE);
                }
                    else{
                        Log.d(TAG,"permission ok");
                    StorageFragment storageFragment = MainActivity.getStorageFragment();
                    Log.d(TAG,"on a get storageFragment");
                    storageFragment.addInFavorite(restaurant);
                }

                putInFavoritesButton.setText("En favori");
                Log.d(TAG,"Fin ajout favori depuis restoFragment");

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
        grade.setText("Note : "+restaurant.getRate()+"/5");

        TextView distance = rootView.findViewById(R.id.restaurantDistance);
        DecimalFormat df = new DecimalFormat("0.00");
        distance.setText(String.format("Distance : %.1fkm", restaurant.getDistance()));

        return rootView;
    }

    /**
     * @return distance between the user and the restaurant in km
     */
    private double distance(){
        GeoPoint userPoint = new GeoPoint(userLatitude,userLongitude);
        GeoPoint restoPoint = new GeoPoint(restaurantLatitude,restaurantLongitude);
        double res = userPoint.distanceToAsDouble(restoPoint);
        Log.d(TAG,"distance to the user in m : "+ res);
        return res/1000.;
    }

}
