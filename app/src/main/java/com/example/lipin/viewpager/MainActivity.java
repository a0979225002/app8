package com.example.lipin.viewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    //統合管理
    private Fragment[] fs = new Fragment[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //目前陣列內有5個Fragment
        //P0和P4是要給予像IPHONE一樣的特效
        fs[0] = new P0();
        fs[1] = new P1();
        fs[2] = new P2();
        fs[3] = new P3();
        fs[4] = new P4();


        //需要有調變器做activity與viewPager做兩個之間的橋樑,這樣兩者才能互動
        // 先做出一個內部類具繼承FragmentStatePagerAdapter
        //然後實作內部類,去取得FragmentManager
        viewPager.setAdapter(new MyPagerAdapter());

       //只問你最後的那一頁,你拿到那頁想幹什麼事
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //position可以知道你現在在哪個Fragment
                super.onPageSelected(position);
               if (position ==0){
                   viewPager.setCurrentItem(position+1,true);
               }else if (position ==fs.length-1){//position是從0開始,所以要減1
                   //如果畫面到最後一頁就讓它跳回上一頁
                   viewPager.setCurrentItem(position-1,true);
               }
            }
        });
        //讓它一進來是第一頁,第二個參數畫面切換速度,true為慢速切換
        viewPager.setCurrentItem(1,true);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(){
            //建構式改寫成一開始先去抓取MainActivity的FragmentManager
            super(MainActivity.this.getSupportFragmentManager());
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            //裡面需要的是給出第幾個做什麼事
            return fs[position];
        }

        @Override
        public int getCount() {
            //裡面需要的是給出總共有幾個Fragment
            return fs.length;
        }
    }

    public void gotoPage1(View view) {
        viewPager.setCurrentItem(1,false);
    }
    public void gotoPage2(View view) {
        viewPager.setCurrentItem(2,false);
    }
    public void gotoPage3(View view) {
        viewPager.setCurrentItem(3,false);
    }
}
