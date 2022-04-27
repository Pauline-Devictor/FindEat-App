package etu.ihm.myactivity.home;

import static etu.ihm.myactivity.Notifications.CHANNEL3_ID;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;

import etu.ihm.myactivity.GoogleAPI;
import etu.ihm.myactivity.LocationGPS;
import etu.ihm.myactivity.favorites.Favorites;
import etu.ihm.myactivity.restaurants.DataBase;
import etu.ihm.myactivity.map.Map;
import etu.ihm.myactivity.Notifications;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.restaurants.FiltreEnum;
import etu.ihm.myactivity.restaurants.RestaurantFragment;


public class MainActivity extends AppCompatActivity implements IListner, RestaurantListFragment.OnRestaurantClickedListener {
    private final String TAG = "polytech-" + getClass().getSimpleName();


    private static MainActivity instance;

    private RestaurantFragment restaurantFragment;
    private RestaurantListFragment restaurantListFragment;

    public RestaurantsList restaurantsList;

    private double userLatitude=0;
    private double userLongitude=0;
    private int radius = 3000; //3km

    private int nofiticationID = 0;

    //Découvrir == Home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;

        Log.d(TAG,"creation of MainActivity");

        restaurantsList = new RestaurantsList();

        ArrayList<FiltreEnum> filtres = new ArrayList<>();
        filtres.add(FiltreEnum.VEGAN);

        new GoogleAPI("Valbonne",restaurantsList).start();


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

        restaurantFragment = new RestaurantFragment();
        restaurantListFragment = new RestaurantListFragment();


        Bundle args = new Bundle();
        args.putSerializable("restoList", (Serializable) restaurantsList);
        restaurantListFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantListFragment).commit();
    }

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    public void onClickRestaurant(int position) {
        Bundle args = new Bundle();
        args.putSerializable("resto", (Serializable) restaurantsList.get(position));
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }

    @Override
    public void onRestaurantClicked(int position){
        Bundle args = new Bundle();
        args.putSerializable("resto", (Serializable) restaurantsList.get(position));
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }


}