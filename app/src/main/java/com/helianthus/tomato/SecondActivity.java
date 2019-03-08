package com.helianthus.tomato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        final String data = intent.getStringExtra("extra_info");
        Button button21 = (Button)findViewById(R.id.button21);
        Button button22 = (Button)findViewById(R.id.button22);

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
