package com.example.filip.transport.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filip.transport.R;

/**
 * Created by Filip on 4.2.2017..
 */

public class RadniNalogFragment extends Fragment {

    Context context;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.svi_radni_nalozi, container, false);
        context = rootView.getContext();
        return rootView;
    }




}
