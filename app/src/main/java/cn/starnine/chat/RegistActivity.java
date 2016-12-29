package cn.starnine.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
        new HttpData("http://115.159.90.206:8088/m/register.php?user="+username+"&pass="+password,this).execute();

    }
    Toast toast;

    public void toast(String s) {
        if (toast == null)
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        else
            toast.setText(s);
        toast.show();
    }
    @Override
    public void getDataUrl(String data) {
        try {
            JSONObject jb = new JSONObject(data);
            String state=jb.getString("state");
            if(state.equals("ok")){
                toast("注册成功");
                this.finish();

            }else{
                toast("注册失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            toast("注册失败");
        }
    }
}
