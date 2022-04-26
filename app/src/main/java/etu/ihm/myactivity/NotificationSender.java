package etu.ihm.myactivity;

import static etu.ihm.myactivity.Notifications.CHANNEL3_ID;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;



public class NotificationSender extends AppCompatActivity {

    private int nofiticationID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Button button = findViewById(R.id.buttonNotif);
        //button.setOnClickListener(notifListener);

    }

    private View.OnClickListener notifListener = new View.OnClickListener() {
        public void onClick(View v) {
            sendNotificationOnChannel(CHANNEL3_ID, NotificationManager.IMPORTANCE_HIGH);        }
    };

    private void sendNotificationOnChannel(String CHANNEL_ID, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logoapp2)
                .setContentTitle("Chez Jean")
                .setContentText("Venez faire un tour chez Jean")
                .setPriority(priority)
                .setTimeoutAfter(5000)
                .setAutoCancel(true); // Enlève la notification après avoir cliqué dessus
        Notifications.notificationManager.notify(++nofiticationID, notification.build());
    }
}

