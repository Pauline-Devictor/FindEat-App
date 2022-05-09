package etu.ihm.myactivity.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.home.MainActivity;
import etu.ihm.myactivity.map.Map;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

/**
 * This class is not used anymore (fragment instead)
 */
public class Favorites extends AppCompatActivity{
    private final String TAG = "polytech-" + getClass().getSimpleName();

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.favoris);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.decouvrir:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.carte:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("displayMap",true);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favoris:
                        return true;
                    case R.id.compte:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


    public void displayRestaurant(Lieux lieux){
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putSerializable("resto", (Serializable) lieux);
        args.putDouble("lat",0);
        args.putDouble("long",0);
        restaurantFragment = new RestaurantFragment();
        restaurantFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantFragment).addToBackStack(null).commit();
    }

    @Override
    public void onFavoriteClicked(Lieux lieux) {
        displayRestaurant(lieux);
    }
    */
}


