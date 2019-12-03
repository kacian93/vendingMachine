package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBExecute  {
    Context context;
    ArrayList<Product> list=null;

    DBExecute(Context context){
        this.context = context;
    }
    public SQLiteDatabase openDB(){
        final DBHelper dbHelper = new DBHelper(context);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("test","db open");
        return db;
    }

    public void insertProduct(Product product){
        final SQLiteDatabase db= openDB();
        final String sql = "insert into product(" +
                "name, price, count)" +
                "values(?,?,?)";
        Object [] data = {product.getName(),product.getPrice(), product.getCount()};

        db.execSQL(sql,data);
        db.close();
    }

    public void editProduct(Product product) {
    }
    public ArrayList<Product> showAllList(){
        Log.d("test","showAllList");
        final SQLiteDatabase db = openDB();
        final String sql = "select * from product";

        Cursor c= db.rawQuery(sql,null);
        list = new ArrayList<>();
        while(c.moveToNext()){
            Product p = null;

            int cname = c.getColumnIndex("name");
            int cidx = c.getColumnIndex("idx");
            int ccount = c.getColumnIndex("count");
            int cPrice = c.getColumnIndex("price");

            int idx = c.getInt(cidx);
            int price = c.getInt(cPrice);
            int count = c.getInt(ccount);
            final String name = c.getString(cname);

            p= new Product(price,count,name,idx);
            Log.d("test",p.getIdx()+" ");

            list.add(p);

        }
        return list;

    }
}
