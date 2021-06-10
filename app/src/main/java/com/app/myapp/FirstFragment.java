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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    TextView result;
    EditText height, kg, name;
    Button count, save;
    double sum;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 在這個方法中取得並定義Fragment的介面元件
        super.onActivityCreated(savedInstanceState);

        result = getView().findViewById(R.id.resultTextView);

        name   = getView().findViewById(R.id.nameEdit);
        height = getView().findViewById(R.id.heightTextView);
        kg     = getView().findViewById(R.id.kgTextView);

        count = getView().findViewById(R.id.countButton);
        save  = getView().findViewById(R.id.saveBMIData);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), BMIResultActivity.class);

                DecimalFormat df = new DecimalFormat("0.00");
                String msg = df.format(sum);

                Bundle bundle = new Bundle();
                bundle.putString("Name", name.getText().toString());
                bundle.putString("Height", height.getText().toString());
                bundle.putString("Weight", kg.getText().toString());
                bundle.putString("BMI", msg);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStringText = height.getText().toString();
                String kgStringText = kg.getText().toString();
                Log.d("height", heightStringText);
                Log.d("kg", kgStringText);
                result.setText(heightStringText + " - " + kgStringText);

                DecimalFormat df = new DecimalFormat("0.00");
                if(heightStringText.equals("") || kgStringText.equals("")) {
                    Toast.makeText(getContext(), "尚未輸入完成", Toast.LENGTH_LONG).show();

                }else {
                    double h = Double.parseDouble(heightStringText);
                    double k = Double.parseDouble(kgStringText);
                    sum = k / Math.pow(h, 2) * 100 * 100;
                    if (sum < 18.5) {
                        result.setText("BMI: " + df.format(sum) + " 體重過輕！");
                    }else if(sum >= 18.5 && sum < 24) {
                        result.setText("BMI: " + df.format(sum) + " 體重正常！");
                    }else if(sum >= 24) {
                        result.setText("BMI: " + df.format(sum) + " 體重過重！");
                    }else if(sum > 27) {
                        result.setText("BMI: " + df.format(sum) + " 輕度肥胖！");
                    }else if(sum >= 30 && sum <= 35) {
                        result.setText("BMI: " + df.format(sum) + " 中度肥胖！");
                    }else {
                        result.setText("BMI: " + df.format(sum) + " 重度肥胖！");
                    }
                }
            }
        });
    }
}
