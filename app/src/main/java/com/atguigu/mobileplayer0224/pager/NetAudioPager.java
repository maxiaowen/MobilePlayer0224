package com.atguigu.mobileplayer0224.pager;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.mobileplayer0224.R;
import com.atguigu.mobileplayer0224.fragment.BaseFragment;

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

    //重写视图
    @Override
    public View initView() {
        Log.e("TAG", "NetAudioPager-initView");

        View view = View.inflate(context, R.layout.fragment_net_audio, null);

        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "NetAudioPager-initData");
        tvNomedia.setVisibility(View.VISIBLE);
        tvNomedia.setText("哈哈");

    }



}
