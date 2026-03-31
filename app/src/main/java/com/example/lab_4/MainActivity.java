package com.example.lab_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultDate;
    private ActivityResultLauncher<Intent> secondActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editName = findViewById(R.id.editName);
        Button btnSend = findViewById(R.id.btnSend);
        tvResultDate = findViewById(R.id.tvResultDate);

        secondActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            long milliseconds = result.getData().getLongExtra("SELECTED_DATE", -1);
                            if (milliseconds != -1) {
                                Date date = new Date(milliseconds);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                                String formattedDate = sdf.format(date);
                                tvResultDate.setText("Выбранная дата: " + formattedDate);
                                Toast.makeText(MainActivity.this, "Дата получена: " + formattedDate, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );

        btnSend.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("USER_NAME", name);
            secondActivityLauncher.launch(intent);
        });
    }
}