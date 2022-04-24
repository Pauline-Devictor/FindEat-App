package etu.ihm.myactivity.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import etu.ihm.myactivity.favorites.Favorites;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.home.MainActivity;

public class Map extends AppCompatActivity {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    public static int REQUEST_LOCATION_CODE = 1001;

    private MapView map;
    private IMapController mapController; //gere les options de la map == zoom et centre au lancement
    private double userlatitude = 0;
    private double userlongitude = 0;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "starting map activity");
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(
                getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_map); //Relier l'activite à son visuel
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.carte);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.decouvrir:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.carte:
                        return true;
                    case R.id.favoris:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        map = findViewById(R.id.map);          //On cherche la map via son ID
        map.setTileSource(TileSourceFactory.MAPNIK);//render de la map
        map.setBuiltInZoomControls(false);           //rendre la map zoomable
        map.setMultiTouchControls(true); //zoom avec les doigts
        mapController = map.getController();
        mapController.setZoom(18.0);//nb float compris entre 0 et 25


        //Pour ajouter un point avec un ping particulier :
        map.getOverlays().add(addMarker(R.drawable.restaurant_position,new GeoPoint(43.65020, 7.00517),"Rallo's office","Rallo Home",R.drawable.ic_home ));
       // map.getOverlays().add(addMarker(R.drawable.restaurant_position,new GeoPoint(43.64950, 7.00517),"Chez Babar","De bons petits plats",R.drawable.ic_home));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            askLastLocationPermission();
        }
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"requesting permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            return;
        }
        Log.d(TAG,"authorized to ask location");

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d(TAG,"locationresult null");
                    return;
                }
                Log.d(TAG,"locationresult not null");
                for (Location location : locationResult.getLocations()) {
                    Log.d(TAG,"lat "+location.getLatitude()+" long "+location.getLongitude());
                }

                Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!=null){
                            Log.d(TAG,"latitude "+location.getLatitude()+" longitude "+location.getLongitude());
                            userlatitude=location.getLatitude();
                            userlongitude=location.getLongitude();

                            GeoPoint userPing = new GeoPoint(userlatitude,userlongitude);
                            mapController.setCenter(userPing);
                            OverlayItem overlayPing = new OverlayItem("me","ping",userPing);
                            overlayPing.setMarker(getApplicationContext().getResources().getDrawable(R.drawable.ic_userping));

                            ArrayList<OverlayItem> items = new ArrayList<>();
                            items.add(overlayPing);
                            ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),items,new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
                                @Override
                                public boolean onItemSingleTapUp(int index, OverlayItem item){
                                    return true;
                                }

                                @Override
                                public boolean onItemLongPress(int index, OverlayItem item){
                                    return false;
                                }
                            });

                            mOverlay.setFocusItemsOnTap(true);

                            map.getOverlays().add(addMarker(R.drawable.ic_userping,new GeoPoint(userlatitude,userlongitude),"Votre position","", R.drawable.person));
                        }
                        else {
                            Log.d(TAG,"failure");
                        }
                    }
                });
                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"on faillure "+e.getLocalizedMessage());
                    }
                });

            }
        };

        Log.d(TAG,"asking location");
        LocationRequest locationRequest = LocationRequest.create();
        //locationRequest.setInterval(100000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    public void askLastLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Log.d(TAG,"asklocationpermission");
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else{
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
            else{
                Log.d(TAG,"permission not granted");
            }
        }
    }

    @Override
    public void onPause(){ //Pour pouvoir mettre aussi la map en pause car elle demande bcp de ressources
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume(){ //Reprendre la map et la page
        super.onResume();
        map.onResume();
    }

    private Marker addMarker(int icon, GeoPoint location, String title, String description, int imageResource){
        Marker marker = new Marker(map);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getDrawable(icon));
        marker.setPosition(location);
        marker.setTitle(title);
        marker.setSubDescription(description);
        marker.setImage(getDrawable(imageResource));
        marker.setPanToView(true);  //the map will be centered on the marker position.
        marker.setDraggable(true);
        return marker;
    }

}
