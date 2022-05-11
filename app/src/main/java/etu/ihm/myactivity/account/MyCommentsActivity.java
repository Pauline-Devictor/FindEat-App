package etu.ihm.myactivity.account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.home.MainActivity;

public class MyCommentsActivity extends AppCompatActivity {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycomments);


        MyCommentsList myComments = new MyCommentsList();
        ArrayList<MyComments> commentList = myComments.commentList;
        Log.d(TAG,"comment List retrieved: "+commentList.size());

        int max = getIntent().getIntExtra("value",0);
        Log.d(TAG,"max="+max);
        CommentAdapter adapter = new CommentAdapter(getApplicationContext(),commentList,max);
        Log.d(TAG,"adapter created");
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);
        Log.d(TAG,"adapter set");


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
