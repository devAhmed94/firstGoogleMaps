package com.example.simplemarkerapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplemarkerapp.R;
import com.example.simplemarkerapp.db.DatabaseHelper;
import com.example.simplemarkerapp.db.Model;

public class AddActivity extends AppCompatActivity {

    TextView tv_lat, tv_long;
    EditText title;
    Button add;
    double latitude;
    double longitude;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        tv_lat = findViewById(R.id.add_lat_tv);
        tv_long = findViewById(R.id.add_long_tv);
        title = findViewById(R.id.add_et_title);
        add = findViewById(R.id.add_btn_add);

        latitude = getIntent().getExtras().getDouble("latitude");
        longitude = getIntent().getExtras().getDouble("longitude");
        float id = getIntent().getExtras().getFloat("id");
        int num_id = Math.round(id);
        tv_long.setText(String.valueOf(longitude));
        tv_lat.setText(String.valueOf(latitude));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitle = title.getText().toString();
                if (!(sTitle.trim().isEmpty())) {
                            boolean insert = databaseHelper.insert(new Model(num_id,sTitle, String.valueOf(latitude), String.valueOf(longitude)));
                            if (insert) {
                                Toast.makeText(AddActivity.this, " Done insert", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddActivity.this, "error .. ", Toast.LENGTH_SHORT).show();
                            }
                            startActivity(new Intent(AddActivity.this, MapsActivity.class));
                            finish();

                } else {
                    Toast.makeText(AddActivity.this, " plz add title ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}