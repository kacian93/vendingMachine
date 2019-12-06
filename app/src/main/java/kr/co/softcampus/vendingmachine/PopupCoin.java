package kr.co.softcampus.vendingmachine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class PopupCoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //titlebarを削除
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_popup_coin);

        //dataをもらう
        Intent intent = getIntent();
        int cash = intent.getIntExtra("cash",0);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}
