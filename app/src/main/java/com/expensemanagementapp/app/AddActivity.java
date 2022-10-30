package com.expensemanagementapp.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.time.LocalDate;

public class AddActivity extends AppCompatActivity {
    private String[] riskArray = {
            "Yes",
            "No",
    };
    EditText name_input,destination_input,description_input, departure_input, fullName_input;
    TextView date_input;
    private Spinner sp;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sp = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, riskArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter((dataAdapter));

        name_input = findViewById(R.id.name_input);
        departure_input = findViewById(R.id.departure_input);
        destination_input = findViewById(R.id.destination_input);
        date_input = findViewById(R.id.date_input);
        fullName_input = findViewById(R.id.fullName_input);
        description_input = findViewById(R.id.description_input);
        add_button = findViewById(R.id.add_button);
        Spinner requiredAssessment = findViewById(R.id.spinner2);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTrip(name_input.getText().toString().trim(),
                        departure_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        fullName_input.getText().toString().trim(),
                        requiredAssessment.getSelectedItem().toString(),
                        description_input.getText().toString().trim());

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOT(LocalDate dot){
        TextView dotText = (TextView) findViewById(R.id.date_input);
        dotText.setText(dot.toString());
    }
}