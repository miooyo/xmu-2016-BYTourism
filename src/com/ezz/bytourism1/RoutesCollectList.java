package com.ezz.bytourism1;

/**
 * Created by 37492 on 2016/4/7.
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ezz.bean.Collect;
import com.ezz.bean.Scenic;
import com.ezz.bean.Scenicroute;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class RoutesCollectList extends BaseActivity implements OnItemClickListener,OnScrollListener{
    private ListView listView;
    private ArrayAdapter< String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private Integer userid;
    private Integer i = 0;;
    private List<Map<String, Object>> dataList;
    private String[] arr_data = {""};
    private String[] route = {"","","","",""};
    private String[] success = {"1","1","1","1","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routescollect);
        listView = (ListView)findViewById(R.id.routeslist);
        //处理数据
        userid = getPreferenceId();
        BmobQuery<Collect> query_city = new BmobQuery<Collect>();
        query_city.addWhereEqualTo("userid", userid);
        query_city.addWhereEqualTo("type", 2);
        //获取用户所有路线
        query_city.findObjects(RoutesCollectList.this, new FindListener<Collect>() {
            @Override
            public void onSuccess(List<Collect> collects) {



                // TODO 自动生成的方法存根
                for (Collect collect : collects) {
                    //对每一个路线获取相应的地点
                    Integer id = collect.getCid();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = id;
                    System.out.println("-----------------路线-----------------"+id);
//                    handler.sendMessage(msg);
                    arr_data[0] = id.toString();
                    simp_adapter = new SimpleAdapter(RoutesCollectList.this, getData(),
                            R.layout.routescollect_item,new String[]{"pic","text","id"} ,new int[]{R.id.image,R.id.title,R.id.age});
//
                    listView.setAdapter(simp_adapter);
                    listView.setOnItemClickListener(RoutesCollectList.this);
                    listView.setOnScrollListener(RoutesCollectList.this);
                    //获取路线详细
                    //getRouteName(id);
                }
            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO 自动生成的方法存根
                Log.i("fail!!---60", arg0 + arg1);
            }
        });

//        end

//        1.创建一个数据适配器
//        ArrayAdapter(上下文，当前ListView加载每一个列表项所对应的布局文件，数据源)
//        2.适配器加载数据源
//        3.视图（ListView）加载适配器
		/*SimpleAdapter
		 * 1.创建一个适配器
		 * 2.适配器加载数据源
		 * 3.视图(ListView)加载适配器
		 * listView.setAapter(arr_adapter)
		 * */

        dataList = new ArrayList<Map<String,Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr_data);
        listView.setAdapter(arr_adapter);

        /**
         * SimpleAdapter:
         * context：上下文
         * data：数据源(List< ?extends Map<String,?>>data)一个Map所组成的List集合
         * 			  每一个Map都会去对应ListView列表的一行
         * 			 每一个Map<键-值对>中的键必须包含所有from所指定的键
         * resource：列表项的布局文件ID
         * from：Map中的键名
         * to：绑定数据源视图的ID，与from成对应关系
         * */


    }
    private List<Map<String, Object>> getData(){
        for(int i = 0;i<arr_data.length;i++){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("pic", R.drawable.ic_launcher);
            map.put("text", "路线"+arr_data[i]);
            map.put("id", arr_data[i]);
            dataList.add(map);
        }
        return dataList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            final long id) {
        // TODO 自动生成的方法存根
        HashMap<String,String> text =  (HashMap<String,String>) listView.getItemAtPosition(position);
        String itemId = text.get("id");
        Toast.makeText(this, "position="+position +"text = "+text.get("id"), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RoutesCollectList.this,AllSightDetail.class);
        intent.putExtra("city_name", "厦门");
        intent.putExtra("rid", itemId);
        startActivity(intent);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO 自动生成的方法存根
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Map<String, Object> map = new HashMap<String,Object>();
                map.put("pic", R.drawable.ic_launcher);
                map.put("text", "增加项");
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
        // TODO 自动生成的方法存根

    }
    protected String getRouteName(int id){
        BmobQuery<Scenicroute> scenicroute_query = new BmobQuery<Scenicroute>();
        scenicroute_query.addWhereEqualTo("id", id);
        final String[] name = {""};
        scenicroute_query.findObjects(RoutesCollectList.this, new FindListener<Scenicroute>() {
            @Override
            public void onError(int arg0, String arg1) {
                // TODO 自动生成的方法存根
                Log.i("fail---70", arg0 + arg1);
            }

            @Override
            public void onSuccess(final List<Scenicroute> scenicroutes) {
                //路线信息

                // TODO 自动生成的方法存根
                if (scenicroutes.get(0).getOne() != 0) {
                    success[0] = "0";
                    BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                    query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getOne());
                    query_scenic.findObjects(RoutesCollectList.this, new FindListener<Scenic>() {
                        @Override
                        public void onError(int arg0, String arg1) {
                            // TODO 自动生成的方法存根
                            Log.i("fail---70", arg0 + arg1);
                        }

                        @Override
                        public void onSuccess(List<Scenic> scenics) {

                            name[0] =  scenics.get(0).getScenicname();



                        }
                    });
                }


            }

        });

    return name[0];
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    System.out.println("-----------------执行--"+arr_data[0]+"-----------------");
                    arr_data[0] = msg.obj.toString();
                    simp_adapter = new SimpleAdapter(RoutesCollectList.this, getData(), R.layout.routescollect_item,new String[]{"pic","text"} ,new int[]{R.id.image,R.id.title});
//
                    listView.setAdapter(simp_adapter);
//                    listView.setOnItemClickListener(RoutesCollectList.this);
//                    listView.setOnScrollListener(RoutesCollectList.this);
                    break;
                case 2:

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
            super.handleMessage(msg);
        }
    };
}