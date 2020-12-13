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

public class ShowActivity extends AppCompatActivity {

    TextView tv_lat, tv_long;
    EditText et_title;
    Button delete,edit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        tv_lat = findViewById(R.id.show_lat_tv);
        tv_long = findViewById(R.id.show_long_tv);
        et_title = findViewById(R.id.show_et_title);
        delete = findViewById(R.id.show_btn_delete);
        edit = findViewById(R.id.show_btn_edit);
        String sTitle = getIntent().getExtras().getString("title");
        final double latitude = getIntent().getExtras().getDouble("latitude");
        final double longitude = getIntent().getExtras().getDouble("longitude");

        float num_id = Float.parseFloat(getIntent().getExtras().getString("id"));
        int int_id = Math.round(num_id);
        Toast.makeText(this, int_id+"", Toast.LENGTH_SHORT).show();

        tv_lat.setText(String.valueOf(latitude));
        tv_long.setText(String.valueOf(longitude));
        et_title.setText(sTitle);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean delete = databaseHelper.delete(new Model(int_id,sTitle, String.valueOf(latitude), String.valueOf(longitude)));
                if (delete){
                    Toast.makeText(ShowActivity.this, "Done deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ShowActivity.this, MapsActivity.class));
                    finish();
                }else {
                    Toast.makeText(ShowActivity.this, "error...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = databaseHelper.update(new Model(int_id, et_title.getText().toString(), String.valueOf(latitude), String.valueOf(longitude)));
                if (update){
                    Toast.makeText(ShowActivity.this, " Done update ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ShowActivity.this,MapsActivity.class));
                    finish();
                }else {
                    Toast.makeText(ShowActivity.this, "error...", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}