package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 37492 on 2016/4/23.
 */
public class LvBildInfoActivity extends Activity {
    private NumberPicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lv_buildinfo);


    }
    public void backlvbuild(View v){
        Intent intent = new Intent(LvBildInfoActivity.this,LvSelectActivity.class);
        startActivity(intent);
    }
}
