package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 37492 on 2016/4/21.
 */
public class LvSelectActivity extends Activity {
    private List<View> lv_selectView;
    private ViewPager myPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_select);

        lv_selectView = new ArrayList<View>();
        View single = View.inflate(this,R.layout.lv_singlecity,null);
        View multi = View.inflate(this,R.layout.lv_multicity,null);
        View lv_build = View.inflate(this,R.layout.lv_build,null);
        lv_selectView.add(single);
        lv_selectView.add(multi);
        lv_selectView.add(lv_build);
        Lv_PagerAdapter mypagerAdapter = new Lv_PagerAdapter(lv_selectView);

        myPager=(ViewPager) findViewById(R.id.mypager);
        myPager.setAdapter(mypagerAdapter);
    }
    public void returnHome(View view){
        Intent intent = new Intent(LvSelectActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void turnToPersonal(View view){
        Intent intent = new Intent(LvSelectActivity.this,Personal_centerActivity.class);
        startActivity(intent);
    }
}
