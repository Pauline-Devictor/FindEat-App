package etu.ihm.myactivity.account;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import etu.ihm.myactivity.NotificationSender;
import etu.ihm.myactivity.favorites.Favorites;
import etu.ihm.myactivity.home.MainActivity;
import etu.ihm.myactivity.map.Map;
import etu.ihm.myactivity.R;

public class Account extends AppCompatActivity {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private int nofiticationID = 0;

    public final static String CHANNEL1_ID = "low channel";
    public final static String CHANNEL2_ID = "default channel";
    public final static String CHANNEL3_ID = "high channel";
    public static NotificationManager notificationManager;
    private NotificationSender notificationSender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        createNotificationChannels();
        notificationSender = new NotificationSender(getApplicationContext());
        NotificationSender.makeNotification(notificationSender);

        //Button button = findViewById(R.id.buttonNotif);
        //button.setOnClickListener(notifListener);

        bottomNavigationView.setSelectedItemId(R.id.compte);
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
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.compte:
                        return true;
                }
                return false;
            }
        });
    }

    /**
    private View.OnClickListener notifListener = new View.OnClickListener() {
        public void onClick(View v) {
            NotificationSender.sendNotificationOnChannel(CHANNEL3_ID, NotificationManager.IMPORTANCE_HIGH);
            ;}
    };
     */

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel low_channel = new NotificationChannel(CHANNEL1_ID,"channel 1",NotificationManager.IMPORTANCE_LOW);
            low_channel.setDescription("Ce channel permet l'affichage de notifications dans la barre d'état");

            NotificationChannel default_channel = new NotificationChannel(CHANNEL2_ID,"channel 2",NotificationManager.IMPORTANCE_DEFAULT);
            default_channel.setDescription("Ce channel permet l'affichage de notifications dasn la barre des sons accompagné d'un son");

            NotificationChannel high_channel = new NotificationChannel(CHANNEL3_ID,"channel 3",NotificationManager.IMPORTANCE_HIGH);
            high_channel.setDescription("Ce channel permet l'affichage de notifications dans la barre d'état ainsi que sur l'écran");

            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(low_channel);
            Objects.requireNonNull(notificationManager).createNotificationChannel(default_channel);
            Objects.requireNonNull(notificationManager).createNotificationChannel(high_channel);
        }
}
}

