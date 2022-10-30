package com.expensemanagementapp.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.time.LocalDate;
import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {
    private String[] riskArray = {
            "Yes",
            "No",
    };

    EditText name_input,destination_input,description_input, departure_input, fullName_input;
    TextView date_input;
    private Spinner spRiskEdit;
    Button edit_button;
    String tripId, name, departure, destination, date, risksAssessment, customerName, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        spRiskEdit = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, riskArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        name_input = findViewById(R.id.name_edit);
        departure_input = findViewById(R.id.departure_edit);
        destination_input = findViewById(R.id.destination_edit);
        date_input = findViewById(R.id.date_edit);
        fullName_input = findViewById(R.id.fullName_edit);
        description_input = findViewById(R.id.description_edit);
        edit_button = findViewById(R.id.edit_button);

        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("departure") && getIntent().hasExtra("destination") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("risksAssessment") &&
                getIntent().hasExtra("customerName")){
            //Getting data from Intent
            tripId = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            departure = getIntent().getStringExtra("departure");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            risksAssessment = getIntent().getStringExtra("risksAssessment");
            customerName = getIntent().getStringExtra("customerName");
            description = getIntent().getStringExtra("description");

            //Setting Intent Data
            name_input.setText(name);
            departure_input.setText(departure);
            destination_input.setText(destination);
            date_input.setText(date);
            if(Objects.equals(risksAssessment, "Yes")){
                spRiskEdit.setSelection(0,false);
                Log.i("yes window", risksAssessment);
            }
            else {
                Log.i("no window", risksAssessment);
                spRiskEdit.setSelection(1,false);
            }
            fullName_input.setText(customerName);
            description_input.setText(description);
        }
        else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                departure = departure_input.getText().toString().trim();
                destination = destination_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                customerName = fullName_input.getText().toString().trim();
                risksAssessment = spRiskEdit.getSelectedItem().toString();
                description = description_input.getText().toString();
                myDB.updateData(tripId,name, departure,destination,date,customerName,risksAssessment,description);
            }
        });

    }

//    void getAndSetIntentData(){
//
//    }
    public void showEditDatePickerDialog(View v){
        DialogFragment newFragment = new EditDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void editDateDOT(LocalDate dot){
        TextView dotText = (TextView) findViewById(R.id.date_edit);
        dotText.setText(dot.toString());
    }

}