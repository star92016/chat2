package cn.starnine.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IHttpGetListener{
EditText user;
    EditText pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        user=(EditText)findViewById(R.id.et_user);
        pass=(EditText)findViewById(R.id.et_pass);
        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(this);
        findViewById(R.id.btn_regist).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username,password;
switch (v.getId()){
    case R.id.btn_login:
        username=user.getText().toString().trim();
        password=pass.getText().toString().trim();
        new HttpData("http://115.159.90.206:8088/login.php?user="+username+"&pass="+password,this).execute();
        break;
    case R.id.btn_regist:
        startActivity(new Intent(this,RegistActivity.class));
        break;
}
    }

    @Override
    public void getDataUrl(String data) {
        this.finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}
