package com.helianthus.tomato;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //显式intent启动活动
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                //在当前活动的基础上，打开SecondActivity这个活动
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;

            //隐式intent启动活动
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                Intent removeIntent = new Intent("com.helianthus.tomato.ACTION_START");
                removeIntent.addCategory("com.helianthus.tomato.MY_CATEGORY");
                startActivity(removeIntent);
                break;

            //向下传递数据
            case R.id.modify_item:
                Toast.makeText(this, "You clicked Modify", Toast.LENGTH_SHORT).show();
                String data = "Hello SecondActivity!";
                Intent modifyIntent = new Intent(this, SecondActivity.class);
                modifyIntent.putExtra("extra_info", data);
                startActivity(modifyIntent);
                break;

            //向上传递数据
            case R.id.query_item:
                Toast.makeText(this, "You clicked Query", Toast.LENGTH_SHORT).show();
                Intent queryIntent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(queryIntent, 1);
                break;

            //不同程序间的活动启动，浏览器
            case R.id.browser_item:
                Toast.makeText(this, "You clicked Query", Toast.LENGTH_SHORT).show();
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                //browserIntent.setData(Uri.parse("http://www.baidu.com"));
                //startActivity(browserIntent);
                //Intent browserIntent = new Intent("com.helianthus.tomato.ACTION_START");
                //browserIntent.addCategory("com.helianthus.tomato.MY_CATEGORY1");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://www.baidu.com"));
                //startActivity(browserIntent);
                startActivity(browserIntent);
                break;

            //不同程序间的活动启动，打电话
            case R.id.dial_item:
                Toast.makeText(this, "You clicked Dial", Toast.LENGTH_SHORT).show();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:12345"));
                startActivity(dialIntent);
                break;
        }

        return true;
    }

    /**
     * 执行回滚数据的显示
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final String returnData = data.getStringExtra("return_info");
                    //Log.d("FirstActivity",returnData);
                    Button button2 = (Button)findViewById(R.id.button2);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(FirstActivity.this,returnData,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "You clicked Button1",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
