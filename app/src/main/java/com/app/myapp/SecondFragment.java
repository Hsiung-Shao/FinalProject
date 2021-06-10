package com.app.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    TextView result, constellation;
    NumberPicker month, day;
    EditText name;
    Button saveActive;
    int[] monthDay = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int[][] stellations = {{3,21,4,19},{4,20,5,20},{5,21,6,21},{6,22,7,22},{7,23,8,22},{8,23,9,22},{9,23,10,23},{10,24,11,21},{11,22,12,20},{12,21,1,20},{1,21,2,19},{2,20,3,20}};
    int[] results = {R.string.牡羊, R.string.金牛, R.string.雙子, R.string.巨蟹, R.string.獅子, R.string.處女, R.string.天秤, R.string.天蠍, R.string.射手, R.string.摩羯, R.string.水瓶, R.string.雙魚};
    String[] resultTitle = {"牡羊座", "金牛座", "雙子座", "巨蟹座", "獅子座", "處女座", "天秤座", "天蠍座", "射手座", "摩羯座", "水瓶座", "雙魚座"};
    int[] nowIndex = new int[2];
    int index = 0;
    int nowDay = 0;
    int nowMonth = 0;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        constellation = getView().findViewById(R.id.constellationTextView);
        result   = getView().findViewById(R.id.result);

        saveActive = getView().findViewById(R.id.saveStellation);
        name = getView().findViewById(R.id.nameStellationEditText);

        month = getView().findViewById(R.id.monthPicker);
        day   = getView().findViewById(R.id.dayPicker);

        month.setMaxValue(12);
        month.setMinValue(1);
        month.setValue(6);
//        month.setTextSize(60f);

        day.setMaxValue(31);
        day.setMinValue(1);
        day.setValue(15);
//        day.setTextSize(60f);

        index = 2;
        nowIndex[0] = 3;
        nowIndex[1] = 2;
        nowDay = 15;
        changeContent();

        month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                for (int i = 0; i < 12; i++){
                    if (stellations[i][0] == newVal){
                        index = i;
                        nowIndex[0] = i;
                    }
                    if (stellations[i][2] == newVal){
                        nowIndex[1] = i;
                    }
                }
                day.setMaxValue(monthDay[stellations[index][0] - 1]);
                nowMonth = newVal;
                changeContent();
            }
        });

        day.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                nowDay = newVal;
                changeContent();
            }
        });

        saveActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), StellationResultActivity.class);

                String birthday = nowMonth + "/" + nowDay;
                Log.d("name = ", name.getText().toString());
                Log.d("birthday = ", birthday);
                Log.d("stellation = ", resultTitle[index]);
                Bundle bundle = new Bundle();
                bundle.putString("NameStellation", name.getText().toString());
                bundle.putString("Birthday", birthday);
                bundle.putString("Stellation", resultTitle[index]);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    public void changeContent() {
        if (nowDay > stellations[nowIndex[1]][3]) {
            index = nowIndex[0];
        }else {
            index = nowIndex[1];
        }
        result.setText(results[index]);
        constellation.setText("星座介紹-" + resultTitle[index] + "(" + stellations[index][0] + "." + stellations[index][1] + "~" + stellations[index][2] + "." + stellations[index][3] + ")");
    }
}
