package etu.ihm.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        (findViewById(R.id.navAccount)).setOnClickListener(click -> {
            Intent intentSend = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intentSend);
        });
        (findViewById(R.id.navMap)).setOnClickListener(click -> {
            Intent intentSend = new Intent(getApplicationContext(),Map.class);
            startActivity(intentSend);
        });
        (findViewById(R.id.navFav)).setOnClickListener(click -> {
            Intent intentSend = new Intent(getApplicationContext(),Favorites.class);
            startActivity(intentSend);
        });
        (findViewById(R.id.navAccount)).setOnClickListener(click -> {
            Intent intentSend = new Intent(getApplicationContext(),Account.class);
            startActivity(intentSend);
        });
    }
}