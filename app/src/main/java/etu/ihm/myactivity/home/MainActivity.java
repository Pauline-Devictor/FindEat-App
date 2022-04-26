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

import java.util.ArrayList;

import etu.ihm.myactivity.GoogleAPI;
import etu.ihm.myactivity.LocationGPS;
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

    private int nofiticationID = 0;

    //Découvrir == Home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        new GoogleAPI("Valbonne").start();

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

        //getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantListFragment).commit();
    }

    @Override
    public void onClickRestaurant(int position) {
        Bundle args = new Bundle();
        args.putInt("position",position);


        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }

    @Override
    public void onRestaurantClicked(int position){
        Bundle args = new Bundle();
        args.putInt("position",position);
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).commit();
    }

  /*  public static void main(String[] args) throws Throwable {
        Lieux lieux1 = LieuxFactory.build("a",1,null,"description",2,null);
        Lieux lieux2 = LieuxFactory.build("b",2,null,"description",2,null);

    }*/
}