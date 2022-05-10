package etu.ihm.myactivity;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class FireBaseCommentaire {

    private DatabaseReference databaseReference;
    private ArrayList<Commentaire> commentaires;

    public FireBaseCommentaire() {
        FirebaseDatabase db = FirebaseDatabase.getInstance(" https://findeat-5db68-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Commentaire.class.getSimpleName());
    }

    public Task<Void> add(Commentaire com) {
        return databaseReference.push().setValue(com);
    }

    public void get(String idRestaurant) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                commentaires = new ArrayList<>();
                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    commentaires.add((Commentaire) dsp.getValue()); //add result into array list
                }
                commentaires.removeIf(id -> !id.getIdResto().equals(idRestaurant));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
