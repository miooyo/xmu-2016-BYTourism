package com.ezz.bytourism1;

/**
 * Created by 37492 on 2016/4/7.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
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
    private List<Map<String, Object>> dataList;
    private String[] arr_data = {""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routescollect);

        //处理数据
        userid = getPreferenceId();
        BmobQuery<Collect> query_city = new BmobQuery<Collect>();
        query_city.addWhereEqualTo("userid", userid);
        query_city.addWhereEqualTo("type", 2);
        //获取用户所有路线
        query_city.findObjects(RoutesCollectList.this, new FindListener<Collect>() {
            @Override
            public void onSuccess(List<Collect> collects) {
                int i=0;
                final String[] route = {""};
                // TODO 自动生成的方法存根
                for(Collect collect:collects){
                    //对每一个路线获取相应的地点
                    int id = collect.getCid();
                    BmobQuery<Scenicroute> scenicroute_query = new BmobQuery<Scenicroute>();
                    scenicroute_query.addWhereEqualTo("id", id);
                    scenicroute_query.findObjects(RoutesCollectList.this, new FindListener<Scenicroute>() {
                        @Override
                        public void onError(int arg0, String arg1) {
                            // TODO 自动生成的方法存根
                            Log.i("fail---70", arg0 + arg1);
                        }

                        @Override
                        public void onSuccess(List<Scenicroute> scenicroutes) {
                            //路线信息

                            // TODO 自动生成的方法存根
                            if (scenicroutes.get(0).getOne() != 0) {
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
                                        // TODO 自动生成的方法存根
                                        Message msg = new Message();

                                        route[0] = scenics.get(0).getScenicname();

                                    }
                                });
                            }
                            if (scenicroutes.get(0).getTwo() != 0) {
                                BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                                query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getTwo());
                                query_scenic.findObjects(RoutesCollectList.this, new FindListener<Scenic>() {
                                    @Override
                                    public void onError(int arg0, String arg1) {
                                        // TODO 自动生成的方法存根
                                        Log.i("fail---70", arg0 + arg1);
                                    }

                                    @Override
                                    public void onSuccess(List<Scenic> scenics) {
                                        // TODO 自动生成的方法存根

                                        route[1] = scenics.get(0).getScenicname();
                                    }
                                });
                            }
                            if (scenicroutes.get(0).getThree() != 0) {
                                BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                                query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getThree());
                                query_scenic.findObjects(RoutesCollectList.this, new FindListener<Scenic>() {
                                    @Override
                                    public void onError(int arg0, String arg1) {
                                        // TODO 自动生成的方法存根
                                        Log.i("fail---70", arg0 + arg1);
                                    }

                                    @Override
                                    public void onSuccess(List<Scenic> scenics) {
                                        // TODO 自动生成的方法存根
                                        route[2] = scenics.get(0).getScenicname();
                                    }
                                });
                            }
                            if (scenicroutes.get(0).getFour() != 0) {
                                BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                                query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getFour());
                                query_scenic.findObjects(RoutesCollectList.this, new FindListener<Scenic>() {
                                    @Override
                                    public void onError(int arg0, String arg1) {
                                        // TODO 自动生成的方法存根
                                        Log.i("fail---70", arg0 + arg1);
                                    }

                                    @Override
                                    public void onSuccess(List<Scenic> scenics) {
                                        // TODO 自动生成的方法存根
                                        route[3] = scenics.get(0).getScenicname();
                                    }
                                });
                            }
                            if (scenicroutes.get(0).getFive() != 0) {
                                BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                                query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getFive());
                                query_scenic.findObjects(RoutesCollectList.this, new FindListener<Scenic>() {
                                    @Override
                                    public void onError(int arg0, String arg1) {
                                        // TODO 自动生成的方法存根
                                        Log.i("fail---70", arg0 + arg1);
                                    }

                                    @Override
                                    public void onSuccess(List<Scenic> scenics) {
                                        // TODO 自动生成的方法存根
                                        route[4] = scenics.get(0).getScenicname();
                                    }
                                });
                            }
                        }

                    });

                    arr_data[i] = route[0]+"-"+route[1]+"-"+route[2]+"-"+route[3]+"-"+route[4];
                    i++;
                    //将路线信息置空
                    for (int j=0;j<5;j++){
                        route[j]=null;
                    }
                }
            }
            @Override
            public void onError(int arg0, String arg1) {
                // TODO 自动生成的方法存根
                Log.i("fail!!---60", arg0+arg1);
            }
        });

        //end
        listView = (ListView)findViewById(R.id.routeslist);
        //1.创建一个数据适配器
        //ArrayAdapter(上下文，当前ListView加载每一个列表项所对应的布局文件，数据源)
        //2.适配器加载数据源
        //3.视图（ListView）加载适配器
		/*SimpleAdapter
		 * 1.创建一个适配器
		 * 2.适配器加载数据源
		 * 3.视图(ListView)加载适配器
		 * listView.setAapter(arr_adapter)
		 * */

        dataList = new ArrayList<Map<String,Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr_data);
        //	listView.setAdapter(arr_adapter);

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
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.routescollect_item,new String[]{"pic","text"} ,new int[]{R.id.image,R.id.title});

        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }
    private List<Map<String, Object>> getData(){
        for(int i = 0;i<5;i++){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("pic", R.drawable.ic_launcher);
            map.put("text", arr_data[i]);
            dataList.add(map);

        }
        return dataList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO 自动生成的方法存根
        String text = listView.getItemAtPosition(position)+"";
        Toast.makeText(this, "position="+position +"text = "+text, Toast.LENGTH_LONG).show();

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
}