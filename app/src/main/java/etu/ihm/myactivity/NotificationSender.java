package etu.ihm.myactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NotificationSender extends AppCompatActivity {

    public final static String CHANNEL1_ID = "low channel";
    public final static String CHANNEL2_ID = "default channel";
    public final static String CHANNEL3_ID = "high channel";
    private int nofiticationID = 0;
    private final Context context;

    public NotificationSender(Context context) {
        this.context = context;
    }

    public void sendNotificationOnChannel(String CHANNEL_ID, int priority) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.mockrestaurant);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logojpeg)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText("FindEat"))
                .setContentTitle("Chez Jean")
                .setContentText("Venez faire un tour chez Jean")
                .setPriority(priority)
                .setTimeoutAfter(10000)
                .setAutoCancel(true); // Enlève la notification après avoir cliqué dessus

        NotificationManagerCompat.from(context).notify(nofiticationID, notification.build());
    }

    public static void makeNotification(NotificationSender notificationSender){
        int priority = 2;
        notificationSender.sendNotificationOnChannel(CHANNEL3_ID,priority);
    }
}

