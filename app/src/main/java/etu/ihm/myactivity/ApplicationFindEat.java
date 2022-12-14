package etu.ihm.myactivity;

import android.app.Application;
import android.util.Log;
import android.view.ViewGroup;

/**
 * @author Alexis Roche
 * @author Alexandre Nunes Dias
 * @author Pauline Devictor
 * @author Mathieu Schalkwijk
 */
public class ApplicationFindEat extends Application {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"starting application");
    }

    public <T extends ViewGroup> void onViewCreated(T layout) {
    }

}