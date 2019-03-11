package com.helianthus.tomato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NormalActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
        if(savedInstanceState != null){
            String info = savedInstanceState.getString("info_key");
            //Toast.makeText(NormalActivity.this,info,Toast.LENGTH_SHORT).show();
            TextView textView = (TextView)findViewById(R.id.normalTextView);
            textView.append(info);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
