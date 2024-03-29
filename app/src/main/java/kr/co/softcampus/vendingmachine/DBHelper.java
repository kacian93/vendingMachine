package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "product.db", null, 2);
    }

    public DBHelper(Context context) {
        super(context,"product.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql= "CREATE TABLE Product(" +
                "idx integer primary key autoincrement," +
                "name text not null," +
                "price integer not null default 0," +
                "count integer not null default 0)";

        final String cashSql = "CREATE TABLE cash(cash integer primary key default 0)";
        final  String insertMoney = "INSERT INTO CASH(cash) values (0)";


        db.execSQL(cashSql);
        db.execSQL(insertMoney);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
