package etu.ihm.myactivity.favorites;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.LieuxFactory;
import etu.ihm.myactivity.factoryTests.Restaurants;
import etu.ihm.myactivity.factoryTests.RestaurationFactory;
import etu.ihm.myactivity.home.MainActivity;

public class StorageFragment extends Fragment {
    private IStorageActivity activity;
    private Button buttonSave;
    private Button buttonLoad;
    private TextView textView;
    private Lieux restaurant;
    private String directoryName;
    private static final String filename="data.dat";
    private Set<Lieux> mesRestaurantsFavoris;

    public StorageFragment() { }

    public StorageFragment(IStorageActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_storage, container, false);
        Log.d("a", "PASSE");

        ContextWrapper contextWrapper = new ContextWrapper(getContext());
        directoryName = contextWrapper.getDir("RestoDir", ContextWrapper.MODE_PRIVATE).getPath(); //chemin par defaut fichier


        buttonLoad = rootView.findViewById(R.id.buttonLoad);
        buttonSave = rootView.findViewById(R.id.buttonSave);
        textView = rootView.findViewById(R.id.favorisText);

        mesRestaurantsFavoris = new HashSet<>();



        //loadRestoFromStorage();


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("a", "CLICK");
                restaurant = new Restaurants("test", "id", true, null, 5.0, 55, 44, 2);
                if (restaurant != null) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                IStorageActivity.REQUEST_MEDIA_WRITE);
                    } else { //Permission ok
                        saveToInternalStorage(restaurant);
                    }
                }
            }
        });


        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("a", "CLICK LOAD");
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                IStorageActivity.REQUEST_MEDIA_READ);
                    } else { //Permission ok
                        loadRestoFromStorage();
                    }
                }

        });


        return rootView;

    }


    private void loadRestoFromStorage() {

        textView.setText("");
        Log.d("a", "paaseLOAD");

        File file = new File(directoryName, filename);
        String nomToutResto = "";





        try {
            FileInputStream file_input_stream = new FileInputStream(file);
            ObjectInputStream object_input_stream = new ObjectInputStream(file_input_stream);
             mesRestaurantsFavoris = (Set<Lieux>) object_input_stream.readObject();
            object_input_stream.close();
            file_input_stream.close();

            Log.d("a", "Fin loading, on a nb resto = "+mesRestaurantsFavoris.size());

            for(Lieux l : mesRestaurantsFavoris){
                Log.d("a",l.toString());
                nomToutResto+=" "+l.getName();
            }

            Log.d("a", "fin fun");


        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        textView.setText(nomToutResto);

        Toast.makeText(getContext(), "Favoris chargés", Toast.LENGTH_LONG).show();

    }

    private void saveToInternalStorage(Lieux resto) {
        //Faire un fichier par resto ?
        Log.d("a", "Save, on va add "+resto.getName());

        mesRestaurantsFavoris.add(resto);

        File file = new File(directoryName, filename);

        Log.d("a","path "+file.getAbsolutePath());
        Log.d("a","on sauv "+mesRestaurantsFavoris.size());



        FileOutputStream file_output_stream = null;
        ObjectOutputStream object_output_stream = null;

        try {
            file_output_stream = new FileOutputStream(file);
            object_output_stream = new ObjectOutputStream(file_output_stream);
            object_output_stream.writeObject(mesRestaurantsFavoris);

            object_output_stream.close();

        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

        Toast.makeText(getContext(), "Favori ajouté", Toast.LENGTH_LONG).show();


        Log.d("a", "finito");

    }









    public Button getButtonSave() {
        return buttonSave;
    }

    public void setButtonSave(Button buttonSave) {
        this.buttonSave = buttonSave;
    }

    public Button getButtonLoad() {
        return buttonLoad;
    }

    public void setButtonLoad(Button buttonLoad) {
        this.buttonLoad = buttonLoad;
    }


    public Lieux getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Lieux restaurant) {
        this.restaurant = restaurant;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}
