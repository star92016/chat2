package cn.starnine.chat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IHttpGetListener{
EditText user;
    EditText pass;
    Button login;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        sp=getSharedPreferences("user", Activity.MODE_PRIVATE);
        user=(EditText)findViewById(R.id.et_user);
        pass=(EditText)findViewById(R.id.et_pass);

        user.setText(sp.getString("user",""));
        pass.setText(sp.getString("pass",""));
        String password;
        username=user.getText().toString().trim();
        password=pass.getText().toString().trim();
        if(username.equals("")||password.equals(""))
            ;
        else
           ;
        // new HttpData("http://115.159.90.206:8088/m/login.php?user="+username+"&pass="+password,this).execute();

        login=(Button)findViewById(R.id.btn_login);
        login.setOnClickListener(this);

        findViewById(R.id.btn_regist).setOnClickListener(this);
    }

    Toast toast;

    public void toast(String s) {
        if (toast == null)
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        else
            toast.setText(s);
        toast.show();
    }
    static  String username;
    @Override
    public void onClick(View v) {
        String password;
        SharedPreferences.Editor editor=sp.edit();
switch (v.getId()){
    case R.id.btn_login:
        username=user.getText().toString().trim();
        password=pass.getText().toString().trim();
editor.putString("user",username);
        editor.putString("pass",password);
        editor.apply();
        new HttpData("http://115.159.90.206:8088/m/login.php?user="+username+"&pass="+password,this).execute();
        break;
    case R.id.btn_regist:
        startActivity(new Intent(this,RegistActivity.class));
        break;
}
    }

    @Override
    public void getDataUrl(String data) {
        try {
            JSONObject jb = new JSONObject(data);
            String state=jb.getString("state");
            if(state.equals("ok")){
                toast("登录成功");
                this.finish();
                startActivity(new Intent(this,MainActivity.class));
            }else{
                toast("登录失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            toast("登录失败");
        }

    }
}
