package com.example.incomesexpensestracker;

public class DataModel {
    private int id;
    private String date;
    private int income;
    private int expenses;

    public DataModel(int id, String date, int income, int expenses){
        this.id=id;
        this.date=date;
        this.income=income;
        this.expenses=expenses;
    }
    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public int getIncome(){
        return income;
    }
    public int getExpenses(){
        return expenses;
    }
}
