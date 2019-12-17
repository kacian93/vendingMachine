package kr.co.softcampus.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

public class UserLogin extends AppCompatActivity {
    final int REQUEST_NEWUSER=110;
    TextView userId,password;
    @BindView(R.id.findid)
        TextView findId;
    @BindView(R.id.findpassword)
        TextView findPw;
    @BindView(R.id.newUser)
        TextView newUser;
    Button login;
    DBExecute db;
    final static int USER_RESULT_OK=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_user_login);


        userId = findViewById(R.id.user_id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    public void makeUser(View view){
        Intent intent = new Intent(this, NewUser.class);
        startActivityForResult(intent,REQUEST_NEWUSER);
    }

    public void loginBtn(View view){
        String userIdStr = userId.getText().toString();
        String passwordStr = password.getText().toString();


        if(userIdStr!=null && passwordStr!=null){
            User user = db.login(userIdStr, passwordStr);

            if(user!=null) {
                Intent intent = new Intent();
                intent.putExtra("user", user);
                setResult(USER_RESULT_OK, intent);
                finish();
            }//if2
        }//if1
    }//loginBtn
}
