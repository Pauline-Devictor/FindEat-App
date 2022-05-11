package etu.ihm.myactivity;

import android.util.Log;
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
    private ArrayList<Commentaire> commentaires = new ArrayList<>();

    public FireBaseCommentaire() {
        FirebaseDatabase db = FirebaseDatabase.getInstance(" https://findeat-5db68-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Commentaire.class.getSimpleName());
    }

    public Task<Void> add(Commentaire com) {
        Log.d("c","dans ADD ");
        return databaseReference.push().setValue(com);

    }

    public void getCommentaireById(String idRestaurant, ArrayList<Commentaire> data) {
        data.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Commentaire tmp = snapshot.getValue(Commentaire.class);
                    Log.d("h","devb"+tmp.toString());

                    if(tmp.getIdResto().equals(idRestaurant)){
                        Log.d("h","passeIf");
                        data.add(tmp);
                        Log.d("c","ComSize vaut"+data.size());
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        }
        );
    }







    public void getCommentaireByName(String nomUtilisateur, ArrayList<Commentaire> data) {
        data.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                             @Override
                                                             public void onDataChange(DataSnapshot dataSnapshot) {
                                                                 for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                     Commentaire tmp = snapshot.getValue(Commentaire.class);
                                                                     Log.d("h","devb"+tmp.toString());

                                                                     if(tmp.getAuteur().equals(nomUtilisateur)){
                                                                         Log.d("h","passeIf");
                                                                         data.add(tmp);
                                                                         Log.d("c","ComSize vaut"+data.size());
                                                                     }
                                                                 }

                                                             }
                                                             @Override
                                                             public void onCancelled(@NonNull DatabaseError error) {
                                                             }
                                                         }
        );
    }




    public ArrayList<Commentaire> getCommentaires() {
        return commentaires;
    }
}
