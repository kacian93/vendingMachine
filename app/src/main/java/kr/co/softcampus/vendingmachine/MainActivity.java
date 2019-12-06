package kr.co.softcampus.vendingmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_MANAGE =10;
    final int REQUEST_COIN=12;
    ArrayList<Product> list= null;
    Adapter adapter = null;
    DBExecute db;

    Button coin, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coin = findViewById(R.id.coin);
        cancel = findViewById(R.id.cancel);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("自動機シミュレーター");
        showList();
    }
    public void clickBtn(View view){
        switch (view.getId()){
            case R.id.coin:
                Intent intent = new Intent(this, PopupCoin.class);
                intent.putExtra("cash",0);
                startActivityForResult(intent,REQUEST_COIN);
                break;
        }
    }
    @Override
    protected void onResume() {
        showList();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.manage){
            Intent intent = new Intent(getApplicationContext(),ProductManagement.class);
            startActivityForResult(intent,REQUEST_MANAGE);
        }

        return super.onOptionsItemSelected(item);
    }
    public void showList(){
        db = new DBExecute(this);
        list = db.showAllList();
        adapter = new Adapter(this,list);
        adapter.notifyDataSetChanged();

        GridView listView = findViewById(R.id.listview1);
        listView.setNumColumns(3);
        int height = listView.getHeight();

        listView.setAdapter(adapter);
    }
}
