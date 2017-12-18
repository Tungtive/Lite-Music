package com.DC.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



/**
 * @Author: pyz
 * @Package: com.pyz.viewpagerdemo.adapter
 * @Description: TODO
 * @Project: ViewPagerDemo
 * @Date: 2016/8/18 11:49
 */
public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTilte;

    public MyFragmentStatePagerAdapter(FragmentManager fm, String[] tabTitle) {
        super(fm);
        this.tabTilte = tabTitle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
              //  return new VPFragment1();
            case 1:
              //  return new VPFragment2();
            case 2:
              //  return new VPFragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTilte.length;
    }
}
