package com.example.eshop.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eshop.helper.UserDbHelper;

public class UserDao {
    private SQLiteDatabase database;

    public UserDao(Context context) {
        UserDbHelper dbHelper = new UserDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long addUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(UserDbHelper.COLUMN_USERNAME, username);
        values.put(UserDbHelper.COLUMN_PASSWORD, password);
        return database.insert(UserDbHelper.TABLE_NAME, null, values);
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {
                UserDbHelper.COLUMN_ID
        };
        String selection = UserDbHelper.COLUMN_USERNAME + " = ? AND " + UserDbHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(UserDbHelper.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkUserExists(String username) {
        String[] columns = {
                UserDbHelper.COLUMN_ID
        };
        String selection = UserDbHelper.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = database.query(UserDbHelper.TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}