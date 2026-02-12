package com.example.incomesexpensestracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModifyDetails extends AppCompatActivity {
    EditText edtDate, edtAmount;
    Spinner spTitle;
    Button btnDelete, btnUpdate;
    MyDatabase myDatabase;
    int id = 0, amount = 0;
    String date = "";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.modify_details);
        edtDate = findViewById(R.id.edtDate);
        edtAmount = findViewById(R.id.edtAmount);
        spTitle = findViewById(R.id.spTitle);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        myDatabase = new MyDatabase(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        date = intent.getStringExtra("date");
        amount = intent.getIntExtra("amount", 0);
        edtDate.setText(date);
        edtAmount.setText(amount + "");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.deleteData(String.valueOf(id));
                Toast.makeText(ModifyDetails.this, "Data Deleted Successfully !", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting data
                String date = edtDate.getText().toString();
                String title = spTitle.getSelectedItem().toString();
                String amount = edtAmount.getText().toString();

                //validating data
                if (date.equals("")) {
                    Toast.makeText(ModifyDetails.this, "Please Enter Date !", Toast.LENGTH_LONG).show();
                } else if (amount.equals("")) {
                    Toast.makeText(ModifyDetails.this, "Please Enter Amount !", Toast.LENGTH_LONG).show();
                } else if (title.equals("Select Title")) {
                    Toast.makeText(ModifyDetails.this, "Please Select Title !", Toast.LENGTH_LONG).show();
                } else {
                    //Checking income or expenses
                    if (title.equals("Income")) {
                        myDatabase.updateData(String.valueOf(id), date, amount, "0");
                    } else {
                        myDatabase.updateData(String.valueOf(id), date, "0", amount);
                    }
                    Toast.makeText(ModifyDetails.this, "Data Updated Successfully !", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}