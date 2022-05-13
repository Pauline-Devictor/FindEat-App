package etu.ihm.myactivity.account;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import etu.ihm.myactivity.NotificationSender;
import etu.ihm.myactivity.home.MainActivity;
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

        Intent HomeActivityIntent = new Intent(getApplicationContext(),MainActivity.class);
        HomeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingHomeActivityIntent = PendingIntent.getActivity(getApplicationContext(),1,HomeActivityIntent,PendingIntent.FLAG_ONE_SHOT);

        createNotificationChannels();
        notificationSender = new NotificationSender(getApplicationContext());


        Button notifybutton = findViewById(R.id.buttonNotif2);
        notifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationSender.makeNotification(notificationSender,pendingHomeActivityIntent);
            }
        });

        Button commentbutton = findViewById(R.id.buttonNotif);
        commentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyCommentsActivity.class);
                startActivity(intent);
            }
        });

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
                        Intent intentMap = new Intent(getApplicationContext(), MainActivity.class);
                        intentMap.putExtra("displayMap",true);
                        startActivity(intentMap);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favoris:
                        Intent intentFav = new Intent(getApplicationContext(), MainActivity.class);
                        intentFav.putExtra("displayFav",true);
                        startActivity(intentFav);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.compte:
                        return true;
                }
                return false;
            }
        });
    }

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

