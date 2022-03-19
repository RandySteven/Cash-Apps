package com.example.igclone3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.igclone3.dao.TransactionDAO;
import com.example.igclone3.dao.UserDAO;
import com.example.igclone3.model.Transaction;
import com.example.igclone3.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper implements UserDAO, TransactionDAO {
    private static final String DATABASE_NAME="kasq.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER="users";
    private static final String TABLE_TRANSACTION="transactions";

    private static final String CREATE_TABLE_USER="CREATE TABLE " + TABLE_USER + "(" +
            "userId CHAR(5) PRIMARY KEY," +
            "userName VARCHAR(20) NOT NULL," +
            "userEmail VARCHAR(30) NOT NULL," +
            "userPassword VARCHAR(30) NOT NULL" +
            ")";

    private static final String CREATE_TABLE_TRANSACTION="CREATE TABLE " + TABLE_TRANSACTION + "(" +
            "transactionId CHAR(5) PRIMARY KEY," +
            "transactionDate DATE NOT NULL," +
            "transactionCategory VARCHAR(20) NOT NULL," +
            "transactionAmount INT NOT NULL," +
            "transactionType VARCHAR(20) NOT NULL,"+
            "transactionDescription TEXT NOT NULL," +
            "FOREIGN KEY(userId) REFERENCES " + TABLE_USER + " (userId) " +
            ")";

    @Override
    public String registerUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        String createUser = "";
        try{
            ContentValues values = new ContentValues();
            values.put("userId", user.getId());
            values.put("userName", user.getUserName());
            values.put("userEmail", user.getUserEmail());
            values.put("userPassword", user.getUserPassword());

            db.insertOrThrow(TABLE_USER, null, values);
            db.setTransactionSuccessful();
            createUser = "Create user success";
        }catch (Exception e){
            e.printStackTrace();
            createUser = "Server error";
        }finally {
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
        return createUser;
    }

    @Override
    public User loginUser(String email, String password) {
        User user = null;
        String query = "SELECT * FROM " + TABLE_USER + " WHERE userEmail = '" + email + "' AND userPassword = '" + password + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                String userId, userName, userEmail, userPassword;
                userId = cursor.getString(cursor.getColumnIndexOrThrow("userId"));
                userName = cursor.getString(cursor.getColumnIndexOrThrow("userName"));
                userEmail = cursor.getString(cursor.getColumnIndexOrThrow("userEmail"));
                userPassword = cursor.getString(cursor.getColumnIndexOrThrow("userPassword"));
                user = new User(userId, userName, userEmail, userPassword);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.isClosed();
            }
        }
        return user;
    }
    //===============================================================================================
    private Context context;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER + "");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION + "");
    }
    //===============================================================================================
    @Override
    public void createTransaction(Transaction transaction) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("transactionId", transaction.getId());
            values.put("transacrtionDate", transaction.getTransactionDate());
            values.put("transactionCategory", transaction.getTransactionCategory());
            values.put("transactionAmount", transaction.getTransactionAmount());
            values.put("transactionType", transaction.getTransactionType());
            values.put("transactionDescription", transaction.getTransactionDescription());
            db.insertOrThrow(TABLE_TRANSACTION, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null && db.inTransaction()){
                db.endTransaction();
            }
        }
    }

    @Override
    public List<Transaction> getAllTransactions(String userId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE userId = '" + userId + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try{
            if(cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("transactionId"));
                    String transactionDate = cursor.getString(cursor.getColumnIndexOrThrow("transactionDate")) ;
                    String transactionType = cursor.getString(cursor.getColumnIndexOrThrow("transactionType"));
                    String transactionCategory = cursor.getString(cursor.getColumnIndexOrThrow("transactionCategory"));
                    int transactionAmount = cursor.getInt(cursor.getColumnIndexOrThrow("transactionAmount"));
                    String transactionDescription = cursor.getString(cursor.getColumnIndexOrThrow("transactionDescription"));

                    Transaction transaction = new Transaction(id, transactionDate, transactionType, transactionCategory, transactionAmount, transactionDescription);
                    transactions.add(transaction);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.isClosed();
            }
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionDetail(String transactionId) {
        String query = "SELECT * FROM " + TABLE_TRANSACTION + " WHERE transactionId = '" + transactionId + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Transaction transaction = null;
        try{
            if(cursor.moveToFirst()){
                String id = cursor.getString(cursor.getColumnIndexOrThrow("transactionId"));
                String transactionDate = cursor.getString(cursor.getColumnIndexOrThrow("transactionDate")) ;
                String transactionType = cursor.getString(cursor.getColumnIndexOrThrow("transactionType"));
                String transactionCategory = cursor.getString(cursor.getColumnIndexOrThrow("transactionCategory"));
                int transactionAmount = cursor.getInt(cursor.getColumnIndexOrThrow("transactionAmount"));
                String transactionDescription = cursor.getString(cursor.getColumnIndexOrThrow("transactionDescription"));

                transaction = new Transaction(id, transactionDate, transactionType, transactionCategory, transactionAmount, transactionDescription);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.isClosed();
            }
        }

        return transaction;
    }

    @Override
    public void updateTransaction(String transactionId, Transaction transaction) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("transacrtionDate", transaction.getTransactionDate());
        values.put("transactionCategory", transaction.getTransactionCategory());
        values.put("transactionAmount", transaction.getTransactionAmount());
        values.put("transactionType", transaction.getTransactionType());
        values.put("transactionDescription", transaction.getTransactionDescription());
        db.update(TABLE_TRANSACTION, values, "transactionId=?", new String[]{transactionId});
        db.close();
    }

    @Override
    public void deleteTransaction(String transactionId) {
        String query = "DELETE FROM " + TABLE_TRANSACTION + " WHERE transactionId = '" + transactionId + "'";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
}
