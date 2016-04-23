package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 37492 on 2016/4/23.
 */
public class MyPatnerInfo extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.partnerinfo);
        }
        public void turnback(View v){
            Intent intent = new Intent(MyPatnerInfo.this, MyPartnerActivity.class);
            startActivity(intent);

    }
}
