package com.app.myapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 建立Pager Adapter
        InnerPagerAdapter pagerAdapter = new InnerPagerAdapter(
                getSupportFragmentManager());

        // 把Pager Adapter設定給ViewPager
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        // 把ViewPager設定給TabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class InnerPagerAdapter extends FragmentPagerAdapter {

        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            // 根據目前頁面的編號，傳回對應的fragment物件
            switch (position) {
                case 0:
                    fragment = new FirstFragment();
                    break;
                case 1:
                    fragment = new SecondFragment();
                    break;
                case 2:
                    fragment = new ThreeFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // 傳回頁面的總數
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "BMI的計算與儲存";
                case 1:
                    return "星座判定與儲存";
                case 2:
                    return "自我介紹";
                default:
                    return null;
            }
        }
    }
}
