package com.atguigu.mobileplayer0224.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.mobileplayer0224.R;
import com.atguigu.mobileplayer0224.domain.NetAudioBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class NetAudioFragmentAdapter extends BaseAdapter {
    private final Context context;
    private final List<NetAudioBean.ListBean> datas;

    /**
     * 视频
     */
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    /**
     * 文字
     */
    private static final int TYPE_TEXT = 2;

    /**
     * GIF图片
     */
    private static final int TYPE_GIF = 3;


    /**
     * 软件推广
     */
    private static final int TYPE_AD = 4;


    public NetAudioFragmentAdapter(Context context, List<NetAudioBean.ListBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null? 0: datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //得到item类型
    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象
        String type = datas.get(position).getType();
        Log.e("TAG", "type===" + type);
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else {
            itemViewType = TYPE_AD;//广播
        }
        return itemViewType;

    }

    //得到类型总数
    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = initView(convertView, getItemViewType(position), datas.get(position));
        return convertView;
    }

    private View initView(View convertView, int itemViewType, NetAudioBean.ListBean listBean) {
        switch (itemViewType) {
            case TYPE_VIDEO://视频

                VideoHoder videoHoder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_video_item, null);
                    videoHoder = new VideoHoder(convertView);
                    convertView.setTag(videoHoder);
                } else {
                    videoHoder = (VideoHoder) convertView.getTag();
                }

                //设置数据
//                videoHoder.setData(mediaItem);

                break;
            case TYPE_IMAGE://图片
                ImageHolder imageHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_image_item, null);
                    imageHolder = new ImageHolder(convertView);
                    convertView.setTag(imageHolder);
                } else {
                    imageHolder = (ImageHolder) convertView.getTag();
                }
                //设置数据
//                imageHolder.setData(mediaItem);
                break;
            case TYPE_TEXT://文字

                TextHolder textHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_text_item, null);
                    textHolder = new TextHolder(convertView);

                    convertView.setTag(textHolder);
                } else {
                    textHolder = (TextHolder) convertView.getTag();
                }

//                textHolder.setData(mediaItem);

                break;
            case TYPE_GIF://gif

                GifHolder gifHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_gif_item, null);
                    gifHolder = new GifHolder(convertView);

                    convertView.setTag(gifHolder);
                } else {
                    gifHolder = (GifHolder) convertView.getTag();
                }

//                gifHolder.setData(mediaItem);

                break;
            case TYPE_AD://软件广告

                ADHolder adHolder;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.all_ad_item, null);
                    adHolder = new ADHolder(convertView);
                    convertView.setTag(adHolder);
                } else {
                    adHolder = (ADHolder) convertView.getTag();
                }

                break;
        }
        return convertView;
    }

    class VideoHoder {

        public VideoHoder(View convertView) {

        }
    }

    class ImageHolder{

        public ImageHolder(View convertView) {

        }
    }

    class TextHolder {

        public TextHolder(View convertView) {

        }
    }

    class GifHolder {

        public GifHolder(View convertView) {

        }
    }

    class ADHolder {

        public ADHolder(View convertView) {

        }
    }
}
