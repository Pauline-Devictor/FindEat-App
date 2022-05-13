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
import etu.ihm.myactivity.restaurants.CommentsActivity;

public  class FireBaseCommentaire {

    private DatabaseReference databaseReference;

    private static String idResto;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    private static ArrayList<Commentaire> commentaires = new ArrayList<>();


    public FireBaseCommentaire() {
        FirebaseDatabase db = FirebaseDatabase.getInstance(" https://findeat-5db68-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Commentaire.class.getSimpleName());
    }

    public Task<Void> add(Commentaire com) {
        Log.d("c","dans ADD ");
        return databaseReference.push().setValue(com);

    }




    public void doTest(){

    }


    public void getCommentaireById(String idRestaurant, CommentsActivity act) {
        Log.d("r","id init Resto "+idRestaurant);
        commentaires.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Commentaire tmp = snapshot.getValue(Commentaire.class);
                    Log.d("h","devb"+tmp.toString());

                    if(tmp.getIdResto().equals(idRestaurant)){
                        Log.d("h","passeIf on a "+tmp.getIdResto()+" "+idRestaurant);
                        commentaires.add(tmp);
                        Log.d("c","ComSize vaut"+commentaires.size());
                    }
                }
                Log.d("e","notifu DOne");

                if(act==null)return;
                act.upDateData();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        }
        );

    }







    public void getCommentaireByName(String nomUtilisateur) {
        commentaires.clear();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                             @Override
                                                             public void onDataChange(DataSnapshot dataSnapshot) {
                                                                 for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                     Commentaire tmp = snapshot.getValue(Commentaire.class);
                                                                     Log.d("h","devb"+tmp.toString());

                                                                     if(tmp.getAuteur().equals(nomUtilisateur)){
                                                                         Log.d("h","passeIf");
                                                                         Log.d("c","ComSize vaut"+commentaires.size());
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
    public static String getIdResto() {
        return idResto;
    }

    public static void setIdResto(String idResto) {
        FireBaseCommentaire.idResto = idResto;
    }

}
