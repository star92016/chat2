package cn.starnine.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener,IHttpGetListener{
    EditText user;
    EditText pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("注册");
        user=(EditText)findViewById(R.id.et_user);
        pass=(EditText)findViewById(R.id.et_pass);
        findViewById(R.id.btn_login).setVisibility(View.GONE);
        login=(Button)findViewById(R.id.btn_regist);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username=null,password=null;
        username=user.getText().toString().trim();
        password=pass.getText().toString().trim();
        new HttpData("http://115.159.90.206:8088/login.php?user="+username+"&pass="+password,this).execute();

    }

    @Override
    public void getDataUrl(String data) {

    }
}
