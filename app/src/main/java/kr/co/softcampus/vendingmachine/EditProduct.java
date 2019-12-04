package kr.co.softcampus.vendingmachine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProduct extends AppCompatActivity {
    EditText pName;
    EditText pPrice;
    EditText pCount;
    int price, count, idx;
    String name;
    Button save;
    DBExecute db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        pName = findViewById(R.id.pName);
        pPrice = findViewById(R.id.pPrice);
        pCount = findViewById(R.id.pCount);
        save = findViewById(R.id.save);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        final Intent intent = getIntent();
        final Product product = intent.getParcelableExtra("product");
        int idx =product==null?0:product.getIdx();
        if(idx ==0){
            actionBar.setTitle("商品情報");
        }else{
            actionBar.setTitle("商品情報(No."+idx+")");
        }
    }//onCreate;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void BtnClick(View view){
        db= new DBExecute(this);
        final Product product = changeType();
        if(idx==0){
            db.insertProduct(product);
        }else{
            db.editProduct(product);
        }
        finish();
    }

    public Product changeType(){
        Product product = null;

        price = Integer.parseInt(pPrice.getText().toString());
        count = Integer.parseInt(pCount.getText().toString());
        name = pName.getText().toString();
        if(idx==0){
           product = new Product(price,count,name);
        }else{
            product = new Product(price, count, name,idx);
        }

        return product;
    }
}
