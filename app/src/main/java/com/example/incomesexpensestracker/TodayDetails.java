package com.example.incomesexpensestracker;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodayDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    MyDatabase myDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.today_details);
        recyclerView = findViewById(R.id.recyclerView);
        myDatabase = new MyDatabase(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        cursor = myDatabase.selectData(MainActivity.getCurrentDate());
        ArrayList<DataModel> data = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            int income = cursor.getInt(2);
            int expense = cursor.getInt(3);

            DataModel dataModel = new DataModel(id, date, income, expense);
            data.add(dataModel);
        }

        layoutManager = new LinearLayoutManager(this);
        recyclerAdapter = new RecyclerViewAdapter(this, data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        if (cursor != null) {
            cursor.close(); // Always close the cursor to prevent memory leaks
        }
    }
}
