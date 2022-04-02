package etu.ihm.myactivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import etu.ihm.myactivity.Account;
import etu.ihm.myactivity.Favorites;
import etu.ihm.myactivity.MainActivity;

public class Map extends AppCompatActivity {
    private final String TAG = "polytech-" + getClass().getSimpleName();
    private MapView map;
    private IMapController mapController; //gere les options de la map == zoom et centre au lancement
    private double userlatitude=0;
    private double userlongitude=0;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        map = findViewById(R.id.map);          //On cherche la map via son ID
        map.setTileSource(TileSourceFactory.MAPNIK);//render de la map
        map.setBuiltInZoomControls(false);           //rendre la map zoomable
        map.setMultiTouchControls(true); //zoom avec les doigts
        Log.d(TAG,"point de départ configuré");
        mapController = map.getController();
        mapController.setZoom(18.0);//nb float compris entre 0 et 25

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    userlatitude=location.getLatitude();
                    userlongitude=location.getLongitude();
                    Log.d(TAG,"latitude "+userlatitude);
                    GeoPoint startPoint = new GeoPoint(userlatitude,userlongitude);
                    mapController.setCenter(startPoint);
                }
            }
        });

        ArrayList<OverlayItem> items = new ArrayList<>(); //Liste d'element qu'on pourra afficher sur la carte
        //creation d'un element
        OverlayItem ralloOffice = new OverlayItem("Rallo's Office","His office",new GeoPoint(43.65020,7.00517));//Titre, Sous-titre, Position géographique
        Drawable m = ralloOffice.getMarker(0); //TODO sert à changer la forme du marqueur
        items.add(ralloOffice);
        items.add(new OverlayItem("Resto","chez babar",new GeoPoint(43.64950,7.00517)));

        //Objet contenant la liste des elements ainsi que les actions auxquels ils reagissent -> ici TapUp() et LongPress()
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

        mOverlay.setFocusItemsOnTap(true); //voir la description des elements en appuyant dessus
        map.getOverlays().add(mOverlay); // relier les items à la map
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


}
