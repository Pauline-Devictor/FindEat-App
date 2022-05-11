package etu.ihm.myactivity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.home.MainActivity;

public class MyCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomments);


        MyCommentsList myComments = new MyCommentsList();

        CommentAdapter adapter = new CommentAdapter(getApplicationContext(),myComments.subList(0,1));
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
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
}
