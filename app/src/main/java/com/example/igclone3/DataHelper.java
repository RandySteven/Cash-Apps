package com.example.igclone3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.igclone3.dao.UserDAO;
import com.example.igclone3.model.User;

public class DataHelper extends SQLiteOpenHelper implements UserDAO {
    private static final String DATABASE_NAME="kasq.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER="users";

    private static final String CREATE_TABLE_USER="CREATE TABLE " + TABLE_USER + "(" +
            "userId CHAR(5) PRIMARY KEY," +
            "userName VARCHAR(20) NOT NULL," +
            "userEmail VARCHAR(30) NOT NULL," +
            "userPassword VARCHAR(30) NOT NULL" +
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER + "");
    }
    //===============================================================================================
}
