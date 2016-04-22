package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by 37492 on 2016/4/7.
 */
public class MyCollectActivity extends BaseActivity {
	private ImageButton btn_back,route,buttonscenic;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collect);
//        TextView username = (TextView)findViewById(R.id.nameview);
//        TextView userid = (TextView)findViewById(R.id.idview);
//        username.setText(getPreferenceName());
//        userid.setText("365354"+getPreferenceId());
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(MyCollectActivity.this,Personal_centerActivity.class);
				startActivity(intent);
			}
		});
        buttonscenic = (ImageButton) findViewById(R.id.buttonscenic);
        buttonscenic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 自动生成的方法存根
                Intent intent = new Intent(MyCollectActivity.this,SightCollectList.class);
                startActivity(intent);
            }
        });
        route = (ImageButton) findViewById(R.id.routes);
        route.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
               turnToRoutes();
            }
        });
    }
    public void turnToRoutes(){
        Intent intent = new Intent(MyCollectActivity.this,RoutesCollectList.class);
        startActivity(intent);
    }
    
}
