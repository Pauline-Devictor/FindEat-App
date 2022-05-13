package etu.ihm.myactivity;

import android.app.PendingIntent;
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


    public void sendNotificationOnChannel(String CHANNEL_ID, int priority,PendingIntent pendingIntent) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.mockrestaurant);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logoapp2)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText("FindEat"))
                .setContentTitle("Une petite faim ?")
                .setContentText("De nombreux restaurants vous attendent")
                .setContentIntent(pendingIntent)
                .setPriority(priority)
                .setTimeoutAfter(10000)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_logoapp,"Découvrir",pendingIntent);


        NotificationManagerCompat.from(context).notify(nofiticationID, notification.build());
    }

    public static void makeNotification(NotificationSender notificationSender, PendingIntent pendingIntent){
        int priority = 2;
        notificationSender.sendNotificationOnChannel(CHANNEL3_ID,priority,pendingIntent);
    }
}

