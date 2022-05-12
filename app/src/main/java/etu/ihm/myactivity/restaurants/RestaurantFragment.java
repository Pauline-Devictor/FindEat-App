package etu.ihm.myactivity.restaurants;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import etu.ihm.myactivity.GoogleAPI;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.favorites.IStorageActivity;
import etu.ihm.myactivity.favorites.StorageFragment;
import etu.ihm.myactivity.home.MainActivity;
import etu.ihm.myactivity.home.RestaurantListFragment;

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
    private boolean estFavori;
    private StorageFragment storageFragment;
    private static ImageView imageView;

    private Lieux restaurant;

    public OnSeeOnMapClickedListener seeOnMapCallback;

    public interface OnSeeOnMapClickedListener {
        void onSeeOnMapClicked(Lieux lieux);
    }

    public RestaurantFragment() {
    }

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);
        storageFragment = MainActivity.getStorageFragment();

        Log.d(TAG,"RestaurantFragment created");

        //int position = getArguments().getInt("position");
        restaurant = (Lieux) getArguments().getSerializable("resto");

        Log.d("n","start");
        new GoogleAPI(this.restaurant.getPicture()).start();
        Log.d("fin","fin");

        imageView = rootView.findViewById(R.id.imageView);

        estFavori = storageFragment.estFavori(restaurant.getName());

        putInFavoritesButton = rootView.findViewById(R.id.favoriteButton);

        if(estFavori){
            putInFavoritesButton.setText("Retirer des favoris");
            putInFavoritesButton.setBackgroundColor(Color.parseColor("#F0B27A"));
        }
        else{
            putInFavoritesButton.setText("Mettre en favori");
            putInFavoritesButton.setBackgroundColor(Color.parseColor("#1B90CF"));
        }


        putInFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"On va me put en favori");
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, IStorageActivity.REQUEST_MEDIA_WRITE);
                }
                    else{
                    Log.d(TAG, "permission ok, ajout/sup favori");

                    if(estFavori){
                        storageFragment.deleteFavorite(restaurant);
                        putInFavoritesButton.setText("Mettre en favori");
                        putInFavoritesButton.setBackgroundColor(Color.parseColor("#1B90CF"));


                    }
                        else {
                            storageFragment.addInFavorite(restaurant);
                            putInFavoritesButton.setText("Retirer des favoris");
                            putInFavoritesButton.setBackgroundColor(Color.parseColor("#F0B27A"));


                    }
                }
                    estFavori = !estFavori;
                Log.d(TAG,"Fin ajout/sup favori depuis restoFragment");
            }
        });

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            showOnMapButton = rootView.findViewById(R.id.showOnMapButton);
            showOnMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    seeOnMap();
                }
            });

            seeCommentsButton = rootView.findViewById(R.id.commentsButton);
            seeCommentsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //see comments
                }
            });

        }

        TextView name = rootView.findViewById(R.id.restaurantName);
        name.setText(restaurant.getName());

        TextView addr = rootView.findViewById(R.id.ouvert);
        if(restaurant.isOpenNow()){
            addr.setText("Restaurant ouvert");
            addr.setBackgroundColor(Color.parseColor("#52BE80"));
        }
        else{
            addr.setText("Restaurant fermé");
            addr.setBackgroundColor(Color.parseColor("#CD6155"));
        }

        TextView grade = rootView.findViewById(R.id.restaurantGrade);
        grade.setText("Note : "+restaurant.getRate()+"/5");

        TextView distance = rootView.findViewById(R.id.restaurantDistance);
        distance.setText(String.format("Distance : %.1fkm", restaurant.getDistance()));

        return rootView;
    }

    public static void updateBitmap(Bitmap image){
        if(image==null){ Log.d("a","bitmap null ici "); }
        Activity activity = MainActivity.getInstance();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(image);
            }
        });

        Log.d("a","passe set bitmpa");

    }

    public void seeOnMap(){
        seeOnMapCallback.onSeeOnMapClicked(this.restaurant);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSeeOnMapClickedListener) {
            seeOnMapCallback = (OnSeeOnMapClickedListener) context;
        } else
            throw new RuntimeException(context.toString() + "must implement OnSeeOnMapClickedListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        seeOnMapCallback = null;
    }


}
