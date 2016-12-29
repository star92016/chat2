package cn.starnine.chat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
EditText et_url;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        et_url=(EditText)findViewById(R.id.et_url);

        sp=getSharedPreferences("user",MODE_PRIVATE);
        et_url.setText(sp.getString("url","http://115.159.90.206:8088/m/chat.php"));
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
    public void onClick(View v) {
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("url",et_url.getText().toString().trim());
        editor.apply();
        toast("ok");
    }
}
