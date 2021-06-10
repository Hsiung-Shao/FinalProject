package com.app.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StellationResultActivity extends AppCompatActivity {

    EditText nameEdit, birthdayEdit, stellationEdit;
    Button save, load, clear, back;

    private String name, birthday, stellation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stellation_result);

        nameEdit = findViewById(R.id.namesStellationEditText);
        birthdayEdit = findViewById(R.id.birthdayEditText);
        stellationEdit = findViewById(R.id.stellationEditText);

        save    = findViewById(R.id.saveStellationDataBtn);
        load    = findViewById(R.id.loadStellationDataBtn);
        clear   = findViewById(R.id.clearStellationDataBtn);
        back    = findViewById(R.id.returnStellation);

        save.setOnClickListener(saveData);
        load.setOnClickListener(loadData);
        clear.setOnClickListener(clearData);
        back.setOnClickListener(backBmi);
        showResult();
    }

    private void showResult() {
        // 從 Bundle 物件中取出資料
        Bundle bundle = getIntent().getExtras();

        name = bundle.getString("NameStellation");
        birthday = bundle.getString("Birthday");
        stellation = bundle.getString("Stellation");

        nameEdit.setText(name);
        birthdayEdit.setText(birthday);
        stellationEdit.setText(stellation);
    }

    private View.OnClickListener saveData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("Stellation_RESULT", 0);

            sp.edit()
                    .putString("NameStellation", name)
                    .putString("Birthday", birthday)
                    .putString("Stellation", stellation)
                    .commit();

            Toast.makeText(StellationResultActivity.this, "儲存完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener loadData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("Stellation_RESULT", 0);

            name = sp.getString("NameStellation", "");
            birthday = sp.getString("Birthday","");
            stellation = sp.getString("Stellation", "");

            nameEdit.setText(name);
            birthdayEdit.setText(birthday);
            stellationEdit.setText(stellation);

            Toast.makeText(StellationResultActivity.this, "載入完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener clearData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sp =
                    getSharedPreferences("Stellation_RESULT", 0);

            sp.edit().clear().commit();

            nameEdit.setText("0");
            birthdayEdit.setText("0");
            stellationEdit.setText("0");

            Toast.makeText(StellationResultActivity.this, "清除完成", Toast.LENGTH_LONG)
                    .show();
        }
    };

    private View.OnClickListener backBmi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(StellationResultActivity.this, MainActivity.class);
            startActivity(intent);
        }
    };
}