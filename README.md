# SpendWise – Income & Expenses Tracker

SpendWise is a lightweight Android app designed to help users track daily income and expenses with ease. It provides a clean, intuitive interface to add, view, update, and report transactions, making personal finance management simple and reliable
- It doesnot store data in remote server, but store data using SQLite

## 📌 Features
- Add daily **Income** and **Expenses**
- View **today’s totals** (Income, Expenses, Balance) on the dashboard
- Browse a **detailed list of transactions** for today or any date range
- Edit or delete individual records
- Generate **date-wise reports** for better insights

## 🛠 Core Components

### 1. SQLite Database (`MyDatabase.java`)
- **Purpose:** Local storage of transaction data  
- **Structure:** Single table `mytable` with columns:
  - `id` (Primary Key)  
  - `date`  
  - `income`  
  - `expenses`  
- **Key Methods:**  
  - `insertData()` – Add a transaction  
  - `selectData(date)` – Fetch records for a specific date  
  - `selectData(date1, date2)` – Fetch records for a date range  
  - `updateData()` – Modify a transaction  
  - `deleteData()` – Remove a transaction  
- **Why SQLite?**  
  - Lightweight and built into Android  
  - Structured storage with query support  
  - Works offline reliably  

### 2. RecyclerView (`list_items.xml` + `RecyclerViewAdapter.java`)
- **Purpose:** Efficiently display transaction records in a scrollable list  
- **Adapter Logic:**  
  - Maps `DataModel` objects from SQLite to UI  
  - Dynamically shows Income or Expense based on data  
  - Handles clicks to open `ModifyDetails` for editing/deleting  
- **Why RecyclerView?**  
  - Handles large datasets efficiently  
  - Recycles views for smooth scrolling  
  - Supports dynamic data updates without UI lag  

---

### 3. App Workflow
- **MainActivity (Dashboard):** Displays today’s totals and navigation buttons  
- **DataEntry Activity:** Add income or expenses  
- **TodayDetails / DateWiseReport:** View transactions via RecyclerView  
- **ModifyDetails Activity:** Edit or delete a transaction  

**Data Flow:**  
`User adds data → Stored in SQLite → Retrieved for display → Shown in RecyclerView → Editable by user
