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
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
    private String restoName;
    private Lieux restaurant;
    private String directoryName;

    public StorageFragment() {
    }

    public StorageFragment(IStorageActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_storage, container, false);
        restoName = "ChezRallo";
        Log.d("a","PASSE");

        ContextWrapper contextWrapper = new ContextWrapper(getContext());
        directoryName = contextWrapper.getDir("RestoDir", ContextWrapper.MODE_PRIVATE).getPath(); //chemin par defaut fichier


        buttonLoad = rootView.findViewById(R.id.buttonLoad);
        buttonSave = rootView.findViewById(R.id.buttonSave);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("a","CLICK");
                restaurant = new Restaurants("test","id",true,null,5.0,55,44,2);
                if(restaurant!=null){
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                IStorageActivity.REQUEST_MEDIA_WRITE);
                    }
                    else{ //Permission ok
                        saveToInternalStorage(restaurant);
                    }
                }
            }
        });


        return rootView;

    }


    private Lieux loadRestoFromStorage() {
        File file = new File(directoryName, restoName);
        Toast.makeText(getContext(), "Resto Load", Toast.LENGTH_LONG).show();
        RestaurationFactory restaurationFactory = new RestaurationFactory();
        return null;
    }

    private void saveToInternalStorage(Lieux resto){
        //Faire un fichier par resto ?
        Log.d("a","paaseSasve");

        String filecontent ="";
         filecontent = resto.toString().trim();
         String filename = "data";

        FileOutputStream fos = null;

         File myExternalFile = new File(directoryName,filename);

        Log.d("a","dirname"+directoryName);


        try{
            Log.d("a","passetryWrite");

            fos = new FileOutputStream(myExternalFile);
             fos.write(filecontent.getBytes(StandardCharsets.UTF_8));

             Toast.makeText(getContext(),"file Ajouter",Toast.LENGTH_LONG).show();

            Log.d("a","Finito ");




        } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }


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

    public String getRestoName() {
        return restoName;
    }

    public void setRestoName(String restoName) {
        this.restoName = restoName;
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
