package etu.ihm.myactivity.restaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import etu.ihm.myactivity.FireBaseCommentaire;
import etu.ihm.myactivity.R;

public class DataBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        final EditText edit_data = findViewById(R.id.edit_data);
        Button btn = findViewById(R.id.btn_submit);
        FireBaseCommentaire dao =  new FireBaseCommentaire();
        btn.setOnClickListener(v->{

            Commentaire com = new Commentaire("Moi",edit_data.getText().toString(),"IdResto");
            dao.add(com).addOnSuccessListener(suc->
            {
                Log.d("OK","bon");
                Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Log.d("OK","nope");

                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
            });

        });
    }
}