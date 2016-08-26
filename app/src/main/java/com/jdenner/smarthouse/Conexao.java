package com.jdenner.smarthouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juliano on 26/08/2016.
 */
public class Conexao extends SQLiteOpenHelper {

    private static final String DB_NAME = "smarthouse.db";
    private static final int DB_VERSION = 1;

    public Conexao(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("" +
                "CREATE TABLE TbLink (" +
                "   codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "   nome VARCHAR(50) NOT NULL," +
                "   urlOn VARCHAR(200) NOT NULL, " +
                "   urlOff VARCHAR(200) NOT NULL " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
