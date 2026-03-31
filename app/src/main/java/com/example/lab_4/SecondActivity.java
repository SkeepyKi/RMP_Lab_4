package com.example.lab_4;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvWelcome = findViewById(R.id.tvWelcome);

        String name = getIntent().getStringExtra("USER_NAME");
        if (name != null && !name.isEmpty()) {
            tvWelcome.setText("Привет, " + name + "!");
        } else {
            tvWelcome.setText("Имя не было передано");
        }
    }
}