package com.example.registerpage;

import android.annotation.SuppressLint;
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
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    private EditText datePickerEditText;
    private EditText fullname, name, email, datepicker, phone, alamat, password;


    @SuppressLint("WrongViewCast")
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

        fullname = findViewById(R.id.edt_fullname);
        name = findViewById(R.id.edt_name);
        email = findViewById(R.id.edt_email);
        datepicker = findViewById(R.id.datepicker);
        phone = findViewById(R.id.editTextPhone2);
        alamat = findViewById(R.id.edt_alamat);
        password = findViewById(R.id.edt_password);
        Spinner spinner = findViewById(R.id.spinner_gender);
        MaterialButton btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(v -> {
            String fullName = fullname.getText().toString();
            String Nama = name.getText().toString();
            String TglLahir = datepicker.getText().toString();
            String Phone = phone.getText().toString();
            String Alamat = alamat.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            String selectedGender = spinner.getSelectedItem().toString();

            if (fullName.isEmpty() || Email.isEmpty() || Password.isEmpty()|| Nama.isEmpty() || TglLahir.isEmpty() || selectedGender.isEmpty() || Phone.isEmpty() || Alamat.isEmpty()){
                Toast.makeText(MainActivity.this, "Tolong isikan semua data!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Register Berhasil!", Toast.LENGTH_SHORT).show();
            }
        });

        // Inisialisasi EditText untuk tanggal
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