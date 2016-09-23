package com.bsj4444.listviewtest2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;


import java.util.ArrayList;
import java.util.List;
/**
* 滑动列表头菜单自动消失
**/

public class MainActivity extends Activity {

    private ListView2 listView;
    private List<String> list;
    ListViewAdapter listViewAdapter;
    private Toolbar mToolbar;
    //private ListView mListView;
    private String[] mStr = new String[20];
    private int mTouchSlop;//最短距离
    private float mFirstY;     //刚碰Y
    private float mCurrentY;  //实现Y
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //触发移动事件的最短距离
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        listView=(ListView2)findViewById(R.id.listview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        list=new ArrayList<String>();
        for(int i=0;i<10;i++){
            list.add(""+i);
        }
        //加一个头部件
        View header=new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material
                )
        ));
        listView.addHeaderView(header);
        listViewAdapter=new ListViewAdapter(this,list);
        listView.setAdapter(listViewAdapter);
        listView.setOnTouchListener(myTouchListener);
        //listView.setSelection(position);显示第几项,瞬间跳转
//        listView.smoothScrollBy(distance,duration);
//        listView.smoothScrollByOffset(offset);
//        listView.smoothScrollToPosition(index);  实现平滑移动到指定的项
        //listViewAdapter.notifyDataSetChanged(); 刷新动态修改后的listVIew中的内容
        //View view=listView.getChildAt(position); 获取第position个itemView
        //listView.setEmptyView(R.id.xxx);设置空数据时显示的内容
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_UP:
//                    break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                }
//                return false;
//            }
//        });
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                switch (scrollState){
//                    case SCROLL_STATE_IDLE://滑动停止时
//                        break;
//                    case SCROLL_STATE_TOUCH_SCROLL://正在滚动时
//                        break;
//                    case SCROLL_STATE_FLING://手指用力滑动时
//                        break;              //在离开后listView由于惯性继续滑动
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view,
//                                 int firstVisibleItem,
//                                 int visibleItemCount,
//                                 int totalItemCount) {
//                //滚动时一直调用
//            }
//        });
    }
    private void toolbarAnim(int flag){
        if (mAnimator!=null&&mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if (flag==0){
            mAnimator=ObjectAnimator.ofFloat(
                    mToolbar,
                    "translationY",
                    mToolbar.getTranslationY(),
                    0
            );
        }else{
            mAnimator=ObjectAnimator.ofFloat(
                    mToolbar,
                    "translationY",
                    mToolbar.getTranslationY(),
                    -mToolbar.getHeight()
            );
        }
        mAnimator.start();
    }
    View.OnTouchListener myTouchListener=new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mFirstY=event.getY();break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY=event.getY();
                    if(mCurrentY-mFirstY>mTouchSlop){
                        direction=0;
                    }else if(mFirstY-mCurrentY>mTouchSlop){
                        direction=1;
                    }
                    if (direction==1){
                        if(mShow){
                            toolbarAnim(1);
                            mShow=!mShow;
                        }
                    }else if(direction==0){
                        toolbarAnim(0);
                        mShow=true;
                    }
                    break;
                case MotionEvent.ACTION_UP:break;
            }
            return false;
        }
    };
}
