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

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1 :

                final String userSql= "CREATE TABLE Person(" +
                        " user_idx integer primary key autoincrement " +
                        ", user_id text not null " +
                        ", password text not null " +
                        ", permission text check(name IN ('common' , 'admin')) not null default 'common'" +
                        ", cash integer not null default 0)";
                db.execSQL(userSql);
            case 2:
                final String alterTable1= "ALTER TABLE user ADD COLUMN phone text";
                final String alterTable2= "ALTER TABLE user ADD COLUMN email text";

                db.execSQL(alterTable1);
                db.execSQL(alterTable2);

        }
    }
}
