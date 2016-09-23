package com.bsj4444.listviewtest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> list;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listview);
        list=new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add(""+i);
        }
        listViewAdapter=new ListViewAdapter(this,list);
        listView.setAdapter(listViewAdapter);
    }
}
