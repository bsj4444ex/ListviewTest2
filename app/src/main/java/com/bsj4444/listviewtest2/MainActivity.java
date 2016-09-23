package com.bsj4444.listviewtest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView2 listView;
    private List<String> list;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView2)findViewById(R.id.listview);
        list=new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add(""+i);
        }
        listViewAdapter=new ListViewAdapter(this,list);
        listView.setAdapter(listViewAdapter);
        //listView.setSelection(position);显示第几项,瞬间跳转
//        listView.smoothScrollBy(distance,duration);
//        listView.smoothScrollByOffset(offset);
//        listView.smoothScrollToPosition(index);  实现平滑移动到指定的项
        //listViewAdapter.notifyDataSetChanged(); 刷新动态修改后的listVIew中的内容
        //View view=listView.getChildAt(position); 获取第position个itemView
        //listView.setEmptyView(R.id.xxx);设置空数据时显示的内容
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                    break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        break;
                }
                return false;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE://滑动停止时
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL://正在滚动时
                        break;
                    case SCROLL_STATE_FLING://手指用力滑动时
                        break;              //在离开后listView由于惯性继续滑动
                }
            }

            @Override
            public void onScroll(AbsListView view,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
                //滚动时一直调用
            }
        });
    }
}
