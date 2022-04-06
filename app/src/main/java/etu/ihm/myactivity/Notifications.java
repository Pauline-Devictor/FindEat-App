package etu.ihm.myactivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Objects;

public class Notifications extends AppCompatActivity {

    public final static String CHANNEL1_ID = "low channel";
    public final static String CHANNEL2_ID = "default channel";
    public final static String CHANNEL3_ID = "high channel";
    public static NotificationManager notificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel low_channel = new NotificationChannel(CHANNEL1_ID,"channel 1",NotificationManager.IMPORTANCE_LOW);
            low_channel.setDescription("Ce channel permet l'affichage de notifications dans la barre d'état");
            NotificationChannel default_channel = new NotificationChannel(CHANNEL2_ID,"channel 1",NotificationManager.IMPORTANCE_LOW);
            default_channel.setDescription("Ce channel permet l'affichage de notifications dasn la barre des sons accompagné d'un son");
            NotificationChannel high_channel = new NotificationChannel(CHANNEL3_ID,"channel 1",NotificationManager.IMPORTANCE_LOW);
            high_channel.setDescription("Ce channel permet l'affichage de notifications dans la barre d'état ainsi que sur l'écran");
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(low_channel);
            Objects.requireNonNull(notificationManager).createNotificationChannel(default_channel);
            Objects.requireNonNull(notificationManager).createNotificationChannel(high_channel);
        }
    }
}
