package etu.ihm.myactivity.home;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;

import com.google.android.gms.common.GooglePlayServicesManifestException;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import etu.ihm.myactivity.GoogleAPI;
import etu.ihm.myactivity.LocationGPS;
import etu.ihm.myactivity.home.IListner;
import etu.ihm.myactivity.restaurants.DataBase;
import etu.ihm.myactivity.map.Map;
import etu.ihm.myactivity.Notifications;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.restaurants.FiltreEnum;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

public class MainActivity extends AppCompatActivity implements IListner, RestaurantListFragment.OnRestaurantClickedListener {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private RestaurantFragment restaurantFragment;
    private RestaurantListFragment restaurantListFragment;

    public RestaurantsList restaurantsList;

    private int nofiticationID = 0;

    //Découvrir == Home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"creation of MainActivity");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantsList = new RestaurantsList();

        double latitude = 43.6221174;
        double longitude = 7.0391009;
        int radius = 3000; //3km

        //////https://www.youtube.com/watch?v=6wkTrbZqgqc //// PARSE JSON


        LocationGPS location = new LocationGPS(latitude,longitude);
        ArrayList<FiltreEnum> filtres = new ArrayList<>();
        filtres.add(FiltreEnum.VEGAN);
        int maxPrice =4; //valeur par defaut pour tout avoir

        //GoogleAPI googleAPI = new GoogleAPI("Valbonne");
        //googleAPI.runDebut();

        new GoogleAPI("Valbonne",restaurantsList).start();

        //new GoogleAPI(radius,location,filtres,maxPrice);
        Log.d("a","passe ici");



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.decouvrir);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.decouvrir:
                        return true;
                    case R.id.carte:
                        startActivity(new Intent(getApplicationContext(), Map.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.favoris:
                        startActivity(new Intent(getApplicationContext(), DataBase.class));
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

        restaurantFragment = new RestaurantFragment();
        restaurantListFragment = new RestaurantListFragment();


        Bundle args = new Bundle();
        args.putSerializable("restoList",restaurantsList);
        restaurantListFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantListFragment).commit();
    }

    @Override
    public void onClickRestaurant(int position) {
        /*Bundle args = new Bundle();
        args.putInt("position",position);
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();*/

        Bundle args = new Bundle();
        args.putSerializable("resto",restaurantsList.get(position));
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }

    @Override
    public void onRestaurantClicked(int position){
        /*Bundle args = new Bundle();
        args.putInt("position",position);
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();*/

        Bundle args = new Bundle();
        args.putSerializable("resto",restaurantsList.get(position));
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }

    private void sendNotificationOnChannel(String CHANNEL_ID, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logoapp2)
                .setContentTitle("Nom du resto")
                .setContentText("Venez faire un tour " + "vous attend")
                .setPriority(priority)
                .setTimeoutAfter(5000)
                .setAutoCancel(true); // Enlève la notification après avoir cliqué dessus
        Notifications.notificationManager.notify(++nofiticationID, notification.build());
    }
}