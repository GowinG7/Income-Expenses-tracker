package com.example.incomesexpensestracker;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Activity context;
    ArrayList<DataModel> data;

    public RecyclerViewAdapter(Activity context, ArrayList<DataModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem = layoutInflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataModel model = data.get(position);

        holder.txtDate.setText("Date: " + model.getDate());
        if (model.getExpenses() == 0) {
            holder.txtExpenses.setVisibility(View.GONE);
            holder.txtIncome.setVisibility(View.VISIBLE);
            holder.txtIncome.setText("Income: " + model.getIncome());
        } else {
            holder.txtExpenses.setVisibility(View.VISIBLE);
            holder.txtIncome.setVisibility(View.GONE);
            holder.txtExpenses.setText("Expenses: " + model.getExpenses());
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModifyDetails.class);
                intent.putExtra("id", model.getId());
                intent.putExtra("date", model.getDate());
                if (model.getExpenses() == 0) {
                    intent.putExtra("amount", model.getIncome());
                } else {
                    intent.putExtra("amount", model.getExpenses());
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtIncome, txtExpenses;
        LinearLayout relativeLayout; // change RelativeLayout → LinearLayout

        public ViewHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtIncome = itemView.findViewById(R.id.txtIncome);
            txtExpenses = itemView.findViewById(R.id.txtExpenses);
            relativeLayout = itemView.findViewById(R.id.relat); // now LinearLayout
        }
    }

}
