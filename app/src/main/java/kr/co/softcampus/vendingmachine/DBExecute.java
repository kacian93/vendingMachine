package kr.co.softcampus.vendingmachine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        final SQLiteDatabase db= openDB();

        final String sql ="UPDATE product SET count = ?, price=? , name=?" +
                "WHERE idx = ?";

        Object [] data = {product.getCount(), product.getPrice(),product.getName(),product.getIdx()};
        db.execSQL(sql,data);

        db.close();
    }
    public ArrayList<Product> showAllList(){
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

            list.add(p);

        }
        c.close();
        db.close();

        return list;

    }

    public void sellProduct(Product product){
        final SQLiteDatabase db = openDB();
        final String sql = "UPDATE product SET count = ? where idx = ? ";
        Object [] data = {product.getCount(),product.getIdx()};

        db.execSQL(sql, data);
        db.close();
    }

    public void inputCash(int cash) {
        final SQLiteDatabase db= openDB();
        final String sql = "UPDATE user SET cash = ? where idx=?";

        db.close();
    }


    public boolean checkId(String user_id){
        boolean isvaild=false;

        final SQLiteDatabase db = openDB();
        final String sql ="SELECT user_id FROM user where user_id ="+user_id;
        final Cursor c = db.rawQuery(sql,null);

        c.moveToNext();

        int user_idPos= c.getColumnIndex("user_id");
        String user_idStr = c.getString(user_idPos);

        if(user_idStr==null || user_idStr.equals("") || user_idStr.length()<1){
            isvaild=true;
        }

        c.close();
        db.close();
        return  isvaild;
    }
    public void makeUser(String user_Id, String password){
        final SQLiteDatabase db= openDB();
        final String sql = "INSERT INTO user(user_id, password) values (?,?)";
        Object [] data= {user_Id,password};

        db.execSQL(sql, data);

    }
    public User login(String userIdStr, String passwordStr) {
        final SQLiteDatabase db= openDB();
        final String sql = "SELECT * FROM user WHERE user_id = "+userIdStr+" and password = "+passwordStr;
        User user = null;

        final  Cursor c= db.rawQuery(sql, null);
        c.moveToNext();

        int uidxPos = c.getColumnIndex("idx");
        int uUser_idPos = c.getColumnIndex("user_id");
        int uPasswordPos = c.getColumnIndex("password");
        int uPermissionPos = c.getColumnIndex("permission");
        int uCashPos = c.getColumnIndex("cash");

        int uidx = c.getInt(uidxPos);
        String user_id = c.getString(uUser_idPos);
        String password=  c.getString(uPasswordPos);
        String permission = c.getString(uPermissionPos);
        int cash = c.getInt(uCashPos);

        user = new User(uidx, user_id, password, permission,cash);


        c.close();
        db.close();
        return user;
    }

    public void makeUser(User user) {
        SQLiteDatabase db = openDB();

        final String sql = "INSERT INTO user(" +
                "user_id, password, phone, email) " +
                "VALUES (?,?,?,?)";
        final Object [] data = {user.getUserId() ,user.getPassword(),user.getPhone(),user.getEmail()};

        db.execSQL(sql,data);

        db.close();
    }
}
