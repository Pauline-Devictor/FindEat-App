package etu.ihm.myactivity.home;



import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.Serializable;
import java.util.ArrayList;

import etu.ihm.myactivity.FireBaseCommentaire;
import etu.ihm.myactivity.GoogleAPI;
import etu.ihm.myactivity.LocationGPS;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.RestoBar;
import etu.ihm.myactivity.favorites.Favorites;
import etu.ihm.myactivity.favorites.IStorageActivity;
import etu.ihm.myactivity.favorites.StorageFragment;
import etu.ihm.myactivity.map.MapFragment;
import etu.ihm.myactivity.restaurants.Commentaire;
import etu.ihm.myactivity.restaurants.DataBase;
import etu.ihm.myactivity.map.Map;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.restaurants.FiltreEnum;
import etu.ihm.myactivity.restaurants.RestaurantFragment;


public class MainActivity extends AppCompatActivity implements RestaurantListFragment.OnRestaurantClickedListener, RestaurantListFragment.OnFilterClickedListener, FilterFragment.OnSubmitListener, IStorageActivity, StorageFragment.OnFavoriteClickedListener, RestaurantFragment.OnSeeOnMapClickedListener {
    private final String TAG = "polytech-" + getClass().getSimpleName();
    public static int REQUEST_LOCATION_CODE = 1001;

    private static MainActivity instance;

    private RestaurantFragment restaurantFragment;
    private RestaurantListFragment restaurantListFragment;
    private MapFragment mapFragment;
    private FilterFragment filterFragment;
    private static StorageFragment storageFragment;

    BottomNavigationView bottomNavigationView;

    private int orientation;

    public boolean displayMap;
    public boolean displayFav;

    public RestaurantsList restaurantsList;

    private double userLatitude = 0;
    private double userLongitude = 0;
    private int radius = 5000; //5km
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    private int nofiticationID = 0;

    //Découvrir == Home
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "creation of MainActivity");

        Intent intent = getIntent();
        displayMap = intent.getBooleanExtra("displayMap",false);
        displayFav = intent.getBooleanExtra("displayFav",false);

        setContentView(R.layout.activity_main);

        Log.d(TAG, "content view set");


/**
        FireBaseCommentaire Fcom = new FireBaseCommentaire();
        Commentaire com = new Commentaire("a","b","c");
        Fcom.add(com);
        ArrayList<Commentaire> data = new ArrayList<>();
        Fcom.getCommentaireById("c",data);
 **/

        restaurantFragment = new RestaurantFragment();
        restaurantListFragment = new RestaurantListFragment();
        storageFragment = new StorageFragment(this);
        mapFragment = new MapFragment();
        filterFragment = new FilterFragment();

        this.orientation = getResources().getConfiguration().orientation;


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        retrieveLocation();

        instance = this;

        restaurantsList = new RestaurantsList();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (this.displayMap){
            bottomNavigationView.setSelectedItemId(R.id.carte);
        } else if (this.displayFav){
            bottomNavigationView.setSelectedItemId(R.id.favoris);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.decouvrir);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.decouvrir:
                        displayRestaurantsList();
                        return true;
                    case R.id.carte:
                        displayMap();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.favoris:
                        //startActivity(new Intent(getApplicationContext(), Favorites.class));
                        displayFavoris();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.compte:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        //displayRestaurantsList();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    private void displayRestaurantsList(){
        Bundle args = new Bundle();
        args.putSerializable("restoList", (Serializable) restaurantsList);
        restaurantListFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantListFragment).addToBackStack(null).commit();
    }

    private void displayFavoris(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, storageFragment).addToBackStack(null).commit();
    }

    private void displayRestaurant(int position){
        Bundle args = new Bundle();
        args.putSerializable("resto", (Serializable) restaurantsList.getRestaurant(position));
        restaurantFragment = new RestaurantFragment();
        restaurantFragment.setArguments(args);
        if (this.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.sideInfo, restaurantFragment).commit();
        }
    }

    private void displayRestaurant(Lieux lieux){
        Bundle args = new Bundle();
        args.putSerializable("resto", (Serializable) lieux);
        restaurantFragment = new RestaurantFragment();
        restaurantFragment.setArguments(args);
        if (this.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.sideInfo, restaurantFragment).commit();
        }
    }

    private void displayMap(){
        Bundle args = new Bundle();
        args.putSerializable("restoList", (Serializable) restaurantsList);
        args.putDouble("userLatitude", userLatitude);
        args.putDouble("userLongitude", userLongitude);
        mapFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, mapFragment).addToBackStack(null).commit();
    }

    private void displayMapFocused(Lieux lieux){
        bottomNavigationView.setSelectedItemId(R.id.carte);
        Bundle args = new Bundle();
        args.putSerializable("restoList", (Serializable) restaurantsList);
        args.putDouble("userLatitude", userLatitude);
        args.putDouble("userLongitude", userLongitude);
        args.putSerializable("restoToFocus",(Serializable) lieux);
        mapFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, mapFragment).addToBackStack(null).commit();
    }

    private void displayFilter(){
        filterFragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putDouble("userLatitude", userLatitude);
        args.putDouble("userLongitude", userLongitude);
        mapFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, filterFragment).addToBackStack(null).commit();
    }

    public void displayAfterLoad(){
        if (displayMap){
            displayMap = false;
            displayMap();
        } else if (displayFav){
            displayFav = false;
            displayFavoris();
        } else {
            displayRestaurantsList();
        }
    }

    @Override
    public void onRestaurantClicked(int position) {
        displayRestaurant(position);
    }

    @Override
    public void onFilterClicked(){
        displayFilter();
    }

    private void retrieveLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "requesting permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            return;
        }
        Log.d(TAG, "authorized to ask location");

        //check if gps is enable
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "gps is not enable");
            buildAlertMessageNoGps();
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d(TAG, "locationresult null");
                    return;
                }

                Task<Location> locationTask = fusedLocationProviderClient.getLastLocation(); // already checked the permission
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Log.d(TAG, "latitude " + location.getLatitude() + " longitude " + location.getLongitude());
                            userLatitude = location.getLatitude();
                            userLongitude = location.getLongitude();
                            ArrayList<FiltreEnum> filtres = new ArrayList<>();
                            //filtres.add(FiltreEnum.VEGAN);
                            LocationGPS locationGPS = new LocationGPS(userLatitude,userLongitude);
                            new GoogleAPI(restaurantsList,radius,locationGPS,filtres,4, instance).start();
                        } else {
                            Log.d(TAG, "cannot retrieve location");
                            Toast.makeText(getApplicationContext(),"cannot retrieve location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "on faillure " + e.getLocalizedMessage());
                        Toast.makeText(getApplicationContext(),"Cannot retrieve location",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };

        Log.d(TAG, "asking location");
        LocationRequest locationRequest = LocationRequest.create();
        //locationRequest.setInterval(100000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                retrieveLocation();
            } else {
                Log.d(TAG, "permission not granted");
            }
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Votre GPS est désactivé. Voulez-vous l'activer ?")
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onSubmit(int radius, int maxPrice, ArrayList<FiltreEnum> options){
        Log.d(TAG,"fetching restaurants after filter : "+radius+" "+maxPrice+" "+options.size());
        LocationGPS locationGPS = new LocationGPS(userLatitude,userLongitude);
        new GoogleAPI(restaurantsList,radius,locationGPS,options,maxPrice,this).start();
        displayRestaurantsList();
    }


    public static StorageFragment getStorageFragment(){
        return storageFragment;
    }

    @Override
    public void onFavoriteClicked(Lieux lieux) {
        displayRestaurant(lieux);
    }

    @Override
    public void onSeeOnMapClicked(Lieux lieux) {
        displayMapFocused(lieux);
    }
}