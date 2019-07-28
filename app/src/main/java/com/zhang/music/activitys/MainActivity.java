package com.zhang.music.activitys;


import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhang.music.R;
import com.zhang.music.adapters.MusicGridAdapter;
import com.zhang.music.adapters.MusicListAdapter;
import com.zhang.music.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGriAdapter;
    private MusicListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initNavBar(false, "IMusic", true);

        mRvGrid = findViewById(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRvGrid));
        mRvGrid.setNestedScrollingEnabled(false);
        mGriAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(mGriAdapter);


        /*
        * 1.假如已知列表高度的情况下，可以直接在布局中把RecycleView的高度定义上
        * 2.不知道列表高度的情况表下，需要手动计算RecycleView的高度
        * */
        mRvList=findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter=new MusicListAdapter(this,mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
