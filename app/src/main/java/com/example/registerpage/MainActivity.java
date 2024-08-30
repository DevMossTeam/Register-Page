package com.example.registerpage;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    private EditText datePickerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.spinner_gender);

        datePickerEditText = findViewById(R.id.datepicker);
        // Set listener untuk EditText
        datePickerEditText.setOnClickListener(v -> showDatePickerDialog());
        // Mengambil array string dari resources
        String[] genderOptions = getResources().getStringArray(R.array.gender_array);
        // Membuat adapter untuk Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        // Menetapkan layout dropdown untuk adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Menetapkan adapter ke Spinner
        spinner.setAdapter(adapter);

    }

        private void showDatePickerDialog() {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDay);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        datePickerEditText.setText(dateFormat.format(selectedDate.getTime()));
                    },
                    year,month,
                    day
            );

            datePickerDialog.show();
    }
}