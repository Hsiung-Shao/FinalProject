package com.app.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.

 */
public class ThreeFragment extends Fragment {

    ImageView imageView;
    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 在這個方法中取得並定義Fragment的介面元件
        super.onActivityCreated(savedInstanceState);

        imageView = getView().findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.item01);
    }
}