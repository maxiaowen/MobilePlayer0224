package com.atguigu.mobileplayer0224.pager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mobileplayer0224.R;
import com.atguigu.mobileplayer0224.activity.ShowImageAndGifActivity;
import com.atguigu.mobileplayer0224.adapter.NetAudioFragmentAdapter;
import com.atguigu.mobileplayer0224.domain.MediaItem;
import com.atguigu.mobileplayer0224.domain.NetAudioBean;
import com.atguigu.mobileplayer0224.fragment.BaseFragment;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：杨光福 on 2017/5/19 11:47
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class NetAudioPager extends BaseFragment {

    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.tv_nomedia)
    TextView tvNomedia;

    public static final String uri = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

    private ArrayList<MediaItem> mediaItems;

    private List<NetAudioBean.ListBean> datas;

    private NetAudioFragmentAdapter adapter;

    //重写视图
    @Override
    public View initView() {
        Log.e("TAG", "NetAudioPager-initView");

        View view = View.inflate(context, R.layout.fragment_net_audio, null);

        ButterKnife.inject(this, view);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                NetAudioBean.ListBean listEntity = datas.get(position);
                if(listEntity !=null ){
                    //3.传递视频列表
                    Intent intent = new Intent(context,ShowImageAndGifActivity.class);
                    if(listEntity.getType().equals("gif")){
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url",url);
                        context.startActivity(intent);
                    }else if(listEntity.getType().equals("image")){
                        String url = listEntity.getImage().getThumbnail_small().get(0);
                        intent.putExtra("url",url);
                        context.startActivity(intent);
                    }
                }


            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "NetAudioPager-initData");

        getDataFromNet();
    }

    public void getDataFromNet() {
        //配置联网请求地址
        final RequestParams request = new RequestParams(uri);
        x.http().get(request, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG","xUtils联网成功=="+result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","xUtils联网失败=="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析json数据和显示数据
     * @param json
     */
    private void processData(String json) {
        NetAudioBean netAudioBean = new Gson().fromJson(json, NetAudioBean.class);
            datas = netAudioBean.getList();
        Log.e("TAG","解决=="+datas.get(0).getText());

        if(datas != null && datas.size() >0){
            //有视频
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            adapter = new NetAudioFragmentAdapter(context,datas);
            listview.setAdapter(adapter);
        }else{
            //没有视频
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
