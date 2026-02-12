package com.example.incomesexpensestracker;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateWiseReport extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    MyDatabase myDatabase;
    Cursor cursor;
    EditText edtDate1, edtDate2;
    TextView txtEmpty;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.datewise_report);
        recyclerView = findViewById(R.id.recyclerView);
        myDatabase = new MyDatabase(this);
        edtDate1 = findViewById(R.id.edtDate1);
        edtDate2 = findViewById(R.id.edtDate2);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtEmpty = findViewById(R.id.txtEmpty);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    public void loadData() {
        String date1 = edtDate1.getText().toString();
        String date2 = edtDate2.getText().toString();
        TextView txtEmpty = findViewById(R.id.txtEmpty);

        if (date1.equals("") || date2.equals("")) {
            Toast.makeText(DateWiseReport.this, "Please Enter Date!", Toast.LENGTH_LONG).show();
        } else {
            cursor = myDatabase.selectData(date1, date2);
            ArrayList<DataModel> data = new ArrayList<>();

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String date = cursor.getString(1);
                int income = cursor.getInt(2);
                int expense = cursor.getInt(3);
                DataModel dataModel = new DataModel(id, date, income, expense);
                data.add(dataModel);
            }

            if (data.isEmpty()) {
                txtEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                txtEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                layoutManager = new LinearLayoutManager(this);
                recyclerAdapter = new RecyclerViewAdapter(this, data);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }
        }
    }
}