package com.example.lipin.viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import java.util.Timer;


public class P1 extends Fragment {

    private  View mainView;
    private ViewFlipper viewFlipper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //null 為了不讓換畫面時,又換回原本畫面重跑這段 //換畫面值不會變
        if (mainView == null){
            mainView = inflater.inflate(R.layout.fragment_p1,container,false);

            //viewFlipper ＝ 可以讓同一個頁面內的值有多個可換
            viewFlipper = mainView.findViewById(R.id.viewFlipper);

            //實作點擊監聽
            MyFlipperListener myFlipperListener = new MyFlipperListener();
            View f1 = viewFlipper.getChildAt(0);
            View f2 = viewFlipper.getChildAt(1);
            View f3 = viewFlipper.getChildAt(2);
            f1.setOnClickListener(myFlipperListener);
            f2.setOnClickListener(myFlipperListener);
            f3.setOnClickListener(myFlipperListener);
        }
        return mainView;
    }

    private  class  MyFlipperListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //每點擊一個就就換到下一個
            viewFlipper.showNext();
        }
    }


}
