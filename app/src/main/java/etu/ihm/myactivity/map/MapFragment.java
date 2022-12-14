package etu.ihm.myactivity.map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.Serializable;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.Restaurants;
import etu.ihm.myactivity.factoryTests.RestoBar;
import etu.ihm.myactivity.home.RestaurantsList;
import etu.ihm.myactivity.restaurants.Restaurant;

public class MapFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    public interface OnSeeRestaurantDetailsClickedListener{
        void onSeeRestaurantDetailsClicked(String restoId);
    }

    private OnSeeRestaurantDetailsClickedListener restoCallback;

    public static float DEFAULT_ZOOM = 14f; //entre 0 et 25
    public static float FOCUS_ZOOM = 19f;

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

        userLatitude = getArguments().getDouble("userLatitude");
        userLongitude = getArguments().getDouble("userLongitude");

        restaurantsList = (RestaurantsList) getArguments().getSerializable("restoList");

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

        map.getOverlays().add(addMarker(R.drawable.ic_userping2, new GeoPoint(userLatitude, userLongitude), "Votre position", R.drawable.person, "user"));
        Log.i(TAG,"setting user ping at "+userLatitude+" "+userLongitude);

        Lieux lieux = (Lieux) getArguments().getSerializable("restoToFocus");
        if (lieux!=null){
            restaurantsList.add(lieux);
            GeoPoint restoToFocus = new GeoPoint(lieux.getLatitude(),lieux.getLongitude());
            Marker marker = addMarker(R.drawable.focus_position, restoToFocus, lieux.getName(), lieux instanceof RestoBar ? R.drawable.ic_restaurant : R.drawable.ic_bar, lieux.getPlaceID());
            map.getOverlays().add(marker);
            mapController.setCenter(restoToFocus);
            mapController.setZoom(FOCUS_ZOOM);
            marker.showInfoWindow();

        }

        return rootView;
    }

    private Marker addMarker(int icon, GeoPoint location, String title, int imageResource, String id) {
        Marker marker = new Marker(map);
        if (!title.equals("Votre position")) {
            MarkerInfoWindow markerInfoWindow = new MarkerInfoWindow(R.layout.map_info_window, map) {
                @Override
                public void onOpen(Object item) {
                    closeAllInfoWindowsOn(map);
                    super.onOpen(item);
                    Button openFragmentResto = mView.findViewById(R.id.show_restaurant);
                    openFragmentResto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "clicked on a restaurant");
                            restoCallback.onSeeRestaurantDetailsClicked(id);
                        }
                    });
                    mView.findViewById(R.id.close_infowindow).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            close();
                        }
                    });
                    mView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return false;
                        }
                    });
                }
            };
            marker.setInfoWindow(markerInfoWindow);
        }

        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getActivity().getDrawable(icon));
        marker.setPosition(location);
        marker.setTitle(title);
        marker.setImage(getActivity().getDrawable(imageResource));
        marker.setPanToView(true);  //the map will be centered on the marker position.
        marker.setDraggable(true);
        return marker;
    }

    private void addElement(){
        for (int i =0;i<restaurantsList.size();i++){//TODO recuperer liste des restos quand on cr???? l'activit??
            Lieux resto = restaurantsList.getRestaurant(i);
            if (resto instanceof Restaurants){
                map.getOverlays().add(addMarker(R.drawable.restaurant_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_restaurant, resto.getPlaceID()));
                Log.d("MAP","Element ?? afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
            }
            else {
                map.getOverlays().add(addMarker(R.drawable.bar_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_bar, resto.getPlaceID()));
                Log.d("MAP","Element ?? afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
            }
            //Le new Geo Point compile pas ici  !!!!
            //map.getOverlays().add(addMarker(R.drawable.bar_position, null, resto.getName(), R.drawable.ic_home));

        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnSeeRestaurantDetailsClickedListener){
            restoCallback = (OnSeeRestaurantDetailsClickedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnSeeRestaurantDetailsClickedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        restoCallback = null;
    }
}
