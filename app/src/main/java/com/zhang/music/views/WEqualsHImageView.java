package com.zhang.music.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Author by Zhang on 2019/7/19 22:03
 */

//AppCompatImageView
public class WEqualsHImageView extends AppCompatImageView {
    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);

/*//        获取View宽度
        int width=MeasureSpec.getSize(widthMeasureSpec);

//        获取View模式（match_parent、warp_content、具体dp）
        int mode=MeasureSpec.getMode(widthMeasureSpec);*/
    }
}
