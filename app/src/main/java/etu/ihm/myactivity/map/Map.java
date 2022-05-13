package etu.ihm.myactivity.map;

import androidx.appcompat.app.AppCompatActivity;

/**
 * No longer used (fragment instead)
 */
public class Map extends AppCompatActivity {
    private final String TAG = "polytech-" + getClass().getSimpleName();
/*
    public static int REQUEST_LOCATION_CODE = 1001;
    public static float DEFAULT_ZOOM = 14f; //entre 0 et 25

    private MapView map;
    private IMapController mapController; //gere les options de la map == zoom et centre au lancement
    private double userLatitude = 0;
    private double userLongitude = 0;
    private RestaurantsList restaurantsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "starting map activity");
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        userLatitude = intent.getDoubleExtra("userLatitude",0);
        userLongitude = intent.getDoubleExtra("userLongitude",0);
        Log.d(TAG,"lat et long récupérés depuis Map : " + userLatitude + " " + userLongitude);
        restaurantsList = (RestaurantsList)intent.getExtras().get("restoList");

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

        findViewById(R.id.focusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapController.setCenter(new GeoPoint(userLatitude, userLongitude));
            }
        });

        map = findViewById(R.id.map);          //On cherche la map via son ID
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
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() { //Pour pouvoir mettre aussi la map en pause car elle demande bcp de ressources
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() { //Reprendre la map et la page
        super.onResume();
        map.onResume();
    }

    private Marker addMarker(int icon, GeoPoint location, String title, int imageResource) {
        Marker marker = new Marker(map);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getDrawable(icon));
        marker.setPosition(location);
        marker.setTitle(title);
        marker.setImage(getDrawable(imageResource));
        marker.setPanToView(true);  //the map will be centered on the marker position.
        marker.setDraggable(true);
        return marker;
    }

    private void addElement(){
        for (int i =0;i<restaurantsList.size();i++){//TODO recuperer liste des restos quand on créé l'activité
            Lieux resto = restaurantsList.getRestaurant(i);
            if (resto instanceof Restaurants ){
            map.getOverlays().add(addMarker(R.drawable.restaurant_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_home));
            Log.d("MAP","Element à afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
        }
            else {
                map.getOverlays().add(addMarker(R.drawable.bar_position, new GeoPoint(resto.getLatitude(), resto.getLongitude()), resto.getName(), R.drawable.ic_home));
                Log.d("MAP","Element à afficher lat " + resto.getLatitude() + " long " + resto.getLongitude()+" nom "+resto.getName());
            }
            //Le new Geo Point compile pas ici  !!!!
            //map.getOverlays().add(addMarker(R.drawable.bar_position, null, resto.getName(), R.drawable.ic_home));

        }
    }
    */
}
