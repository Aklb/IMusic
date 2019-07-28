package com.zhang.music.activitys;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhang.music.R;

/**
 * Author by Zhang on 2019/7/15 17:30
 */
public class BaseActivity extends Activity {

    private ImageView mIvBack,mIvMe;
    private TextView mTvTitle;

/*//    findViewById
    protected <T extends View> T fd (@IdRes int id){
        return findViewById(id);
    }

//    初始化NavigationBar*/
    protected void initNavBar(boolean isShowBack,String title,boolean isShowMe){

        mIvBack=findViewById(R.id.iv_back);
        mTvTitle=findViewById(R.id.tv_title);
        mIvMe=findViewById(R.id.iv_me);

        mIvBack.setVisibility(isShowBack?View.VISIBLE:View.GONE);
        mTvTitle.setText(title);
        mIvMe.setVisibility(isShowMe?View.VISIBLE:View.GONE);


        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
    }
}
