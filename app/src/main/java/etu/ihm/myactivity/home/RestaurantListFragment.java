package etu.ihm.myactivity.home;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

import androidx.fragment.app.Fragment;
//import android.app.Fragment;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantListFragment extends Fragment implements IListner {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private Button button;

    private OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        void onButtonClicked();
    }


    public RestaurantListFragment() {}

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        button = rootView.findViewById(R.id.buttonresto);

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(getActivity());

        ListView list = rootView.findViewById(R.id.restoList);

        list.setAdapter(restaurantsAdapter);

        restaurantsAdapter.addListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onButtonClicked();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickedListener){
            mCallback = (OnButtonClickedListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallback=null;
    }

    @Override
    public void onClickRestaurant(int position) {
        Toast toast = Toast.makeText(getActivity(), "restaurant " + position, Toast.LENGTH_SHORT);
        toast.show();
    }



}
