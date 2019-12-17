package kr.co.softcampus.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public class NewUser extends AppCompatActivity {
    DBExecute db;
    EditText makeId;
    EditText makePw;
    EditText checkPw;
    EditText phone;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_new_user);

        email = findViewById(R.id.email);
        makeId = findViewById(R.id.makeId);
        makePw = findViewById(R.id.makePw);
        checkPw = findViewById(R.id.checkPw);
        phone = findViewById(R.id.phone);

    }
    @OnClick(R.id.make)
    public void signUp(View view){
        db = new DBExecute(this);
        final String makeIdStr=makePw.getText().toString();
        final String checkIdStr =checkPw.getText().toString();
        if(makeIdStr.equals(checkIdStr)) {
            String userId= makeId.getText().toString();
            String userPw = makePw.getText().toString();
            String phoneNum = phone.getText().toString();
            String emailStr = email.getText().toString();


            User user = new User(userId,userPw,phoneNum,emailStr);

            db.makeUser(user);
        }

    }
}
