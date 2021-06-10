package com.app.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMIResultActivity extends AppCompatActivity {

    EditText nameEdit, heightEdit, weightEdit, bmiEdit;
    Button save, load, clear, back;

    private String name, height, weight, bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i_result);

        nameEdit    = findViewById(R.id.nameEditText);
        heightEdit  = findViewById(R.id.heightEditText);
        weightEdit  = findViewById(R.id.weightEditText);
        bmiEdit     = findViewById(R.id.bmiEditText);

        save    = findViewById(R.id.saveDataBtn);
        load    = findViewById(R.id.loadDataBtn);
        clear   = findViewById(R.id.clearDataBtn);
        back    = findViewById(R.id.returnBmi);

        save.setOnClickListener(saveData);
        load.setOnClickListener(loadData);
        clear.setOnClickListener(clearData);
        back.setOnClickListener(backBmi);
        showResult();
    }

    private void showResult() {
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();

        name = bundle.getString("Name");
        height = bundle.getString("Height");
        weight = bundle.getString("Weight");
        bmi = bundle.getString("BMI");


        nameEdit.setText(name);
        heightEdit.setText(height);
        weightEdit.setText(weight);
        bmiEdit.setText(bmi);
    }

    private View.OnClickListener saveData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("BMI_RESULT", 0);

            sp.edit()
                    .putString("Name", name)
                    .putString("Height", height)
                    .putString("Weight", weight)
                    .putString("BMI", bmi)
                    .commit();

            Toast.makeText(BMIResultActivity.this, "儲存完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener loadData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("BMI_RESULT", 0);

            name = sp.getString("Name", "");
            height = sp.getString("Height","");
            weight = sp.getString("Weight", "");
            bmi = sp.getString("BMI", "");

            nameEdit.setText(name);
            heightEdit.setText(height);
            weightEdit.setText(weight);
            bmiEdit.setText(bmi);

            Toast.makeText(BMIResultActivity.this, "載入完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener clearData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("BMI_RESULT", 0);

            sp.edit().clear().commit();

            nameEdit.setText("0");
            heightEdit.setText("0");
            weightEdit.setText("0");
            bmiEdit.setText("0");

            Toast.makeText(BMIResultActivity.this, "清除完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener backBmi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BMIResultActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}