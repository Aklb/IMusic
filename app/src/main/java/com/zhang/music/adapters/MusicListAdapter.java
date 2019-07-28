package com.zhang.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhang.music.R;
import com.zhang.music.activitys.PlayMusicActivity;

/**
 * Author by Zhang on 2019/7/20 18:37
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.VieHolder>{

    private Context Context;
    private View ItemView;
    private RecyclerView Rv;
    private boolean isCalcaulationRvHeight;

    public MusicListAdapter(Context context,RecyclerView recyclerView){
        Context=context;
        Rv=recyclerView;
    }

    @NonNull
    @Override
    public VieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemView=LayoutInflater.from(Context).inflate(R.layout.item_list_music,parent,false);
        return new VieHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VieHolder holder, int position) {
        setRecyclerViewHeight();

        Glide.with(Context)
                .load("http://res.lgdsunday.club/poster-1.png")
                .into(holder.ivIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Context, PlayMusicActivity.class);
                Context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }


    /*
    * 计算过程
    * 1.获取ItemView的高度
    * 2.获取itemView的数量
    * 3.使用itemViewHeight*itemViewNum=RecyclerView的高度
    * */
    private void setRecyclerViewHeight(){

        if (isCalcaulationRvHeight||Rv==null){
            return;
        }

        isCalcaulationRvHeight=true;

//        获取ItemView高度
       RecyclerView.LayoutParams itemViewlp=(RecyclerView.LayoutParams) ItemView.getLayoutParams();

//       获取itemView的数量
        int itemCount=getItemCount();

//       使用itemViewHeight*itemViewNum=RecyclerView的高度
        int recyclerViewHeight=itemViewlp.height*itemCount;
//       设置RecyclerView高度
        LinearLayout.LayoutParams rvlp=(LinearLayout.LayoutParams)Rv.getLayoutParams();
        rvlp.height=recyclerViewHeight;
        Rv.setLayoutParams(rvlp);
    }

    class VieHolder extends RecyclerView.ViewHolder{

        View itemView;
        ImageView ivIcon;

        public VieHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView=itemView;
            ivIcon=itemView.findViewById(R.id.iv_icon);
        }
    }
}
