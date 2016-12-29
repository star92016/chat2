package cn.starnine.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IHttpGetListener {
    ListView lv;
    Button btn_send;
    EditText et_send;
    private List<ListData> lists;
    private TextAdapter adapter;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        btn_send = (Button) findViewById(R.id.btn_send);
        et_send = (EditText) findViewById(R.id.sendText);
        btn_send.setOnClickListener(this);
        lists = new ArrayList<ListData>();
        getData();
        adapter = new TextAdapter(lists, this);
        lv.setAdapter(adapter);
    }

    private void getData() {
        lists.add(new ListData("欢迎回来", ListData.RECEIVER, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE,1,1,"设置");
        menu.add(Menu.NONE,2,2,"清除记录");
        menu.add(Menu.NONE,3,3,"退出登录");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case 1:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case 2:
                lists.clear();
                adapter.notifyDataSetChanged();
                break;
            case 3:
                this.finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
        return  true;
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
        content = et_send.getText().toString().trim().replaceAll("\n", "");
        et_send.setText("");
        if (content.equals("")) {
            toast("不能为空");
            return;
        } else {
            lists.add(new ListData(content, ListData.SEND, ""));
            adapter.notifyDataSetChanged();
            new HttpData(getSharedPreferences("user",MODE_PRIVATE).getString("url","http://115.159.90.206:8088/m/chat.php")+"?user="+LoginActivity.username+
                    "&content="+content,this).execute();
        }

    }

    @Override
    public void getDataUrl(String data) {
        try {
            JSONObject jb = new JSONObject(data);
            String state=jb.getString("state");
            if(state.equals("ok")){
                lists.add(new ListData(jb.getString("content"),ListData.RECEIVER,""));
                adapter.notifyDataSetChanged();
            }else{
                toast("获取失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            toast("获取失败");
        }

    }
}
