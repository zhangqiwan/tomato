package com.helianthus.tomato;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListviewActivity extends AppCompatActivity {

    private String[] data = {"apple","orange","banana","pear","cherry","mango","watermelon","orange","banana","pear","cherry","mango","watermelon","orange","banana","pear","cherry","mango","watermelon"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListviewActivity.this,
                android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
