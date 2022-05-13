package etu.ihm.myactivity.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import etu.ihm.myactivity.FireBaseCommentaire;
import etu.ihm.myactivity.R;
import etu.ihm.myactivity.account.CommentAdapter;
import etu.ihm.myactivity.home.MainActivity;

public class CommentsActivity extends AppCompatActivity {

    private final String TAG = "polytech-" + getClass().getSimpleName();
    private ArrayList<Commentaire> commentaires;
    private FireBaseCommentaire data;
    private ListView listView;
    private ArrayAdapter<Commentaire> adapter;
    private Button bouton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        //String idResto = FireBaseCommentaire.getIdResto();

        data = new FireBaseCommentaire();

        commentaires = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.favorite_text_list, commentaires);

        listView = this.findViewById(R.id.listCom);
        listView.setAdapter(adapter);

        data.getCommentaireById(FireBaseCommentaire.getIdResto(),this);
        Log.d("e","passe ici BIS, id "+FireBaseCommentaire.getIdResto());

        bouton = this.findViewById(R.id.buttonAddCom);


        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddCommentaire.class);
                startActivity(i);
            }
        });


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


    @Override
    public void onResume() {
        super.onResume();
        Log.d("e","on resume");
        data.getCommentaireById(FireBaseCommentaire.getIdResto(),this);
    }






    public void upDateData(){
        commentaires.clear();
        Log.d("e","upDataData"+commentaires.size());

        for(Commentaire t : data.getCommentaires()){
            commentaires.add(t);
        }

        adapter.notifyDataSetChanged();
        Log.d("e","upDataData"+commentaires.size());
        listView.invalidateViews();
    }
}
