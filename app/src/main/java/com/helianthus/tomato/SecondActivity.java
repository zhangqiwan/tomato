package com.helianthus.tomato;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BasicActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    public static void actionStart(Context context,String data1, String data2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("SecondActivity", this.toString());
        Log.d("singleInstance", "Task id is "+getTaskId());
        Intent intent = getIntent();
        final String data = intent.getStringExtra("extra_info");
        Button button21 = (Button)findViewById(R.id.button21);
        Button button22 = (Button)findViewById(R.id.button22);
        Button startSingletopMode = (Button) findViewById(R.id.start_singletop_mode);
        Button startSingletopMode1 = (Button) findViewById(R.id.start_singletop_mode1);

        startSingletopMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        startSingletopMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("return_info","Hello FirstActivity...");
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}
