package kr.co.softcampus.vendingmachine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class PopupCoin extends AppCompatActivity {
    static int cash=0;
    DBExecute db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //titlebarを削除
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_popup_coin);


        db= new DBExecute(this);
        //dataをもらう
        Intent intent = getIntent();
        cash = intent.getIntExtra("cash",0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //外のレイアを触ると閉じないように
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("cash",cash);
        setResult(RESULT_OK, intent);
        db.inputCash(cash);

        finish();
        super.onBackPressed();
    }
    public void clickCash(View view){
        int id = view.getId();
        switch (id){
            case R.id.coin10:
                cash+=10;
                Log.d("test",cash+"");
                break;
            case R.id.coin50:
                cash+=50;
                Log.d("test",cash+"");
                break;
            case R.id.coin100:
                cash+=100;
                Log.d("test",cash+"");
                break;
            case R.id.coin500:
                cash+=500;
                Log.d("test",cash+"");
                break;
        }

    }
    public void clickClose(View view){
        Intent intent = new Intent();
        intent.putExtra("cash",cash);
        setResult(RESULT_OK, intent);

        finish();
    }
}
