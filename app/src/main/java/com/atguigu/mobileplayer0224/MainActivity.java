package com.atguigu.mobileplayer0224;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.atguigu.mobileplayer0224.fragment.BaseFragment;
import com.atguigu.mobileplayer0224.pager.LocalAudioPager;
import com.atguigu.mobileplayer0224.pager.LocalVideoPager;
import com.atguigu.mobileplayer0224.pager.NetAudioPager;
import com.atguigu.mobileplayer0224.pager.NetVideoPager;
import com.atguigu.mobileplayer0224.pager.RecyclerViewPager;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position;
    /**
     * 缓存当前显示的Fragment
     */
    private Fragment tempFragment;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MainActivity","onCreate");
        setContentView(R.layout.activity_main);
        //初始化控件
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        initFragment();
        //设置监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_main.check(R.id.rb_local_video);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }


    private void initFragment() {
        //把各个页面实例化放入集合中
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoPager());
        fragments.add(new LocalAudioPager());
        fragments.add(new NetAudioPager());
        fragments.add(new NetVideoPager());
        fragments.add(new RecyclerViewPager());

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_local_video:
                    position = 0;
                    break;
                case R.id.rb_local_audio:
                    position = 1;
                    break;

                case R.id.rb_net_audio:
                    position = 2;
                    break;

                case R.id.rb_net_video:
                    position  = 3;
                    break;
                case R.id.rb_recyclerview:
                    position = 4;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment currentFragment = fragments.get(position);//要显示的Fragment
            addFragment(currentFragment);


        }
    }

    private void addFragment(Fragment currentFragment) {
        if(tempFragment != currentFragment){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            //判断是否添加过-没有添加
            if(!currentFragment.isAdded()){
                //把之前的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }
                //添加当前的
                ft.add(R.id.fl_content,currentFragment);
            }else{
//       当前Fragment已经添加过
                //把之前的隐藏
                if(tempFragment != null){
                    ft.hide(tempFragment);
                }
                //显示当前的
                ft.show(currentFragment);

            }

            ft.commit();//提交事务
            //把当前的缓存起来
            tempFragment = currentFragment;

        }

    }

    /**
     * 是否已经退出
     */
    private boolean isExit = false;


    /**
     * 防止不小心点击退出
     * @param keyCode
     * @param event
     * @return
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode ==KeyEvent.KEYCODE_BACK){
//            if(position!= 0){
//                rg_main.check(R.id.rb_local_video);
//                return true;
//            }else if(!isExit){
//                Toast.makeText(MainActivity.this, "再按一次推出软件", Toast.LENGTH_SHORT).show();
//                isExit = true;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        isExit = false;
//                    }
//                }, 2000);
//
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}


