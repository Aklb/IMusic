package com.zhang.music.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.zhang.music.R;
import com.zhang.music.helps.MediaPlayerHelp;

/**
 * Author by Zhang on 2019/7/22 0:34
 */
public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private MediaPlayerHelp mMediaPlayerHelp;
    private boolean isPlaying;
    private String mPath;
    private View mView;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvIcon,mIvNeedle,mIvPlay;

    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;

    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext=context;

        mView=LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);

        mFlPlayMusic=mView.findViewById(R.id.fl_play_music);
        mIvIcon=mView.findViewById(R.id.iv_icon);
        mIvNeedle=mView.findViewById(R.id.iv_needle);
        mIvPlay=mView.findViewById(R.id.iv_play);

        /*
        * 1.定义所需要执行的动画
        *    1>光盘转动的动画
        *    2>指针指向光盘的动画
        *    3>指针离开光盘的动画
        * 2.startAnimation
        * */

        mPlayMusicAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_music_anim);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                trigger();
            }
        });
        mPlayNeedleAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedleAnim=AnimationUtils.loadAnimation(mContext,R.anim.stop_needle_anim);

        addView(mView);

        mMediaPlayerHelp=MediaPlayerHelp.getInstance(mContext);
    }

//    切换播放状态
    private void trigger(){
        if (isPlaying){
            stopMusic();
        }else {
            playMusic(mPath);
        }
    }

    public void getPath(){

    }

//    播放音乐
    public void playMusic(String path){
        mPath=path;
        isPlaying=true;
        mIvPlay.setVisibility(android.view.View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);

        /*
        * 1.判断当前音乐是否已经在播放的音乐
        * 2.如果当前的音乐是已经在播放的音乐，那么就直接执行start方法
        * 3.如果当前播放的音乐不是需要播放的音乐，那么就调用setPath方法
        * */
        if(mMediaPlayerHelp.getPath()!=null&&mMediaPlayerHelp.getPath().equals(path)) {
            mMediaPlayerHelp.start();
        }else {
            mMediaPlayerHelp.setPath(path);
            mMediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mMediaPlayerHelp.start();
                }
            });
        }
    }

//    停止播放
    public void stopMusic(){
        isPlaying=false;
        mIvPlay.setVisibility(android.view.View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

        mMediaPlayerHelp.pause();
    }

//    设置光盘中显示的音乐封面图片
    public void  setMusicIcon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }

    /*public void playMusic() {
    }*/
}
