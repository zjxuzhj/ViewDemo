package com.zhj.viewdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TabButton mTab1;
    private TabButton mTab2;
    private TabButton mTab3;
    private FragmentManager mManager;
    private Fragment1 mFg1;
    private Fragment2 mFg2;
    private Fragment3 mFg3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFg1 = new Fragment1();
        mFg2 = new Fragment2();
        mFg3 = new Fragment3();
        //使用V4包,用getSupportFragmentManager()得到FragmentManager的对象
        mManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = mManager.beginTransaction();
        //把fragment对象显示到指定资源id的组件里面
        ft.replace(R.id.ll_main, mFg1);
        ft.commit();
        initView();
        mTab1.setChecked(true);
    }

    private void initView() {
        mTab1 = (TabButton) findViewById(R.id.tab1);
        mTab2 = (TabButton) findViewById(R.id.tab2);
        mTab3 = (TabButton) findViewById(R.id.tab3);
        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTab1.isChecked()) {
                    mTab1.setChecked(true);
                    mTab2.setChecked(false);
                    mTab3.setChecked(false);
                    FragmentTransaction ft = mManager.beginTransaction();
                    ft.replace(R.id.ll_main, mFg1);
                    ft.commit();
                }
            }
        });
        mTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTab2.isChecked()) {
                    mTab1.setChecked(false);
                    mTab2.setChecked(true);
                    mTab3.setChecked(false);
                    FragmentTransaction ft = mManager.beginTransaction();
                    ft.replace(R.id.ll_main, mFg2);
                    ft.commit();
                }
            }
        });
        mTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTab3.isChecked()) {
                    mTab1.setChecked(false);
                    mTab2.setChecked(false);
                    mTab3.setChecked(true);
                    FragmentTransaction ft = mManager.beginTransaction();
                    ft.replace(R.id.ll_main, mFg3);
                    ft.commit();
                }
            }
        });
    }
}
