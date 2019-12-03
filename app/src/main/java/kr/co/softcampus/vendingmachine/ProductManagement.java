package kr.co.softcampus.vendingmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ProductManagement extends AppCompatActivity {
    DBExecute dbExecute;
    ArrayList<Product> list = new ArrayList<>();
    Button newProduct;
    RecyclerAdapter recyclerAdapter= null;
    RecyclerView recyclerView;
    final int NEW_PRODUCT=11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("商品管理");


        newProduct = findViewById(R.id.addProduct);

        showList();
    }

    @Override
    protected void onRestart() {
        showList();
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showList(){
        dbExecute = new DBExecute(this);
        list = dbExecute.showAllList();
        Log.d("test","list : "+list.get(0).getName());

        recyclerAdapter = new RecyclerAdapter(list);
        recyclerAdapter.notifyDataSetChanged();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);


    }
    public void addProduct(View view){
        Intent intent = new Intent(this,EditProduct.class);
        startActivityForResult(intent, NEW_PRODUCT);
    }
}
