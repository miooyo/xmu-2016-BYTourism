package com.ezz.bytourism1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import com.ezz.bean.City;
import com.ezz.bean.Collect;
import com.ezz.bean.Partner;

import java.util.*;

/**
 * Created by 37492 on 2016/4/21.
 */
public class LvSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
    private List<View> lv_selectView;
    private ViewPager myPager;
    private ListView list_mul;
    //private ArrayAdapter<String> array_Adapter1,array_Adapter2;
    private Integer i_1 = 0;
    private Integer i_2= 0;

    //private ArrayAdapter< String> arr_adapter;
    private SimpleAdapter simp_adapter;
    //private Integer userid;
    private List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
    //private String[] arr_data = {""};

    private Integer single_num;
    private ArrayList<Integer> idone = new ArrayList<Integer>();
    private ArrayList<String> startname  = new ArrayList<String>();
    private  ArrayList<String> endname= new ArrayList<String>();
    private  ArrayList<String> title= new ArrayList<String>();
    private ArrayList<String> yuetime= new ArrayList<String>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_select);

        lv_selectView = new ArrayList<View>();
        // View single = View.inflate(this,R.layout.lv_singlecity,null);
        View multi = View.inflate(this,R.layout.lv_multicity,null);

        View lv_build = View.inflate(this,R.layout.lv_build,null);
        Button bt = (Button) lv_build.findViewById(R.id.turnbuild);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //增加自己的代码......
                Intent intent = new Intent(LvSelectActivity.this,LvBildInfoActivity.class);
                startActivity(intent);
            }
        });
        //lv_selectView.add(single);
        lv_selectView.add(multi);
        lv_selectView.add(lv_build);
        Lv_PagerAdapter mypagerAdapter = new Lv_PagerAdapter(lv_selectView);

        myPager=(ViewPager) findViewById(R.id.mypager);
        myPager.setAdapter(mypagerAdapter);

        list_mul = (ListView) multi.findViewById(R.id.listView_mul);
        //list_sing = (ListView) single.findViewById(R.id.listView_sing);

       // String[] array_data = {"驴友邀请1","驴友邀请2","驴友邀请3"};
        //array_Adapter1 = new ArrayAdapter<String>(LvSelectActivity.this,android.R.layout.simple_list_item_1,array_data);
        //array_Adapter2 = new ArrayAdapter<String>(LvSelectActivity.this,android.R.layout.simple_list_item_1,array_data);
        //list_mul.setAdapter(array_Adapter1);
        //list_sing.setAdapter(array_Adapter2);


        BmobQuery<Partner> query_multi = new BmobQuery<Partner>();


        query_multi.addWhereEqualTo("state", "0");

        query_multi.findObjects(LvSelectActivity.this, new FindListener<Partner>() {
            @Override
            public void onSuccess(List<Partner> singles) {
                System.out.println("------开始-------");


                // TODO �Զ����ɵķ������
                single_num = singles.size();
                System.out.println("------"+single_num+"-------");
                int i = 0;
                for (Partner single : singles) {
                    System.out.println("-------进入循环-------");
                    Integer istart = single.getStartid();

                    BmobQuery<City> query_name1 = new BmobQuery<City>();
                    query_name1.addWhereEqualTo("id", istart);
                    query_name1.findObjects(LvSelectActivity.this, new FindListener<City>() {
                        @Override
                        public void onSuccess(List<City> list) {
                            Toast.makeText(LvSelectActivity.this,list.get(0).toString(),Toast.LENGTH_LONG).show();
        System.out.println(list.get(0).toString()+"----------");
        System.out.println(list.get(0).getCityname()+"---city-------");
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                                startname.add(list.get(0).getCityname());
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });
                    Integer iend = single.getEndid();
                    Toast.makeText(LvSelectActivity.this,"end:"+iend,Toast.LENGTH_LONG).show();
                    BmobQuery<City> query_name2 = new BmobQuery<City>();
                    query_name2.addWhereEqualTo("id", iend);
                    query_name2.findObjects(LvSelectActivity.this, new FindListener<City>() {
                        @Override
                        public void onSuccess(List<City> list) {
                            Message msg = new Message();
                            msg.what = 2;
                            handler.sendMessage(msg);
                            endname.add(list.get(0).getCityname());
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });

                    title.add(single.getTitle());
                    yuetime.add(single.getYuetime());
                    Toast.makeText(LvSelectActivity.this,title.get(i),Toast.LENGTH_LONG).show();
                    i++;

                    //��ȡ·����ϸ
                    //getRouteName(id);
                }
                System.out.println(endname.toString());


            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO �Զ����ɵķ������
                Log.i("fail!!---60", arg0 + arg1);
                Toast.makeText(LvSelectActivity.this,"fail",Toast.LENGTH_LONG).show();
            }
        });


    }
    private List<Map<String, Object>> getData1(){
        for(int i = 0;i<single_num;i++){
            Map<String, Object> map = new HashMap<String,Object>();
//            map.put("idone", idone.get(i));
            map.put("startid", startname.get(i));
            map.put("endid", endname.get(i));
            map.put("title", title.get(i));
            map.put("yuetime", yuetime.get(i));
            System.out.println(map.toString());
            dataList.add(map);
        }
        return dataList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            final long id) {
        // TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
        HashMap<String,String> text =  (HashMap<String,String>) list_mul.getItemAtPosition(position);
        String itemId = text.get("id");
        Toast.makeText(this, "position="+position +"text = "+text.get("id"), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LvSelectActivity.this,AllSightDetail.class);
        intent.putExtra("city_name", "锟斤拷锟斤拷");
        intent.putExtra("rid", itemId);
        startActivity(intent);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Map<String, Object> map = new HashMap<String,Object>();
                map.put("pic", R.drawable.ic_launcher);
                map.put("text", "锟斤拷锟斤拷锟斤拷");
                dataList.add(map);
                simp_adapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
            default:
                break;
        }
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�

    }
    public void returnHome(View view){
        Intent intent = new Intent(LvSelectActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void turnToPersonal(View view){
        Intent intent = new Intent(LvSelectActivity.this,Personal_centerActivity.class);
        startActivity(intent);
    }
    public void setView(){
        simp_adapter = new SimpleAdapter(LvSelectActivity.this, getData1(),
                R.layout.lv_mulitem,new String[] {"startid","endid","title","yuetime"}
                ,new int[]{R.id.startid,R.id.endid,R.id.title,R.id.yuetime});
        System.out.println("-------222-------");
        list_mul.setAdapter(simp_adapter);
        list_mul.setOnItemClickListener(LvSelectActivity.this);
        list_mul.setOnScrollListener(LvSelectActivity.this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    i_1 = i_1+1;
                    if((i_1 == single_num) && (i_2 == single_num)){
                        setView();
                    }
                    break;
                case 2:
                    i_2 = i_2+1;
                    if(i_1==single_num&&i_2==single_num){
                        setView();
                    }
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                default:
                    break;
            }
        }
    };

}
