package com.miflappybird.personalizable.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.miflappybird.personalizable.android.ScoreManager;


import java.util.ArrayList;

public class ScoreManager {

    private final DBHelper dbHelper;

    public ScoreManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addScore(int score) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Insertar nuevo score
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_SCORE, score);
        db.insert(DBHelper.TABLE_SCORES, null, values);

        // Eliminar scores por debajo del top 7
        Cursor cursor = db.query(DBHelper.TABLE_SCORES,
            null, null, null, null, null,
            DBHelper.COLUMN_SCORE + " DESC");

        if (cursor.moveToPosition(7)) {
            int idToDelete = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            db.delete(DBHelper.TABLE_SCORES,
                DBHelper.COLUMN_SCORE + " <= (SELECT MIN(" + DBHelper.COLUMN_SCORE + ") FROM " + DBHelper.TABLE_SCORES + " WHERE " + DBHelper.COLUMN_ID + " >= " + idToDelete + ")",
                null);
        }

        cursor.close();
        db.close();
    }

    public ArrayList<Integer> getTopScores() {
        ArrayList<Integer> scores = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_SCORES,
            new String[]{DBHelper.COLUMN_SCORE},
            null, null, null, null,
            DBHelper.COLUMN_SCORE + " DESC", "7");

        while (cursor.moveToNext()) {
            scores.add(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_SCORE)));
        }

        cursor.close();
        db.close();
        return scores;
    }

}
