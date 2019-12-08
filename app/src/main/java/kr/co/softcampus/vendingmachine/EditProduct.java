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
    Button save;
    DBExecute db;
    Product product;
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
        product = intent.getParcelableExtra("product");
        int idx =product==null?0:product.getIdx();
        if(idx ==0){
            actionBar.setTitle("商品情報");
        }else{
            actionBar.setTitle("商品情報(No."+idx+")");
            pName.setText(product.getName());
            pPrice.setText(product.getPrice()+"");
            pCount.setText(product.getCount()+"");
        }
    }//onCreate;

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void BtnClick(View view){
        db= new DBExecute(this);

        int price = Integer.parseInt(pPrice.getText().toString());
        int count = Integer.parseInt(pCount.getText().toString());
        String name = pName.getText().toString();

        if(product==null){
            Product eProduct = new Product(price, count, name);

            db.insertProduct(eProduct);
        }else{
            int idx = product.getIdx();
            Product eProduct = new Product(price, count, name, idx);
             db.editProduct(eProduct);
        }
        finish();
    }

}
