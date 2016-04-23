package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 37492 on 2016/4/23.
 */
public class MyPartnerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_parter);
    }
    public void turnToLvInfo(View v){
        Intent intent = new Intent(MyPartnerActivity.this,Personal_centerActivity.class);
        startActivity(intent);
    }
}
