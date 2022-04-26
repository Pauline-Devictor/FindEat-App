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

public class MainActivity extends AppCompatActivity implements IListner {
    private final String TAG = "polytech-" + getClass().getSimpleName();
    private int nofiticationID=0;
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

        GoogleAPI googleAPI = new GoogleAPI("Valbonne");
        googleAPI.runDebut();

        //new GoogleAPI(radius,location,filtres,maxPrice);
        Log.d("a","passe ici");



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.decouvrir);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.decouvrir:
                        return true;
                    case R.id.carte:
                        startActivity(new Intent(getApplicationContext(), Map.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favoris:
                        startActivity(new Intent(getApplicationContext(),DataBase.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.compte:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(getApplicationContext());

        ListView list = findViewById(R.id.restoList);

        list.setAdapter(restaurantsAdapter);

        restaurantsAdapter.addListener(this);

    }

    @Override
    public void onClickRestaurant(int position) {
        //TODO: start fragment
        Toast toast = Toast.makeText(getApplicationContext(),"restaurant "+position,Toast.LENGTH_SHORT);
        toast.show();
    }

    private void sendNotificationOnChannel(String CHANNEL_ID,int priority){
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logoapp2)
                .setContentTitle("Nom du resto")
                .setContentText("Venez faire un tour " + "vous attend")
                .setPriority(priority)
                .setTimeoutAfter(5000)
                .setAutoCancel(true); // Enlève la notification après avoir cliqué dessus
        Notifications.notificationManager.notify(++nofiticationID, notification.build());
    }
}