package com.zhang.music.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author by Zhang on 2019/7/19 22:54
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpacce;

    public GridSpaceItemDecoration(int space,RecyclerView parent){
        mSpacce=space;
        getRecvclerViewOffsets(parent);
    }

    /**
     * @description
     * @param  outRect Item的矩形边界
     * @param  view ItemView
     * @param  parent RecyclerView
     * @param  state RecyclerView的状态
     */

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left=mSpacce;

//        判断Item是不是每行第一个Item
//        if (parent.getChildLayoutPosition(view) % 3 == 0){
//            outRect.left=0;
//        }
    }

    private void getRecvclerViewOffsets(RecyclerView parent){
        /*
         * View margin
         * margin为正，则View会距离边界产生一个距离
         * margin为负，则View会超出边界产生一个距离
         * */
        LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin=-mSpacce;
        parent.setLayoutParams(layoutParams);
    }
}
