package etu.ihm.myactivity.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import etu.ihm.myactivity.FireBaseCommentaire;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.CommentAdapter;
import etu.ihm.myactivity.home.MainActivity;

public class CommentsActivity extends AppCompatActivity {

    private final String TAG = "polytech-" + getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        //String idResto = FireBaseCommentaire.getIdResto();


        ArrayList<Commentaire> commentaires = new ArrayList<>();
        FireBaseCommentaire data = new FireBaseCommentaire();
        data.getCommentaireById("c",commentaires);

        commentaires = data.getCommentaires();


        CommentsAdapter adapter = new CommentsAdapter(getApplicationContext(),commentaires);
        ((ListView)findViewById(R.id.listViewComments)).setAdapter(adapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.decouvrir);
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
