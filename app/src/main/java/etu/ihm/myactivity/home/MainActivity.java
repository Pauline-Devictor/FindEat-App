package etu.ihm.myactivity.home;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import etu.ihm.myactivity.restaurants.DataBase;
import etu.ihm.myactivity.map.Map;
import etu.ihm.myactivity.Notifications;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.Account;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

public class MainActivity extends AppCompatActivity implements /*IListner*/ RestaurantListFragment.OnButtonClickedListener {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private RestaurantFragment restaurantFragment;
    private RestaurantListFragment restaurantListFragment;

    private int nofiticationID = 0;

    //Découvrir == Home
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, restaurantListFragment).commit();
    }
/*
    @Override
    public void onClickRestaurant(int position) {
        Toast toast = Toast.makeText(getApplicationContext(),"restaurant "+position,Toast.LENGTH_SHORT);
        toast.show();
        Fragment restaurantFragment= new RestaurantFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_restaurant, restaurantFragment);
        transaction.commit();
    }
 */

    @Override
    public void onButtonClicked(){
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