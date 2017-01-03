package com.jiaotang.mysqltest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;

public class Main3Activity extends AppCompatActivity {

    private BGABanner bgaBanner;
    private List<View> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        bgaBanner=(BGABanner)findViewById(R.id.banner_content);

        //添加轮播图片
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.login_xun));
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.test1));
        views.add(BGABannerUtil.getItemImageView(this, R.drawable.test2));
        //添加轮播文案
        List<String> tips = new ArrayList<String>();
        tips.add("欢迎来到巡检中心");
        tips.add("XX小区发生水管泄露，请迅速处理");
        tips.add("XX小区发生水管泄露，请迅速处理");
        //实现显示
        bgaBanner.setData(views,null,tips);
        //设置轮转点击事件
        bgaBanner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                Log.d("data","点击了："+position);
            }
        });

    }


}
