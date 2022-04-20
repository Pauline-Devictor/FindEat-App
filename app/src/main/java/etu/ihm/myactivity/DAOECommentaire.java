package etu.ihm.myactivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOECommentaire {

    private DatabaseReference databaseReference;

    public DAOECommentaire(){
        FirebaseDatabase db = FirebaseDatabase.getInstance(" https://findeat-5db68-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(Commentaire.class.getSimpleName());
    }

    public Task<Void> add(Commentaire com){
        return databaseReference.push().setValue(com);
    }


}
