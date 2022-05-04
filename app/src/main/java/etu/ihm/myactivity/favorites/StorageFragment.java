package etu.ihm.myactivity.favorites;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.factoryTests.Lieux;

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
        ContextWrapper contextWrapper = new ContextWrapper(getContext());
        directoryName = contextWrapper.getDir("RestoDir", ContextWrapper.MODE_PRIVATE).getPath(); //chemin par defaut fichier


        buttonLoad = rootView.findViewById(R.id.buttonLoad);
        buttonSave = rootView.findViewById(R.id.buttonSave);

        return rootView;

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
