package com.helianthus.tomato;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends BasicActivity {

    public static final String TAG = "FirstActivity";

    /**
     * 注册menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * menu菜单的操作
     *
     * @param item
     * @return
     */
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

            //测试singleInstance模式
            case R.id.mode_item:
                Intent modeIntent = new Intent(FirstActivity.this, ThirdActivity.class);
                startActivity(modeIntent);
                break;

            //删除所有的活动
            case R.id.removeAll_item:
                ActivityCollector.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }

        return true;
    }

    /**
     * 执行回滚数据的显示
     *
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
                    Button button2 = (Button) findViewById(R.id.button2);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(FirstActivity.this, returnData, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(FirstActivity.this,"onStart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(FirstActivity.this,"onRestart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(FirstActivity.this,"onDestroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(FirstActivity.this,"onStop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(FirstActivity.this,"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(FirstActivity.this,"onResume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);

        Log.d("FirstActivity",this.toString());
        Log.d("singleInstance", "Task id is "+getTaskId());

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "You clicked Button1",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        final Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        Button startStandardActivity = (Button)findViewById(R.id.start_standard_activity);
        startStandardActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });

        if(savedInstanceState != null){
            String info = savedInstanceState.getString("info_key");
            //Toast.makeText(NormalActivity.this,info,Toast.LENGTH_SHORT).show();
            TextView textView = (TextView)findViewById(R.id.infoDisplay);
            textView.setText(info);
            //textView.append(info);
        }

        Button alertDialog = (Button)findViewById(R.id.alert_dialog);
        alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
                dialog.setTitle("This is a dialog");
                dialog.setMessage("Something important");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        Button progressDialog = (Button) findViewById(R.id.progress_dialog);
        progressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(FirstActivity.this);
                dialog.setTitle("This is a progressDialog");
                dialog.setCancelable(false);
                dialog.setMessage("Loading....");

                dialog.show();
            }
        });

        Button forthActivity = (Button)findViewById(R.id.four_activity);
        forthActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,PersonalActivity.class);
                startActivity(intent);
            }
        });
        Button listviewActivity = (Button) findViewById(R.id.listview_activity);
        listviewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ListviewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String info = "something is temp";
        outState.putString("info_key",info);
    }
}
