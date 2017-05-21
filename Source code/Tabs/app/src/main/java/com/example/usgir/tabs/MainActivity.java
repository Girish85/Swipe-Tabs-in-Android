package com.example.usgir.tabs;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        myadapter = new Myadapter(getSupportFragmentManager());
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabber);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(myadapter);
    }
    public class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Myfragment.getfragment(position+1);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0 : return "Home";
                case 1 : return "Login";
                default: return "Sample";
            }
        }
    }
    public static class Myfragment extends Fragment{

        public static Fragment getfragment(int position)
        {
            Myfragment myfragment = new Myfragment();
            Bundle args = new Bundle();
            args.putInt("pos",position);
            myfragment.setArguments(args);
            return myfragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            int i = getArguments().getInt("pos");
            if (i==2)
            {
                View view = inflater.inflate(R.layout.fragment_second,container,false);
                return view;
            }
            else
            {
                View view = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) view.findViewById(R.id.textView);
                textView.setText("This is Tab : " + Integer.toString(getArguments().getInt("pos")));
                return view;
            }
        }
    }
}
