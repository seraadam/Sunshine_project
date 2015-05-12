package com.shape.in.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle extras = getActivity().getIntent().getExtras();

        TextView weatherdata = (TextView) rootview.findViewById(R.id.weatherdata) ;
        Toast.makeText(getActivity(),extras.toString(),Toast.LENGTH_SHORT).show();
        weatherdata.setText(extras.toString());
        return rootview;
    }
}
