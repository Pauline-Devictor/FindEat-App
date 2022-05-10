package etu.ihm.myactivity.map;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.Restaurants;
import etu.ihm.myactivity.home.RestaurantsList;

public class MapFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    public static float DEFAULT_ZOOM = 14f; //entre 0 et 25

    private MapView map;
    private IMapController mapController; //gere les options de la map == zoom et centre au lancement
    private double userLatitude = 0;
    private double userLongitude = 0;
    private RestaurantsList restaurantsList;

    public MapFragment(){
        Log.i(TAG, "starting map fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        restaurantsList = (RestaurantsList) getArguments().getSerializable("restoList");
        userLatitude = getArguments().getDouble("userLatitude");
        userLongitude = getArguments().getDouble("userLongitude");

        Configuration.getInstance().load(
                getActivity().getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));

        rootView.findViewById(R.id.focusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapController.setCenter(new GeoPoint(userLatitude, userLongitude));
            }
        });

        map = rootView.findViewById(R.id.map);          //On cherche la map via son ID
        map.setTileSource(TileSourceFactory.MAPNIK);//render de la map
        map.setBuiltInZoomControls(false);           //rendre la map zoomable
        map.setMultiTouchControls(true); //zoom avec les doigts
        mapController = map.getController();
        mapController.setZoom(DEFAULT_ZOOM);//nb float compris entre 0 et 25
        mapController.setCenter(new GeoPoint(userLatitude, userLongitude));

        //Pour ajouter un point avec un ping particulier :
        Log.d("MAP", "User : " + userLatitude + " et " + userLongitude);
        addElement();

        map.getOverlays().add(addMarker(R.drawable.ic_userping2, new GeoPoint(userLatitude, userLongitude), "Votre position", R.drawable.person));
        Log.i(TAG,"setting user ping at "+userLatitude+" "+userLongitude);

        return rootView;
    }

    private Marker addMarker(int icon, GeoPoint location, String title, int imageResource) {
        Marker marker = new Marker(map);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getActivity().getDrawable(icon));
        marker.setPosition(location);
        marker.setTitle(title);
        /*
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        */
        //MarkerInfoWindow markerInfoWindow = new MarkerInfoWindow(R.layout.fragment_vide,map);
        //marker.setInfoWindow(markerInfoWindow);
        /*
        marker.setInfoWindow(new InfoWindow() {
        } {
            @Override
            public void onOpen(Object item) {
                Log.d(TAG,"a");
            }

            @Override
            public void onClose() {
                Log.d(TAG,"b");
            }
        });
        */
        marker.setImage(getActivity().getDrawable(imageResource));
        marker.setPanToView(true);  //the map will be centered on the marker position.
        marker.setDraggable(true);
        return marker;
    }

    private void addElement(){
        for (int i =0;i<restaurantsList.size();i++){//TODO recuperer liste des restos quand on créé l'activité
            Lieux resto = restaurantsList.getRestaurant(i);
            if (resto instanceof Restaurants){
                map.getOverlays().add(addMarker(R.drawable.restaurant_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_restaurant));
                Log.d("MAP","Element à afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
            }
            else {
                map.getOverlays().add(addMarker(R.drawable.bar_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_bar));
                Log.d("MAP","Element à afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
            }
            //Le new Geo Point compile pas ici  !!!!
            //map.getOverlays().add(addMarker(R.drawable.bar_position, null, resto.getName(), R.drawable.ic_home));

        }
    }

}
