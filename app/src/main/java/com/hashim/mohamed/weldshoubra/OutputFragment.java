package com.hashim.mohamed.weldshoubra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by mohamed on 17/02/17.
 */
public class OutputFragment extends Fragment {

    ImageView out_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.output_fragment, container, false);
        out_img = (ImageView) root.findViewById(R.id.out_img);
        return root;
    }
}