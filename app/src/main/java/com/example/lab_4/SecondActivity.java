package com.example.lab_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        Button btnOk = findViewById(R.id.btnOk);

        String name = getIntent().getStringExtra("USER_NAME");
        if (name != null && !name.isEmpty()) {
            tvWelcome.setText("Привет, " + name + "! Выберите дату и время:");
        } else {
            tvWelcome.setText("Выберите дату и время:");
        }

        btnOk.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, hour, minute, 0);
            long milliseconds = calendar.getTimeInMillis();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("SELECTED_DATE", milliseconds);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}