package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gustavo on 26/11/16.
 */

public class DaoFactory extends SQLiteOpenHelper {





    public DaoFactory(Context context) {
        super(context, "dhl", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user (id INTEGER PRIMARY KEY, name TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS user";
        db.execSQL(sql);
        onCreate(db);
    }
}
