package com.helianthus.tomato;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                //在当前活动的基础上，打开SecondActivity这个活动
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                Intent removeIntent = new Intent("com.helianthus.tomato.ACTION_START");
                removeIntent.addCategory("com.helianthus.tomato.MY_CATEGORY");
                startActivity(removeIntent);
                break;
            case R.id.modify_item:
                Toast.makeText(this, "You clicked Modify", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_item:
                Toast.makeText(this, "You clicked Query", Toast.LENGTH_SHORT).show();
                break;
            case R.id.browser_item:
                Toast.makeText(this, "You clicked Query", Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(browserIntent);
                break;

        }

        return true;
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
