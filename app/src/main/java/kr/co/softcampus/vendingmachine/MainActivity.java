package kr.co.softcampus.vendingmachine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_MANAGE =10;
    final int REQUEST_COIN=12;
    ArrayList<Product> list= null;
    Adapter adapter = null;
    DBExecute db;
    GridView listView;
    TextView message_text, cash_text;
    static int cash = 0;
    Button coin, cancel;
    final int REQUEST_LOGIN=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this,UserLogin.class);
//        startActivityForResult(intent,REQUEST_LOGIN);

        coin = findViewById(R.id.coin);
        cancel = findViewById(R.id.cancel);
        cash_text = findViewById(R.id.cash_text);
        message_text = findViewById(R.id.message_text);
        listView= findViewById(R.id.listview1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("自動機シミュレーター");
        showList();
    }
    public void clickBtn(View view){
        switch (view.getId()){
            case R.id.coin:
                Intent intent = new Intent(this, PopupCoin.class);
                intent.putExtra("cash",cash);
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

        final GridView listView = findViewById(R.id.listview1);
        listView.setNumColumns(3);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.notifyDataSetChanged();
            }
        });


        cash_text.setText(cash+"");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_COIN){
            if(resultCode ==RESULT_OK){
                cash = data.getIntExtra("cash",0);
                Log.d("test",cash+"");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
