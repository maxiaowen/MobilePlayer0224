package com.atguigu.mobileplayer0224.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.atguigu.mobileplayer0224.domain.Lyric;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/26.
 */

public class LyricShowView extends TextView {

    private Paint paint;

    private int width;
    private int heigth;

    private ArrayList<Lyric> lyrics;


    public LyricShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.GREEN);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置文字大小
        paint.setTextSize(16);
        //设置居中
        paint.setTextAlign(Paint.Align.CENTER);

        //准备歌词
        lyrics = new ArrayList<>();
        Lyric lyric = new Lyric();
        for (int i = 0; i < 10000; i++) {
            //不同歌词
            lyric.setContent("aaaaaaaaaaaa_" + i);
            lyric.setSleepTime(2000);
            lyric.setTimePoint(2000*i);

            //添加到集合
            lyrics.add(lyric);
            //重新创建新对象
            lyric = new Lyric();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        heigth = h;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("没有找到歌词...",width/2,heigth/2,paint);
    }
}
