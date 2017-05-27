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

    private Paint paintGreen;
    private Paint paintWhite;

    private int width;
    private int heigth;

    /**
     * 表示的是歌词列表中的哪一句
     */
    private int index = 0;
    //每句歌词所占的高度
    private float textHeight = 20;

    private ArrayList<Lyric> lyrics;
    private int nextShowLyric;


    public LyricShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paintGreen = new Paint();
        //设置画笔颜色
        paintGreen.setColor(Color.GREEN);
        //设置抗锯齿
        paintGreen.setAntiAlias(true);
        //设置文字大小
        paintGreen.setTextSize(16);
        //设置居中
        paintGreen.setTextAlign(Paint.Align.CENTER);

        paintWhite = new Paint();
        //设置画笔颜色
        paintWhite.setColor(Color.WHITE);
        //设置抗锯齿
        paintWhite.setAntiAlias(true);
        //设置文字大小
        paintWhite.setTextSize(16);
        //设置居中
        paintWhite.setTextAlign(Paint.Align.CENTER);

//        //准备歌词
//        lyrics = new ArrayList<>();
//        Lyric lyric = new Lyric();
//        for (int i = 0; i < 10000; i++) {
//            //不同歌词
//            lyric.setContent("aaaaaaaaaaaa_" + i);
//            lyric.setSleepTime(2000);
//            lyric.setTimePoint(2000*i);
//
//            //添加到集合
//            lyrics.add(lyric);
//            //重新创建新对象
//            lyric = new Lyric();
//        }
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
        
        if(lyrics != null && lyrics.size() > 0) {
            //有歌词
            //当前句-中心的哪一句
            String currentContent = lyrics.get(index).getContent();
            canvas.drawText(currentContent,width/2,heigth/2,paintGreen);

            //得到中间句的坐标
            float tempY = heigth/2;
            //绘制前面部分
            for (int i = index - 1; i >= 0; i--) {

                //得到前一部分多月的歌词内容
                String preContent = lyrics.get(i).getContent();

                tempY = tempY - textHeight;
                if (tempY < 0) {
                    break;
                }

                //绘制内容
                canvas.drawText(preContent, width / 2, tempY, paintWhite);
            }

            tempY = heigth / 2;
            //绘制后面部分
            for (int i = index + 1; i < lyrics.size(); i++) {
                //得到后一部分多月的歌词内容
                String nextContent = lyrics.get(i).getContent();

                tempY = tempY + textHeight;
                if (tempY > heigth) {
                    break;
                }
                //绘制内容
                canvas.drawText(nextContent, width / 2, tempY, paintWhite);
            }

        }else {
            canvas.drawText("没有找到歌词...",width/2,heigth/2,paintGreen);
        }
    }

    public void setNextShowLyric(int nextShowLyric) {
        this.nextShowLyric = nextShowLyric;

        if(lyrics == null || lyrics.size() == 0) {
            return;
        }

        for (int i = 1; i < lyrics.size(); i++) {

            if (nextShowLyric < lyrics.get(i).getTimePoint()) {
                int tempIndex = i - 1;
                if (nextShowLyric >= lyrics.get(tempIndex).getTimePoint()) {
                    //中间高亮显示的哪一句
                    index = tempIndex;
                }
            }

        }

        //什么方法导致onDraw
        invalidate();
    }

    public void setLyrics(ArrayList<Lyric> lyrics) {
        this.lyrics = lyrics;
    }

}
