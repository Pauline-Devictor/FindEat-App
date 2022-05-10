package etu.ihm.myactivity.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.restaurants.FiltreEnum;

public class FilterFragment extends Fragment {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    public static int RADIUS_MIN = 1; //radius min in km
    public static int RADIUS_MAX = 30; //radius max in km
    public static int RADIUS_DEFAULT = 5;
    public static int PRICE_MIN = 1;
    public static int PRICE_MAX = 4;
    public static int PRICE_DEFAULT = PRICE_MAX;

    private OnSubmitListener submitCallback;

    public interface OnSubmitListener {
        void onSubmit(int radius, int maxPrice, ArrayList<FiltreEnum> options);
    }

    private SeekBar radiusSeekBar;
    private TextView radiusDisplayed;
    private SeekBar maxPriceSeekBar;
    private TextView maxPriceDisplayed;
    private SwitchCompat veganSwitch;
    private SwitchCompat vegetarienSwitch;
    private SwitchCompat halalSwitch;
    private SwitchCompat casherSwitch;

    private int radius = RADIUS_DEFAULT*1000; //radius in m
    private int maxPrice = PRICE_DEFAULT;
    private ArrayList<FiltreEnum> options = new ArrayList<>();

    public FilterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "cration of filterFragment");
        View rootView = inflater.inflate(R.layout.fragment_filter, container, false);


        this.veganSwitch = rootView.findViewById(R.id.veganSwitch);
        this.vegetarienSwitch = rootView.findViewById(R.id.vegetarienSwitch);
        this.halalSwitch = rootView.findViewById(R.id.halalSwitch);
        this.casherSwitch = rootView.findViewById(R.id.casherSwitch);
        this.radiusDisplayed = rootView.findViewById(R.id.radiusValue);
        this.maxPriceDisplayed = rootView.findViewById(R.id.maxPriceValue);

        this.radiusDisplayed.setText("Distance : "+RADIUS_DEFAULT+"km");
        this.maxPriceDisplayed.setText("Prix maximum : "+PRICE_DEFAULT);
        String price = "";
        for (int k = 0; k<maxPrice; k++){
            price+="€";
        }
        this.maxPriceDisplayed.setText("Prix maximum : "+price);

        this.radiusSeekBar = rootView.findViewById(R.id.radiusSeekBar);
        this.radiusSeekBar.setMin(RADIUS_MIN);
        this.radiusSeekBar.setMax(RADIUS_MAX);
        this.radiusSeekBar.setProgress(RADIUS_DEFAULT);
        this.radiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                radiusDisplayed.setText("Distance : " + i + "km");
                radius = i*1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.maxPriceSeekBar = rootView.findViewById(R.id.maxPriceSeekBar);
        this.maxPriceSeekBar.setMin(PRICE_MIN);
        this.maxPriceSeekBar.setMax(PRICE_MAX);
        this.maxPriceSeekBar.setProgress(PRICE_DEFAULT);
        this.maxPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String s = "";
                for (int k = 0; k<i; k++){
                    s+="€";
                }
                maxPriceDisplayed.setText("Prix maximum : "+s);
                maxPrice = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.veganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    options.add(FiltreEnum.VEGAN);
                } else {
                    options.remove(FiltreEnum.VEGAN);
                }
            }
        });

        this.vegetarienSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    options.add(FiltreEnum.VEGETARIEN);
                } else {
                    options.remove(FiltreEnum.VEGETARIEN);
                }
            }
        });

        this.halalSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    options.add(FiltreEnum.HALAL);
                } else {
                    options.remove(FiltreEnum.HALAL);
                }
            }
        });

        this.casherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    options.add(FiltreEnum.CASHER);
                } else {
                    options.remove(FiltreEnum.CASHER);
                }
            }
        });

        rootView.findViewById(R.id.filterButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"submitting filters");
                submitCallback.onSubmit(radius, maxPrice, options);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubmitListener) {
            submitCallback = (OnSubmitListener) context;
        } else
            throw new RuntimeException(context.toString() + "must implement OnButtonClickedListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        submitCallback = null;
    }


}
