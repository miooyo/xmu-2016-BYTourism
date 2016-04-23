package com.ezz.bytourism1;

/**
 * Created by 37492 on 2016/4/7.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class MyRoutesCollectList extends BaseActivity implements OnItemClickListener,OnScrollListener{
    private ListView listView;
    private ArrayAdapter< String> arr_adapter;
    private SimpleAdapter simp_adapter;
    private Integer userid;
    private Integer i = 0;
    private List<Map<String, Object>> dataList;
    private String[] arr_data = {""};
    private String[] route = {"","","","",""};
    private String[] success = {"1","1","1","1","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO �Զ����ɵķ������
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routescollect);
        listView = (ListView)findViewById(R.id.routeslist);
        //��������
        userid = getPreferenceId();
        BmobQuery<Collect> query_city = new BmobQuery<Collect>();
        query_city.addWhereEqualTo("userid", userid);
        query_city.addWhereEqualTo("type", 2);
        //��ȡ�û�����·��
        query_city.findObjects(MyRoutesCollectList.this, new FindListener<Collect>() {
            @Override
            public void onSuccess(List<Collect> collects) {



                // TODO �Զ����ɵķ������
                for (Collect collect : collects) {
                    //��ÿһ��·�߻�ȡ��Ӧ�ĵص�
                    Integer id = collect.getCid();
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = id;
                    System.out.println("-----------------·��-----------------"+id);
//                    handler.sendMessage(msg);
                    arr_data[0] = id.toString();
                    simp_adapter = new SimpleAdapter(MyRoutesCollectList.this, getData(),
                            R.layout.routescollect_item,new String[]{"pic","text","id"} ,new int[]{R.id.image,R.id.title,R.id.age});
//
                    listView.setAdapter(simp_adapter);
                    listView.setOnItemClickListener(MyRoutesCollectList.this);
                    listView.setOnScrollListener(MyRoutesCollectList.this);
                    //��ȡ·����ϸ
                    //getRouteName(id);
                }
            }

            @Override
            public void onError(int arg0, String arg1) {
                // TODO �Զ����ɵķ������
                Log.i("fail!!---60", arg0 + arg1);
            }
        });

//        end

//        1.����һ������������
//        ArrayAdapter(�����ģ���ǰListView����ÿһ���б�������Ӧ�Ĳ����ļ�������Դ)
//        2.��������������Դ
//        3.��ͼ��ListView������������
		/*SimpleAdapter
		 * 1.����һ��������
		 * 2.��������������Դ
		 * 3.��ͼ(ListView)����������
		 * listView.setAapter(arr_adapter)
		 * */

        dataList = new ArrayList<Map<String,Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr_data);
        listView.setAdapter(arr_adapter);

        /**
         * SimpleAdapter:
         * context��������
         * data������Դ(List< ?extends Map<String,?>>data)һ��Map����ɵ�List����
         * 			  ÿһ��Map����ȥ��ӦListView�б��һ��
         * 			 ÿһ��Map<��-ֵ��>�еļ������������from��ָ���ļ�
         * resource���б���Ĳ����ļ�ID
         * from��Map�еļ���
         * to��������Դ��ͼ��ID����from�ɶ�Ӧ��ϵ
         * */


    }
    private List<Map<String, Object>> getData(){
        for(int i = 0;i<arr_data.length;i++){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("pic", R.drawable.ic_launcher);
            map.put("text", "·��"+arr_data[i]);
            map.put("id", arr_data[i]);
            dataList.add(map);
        }
        return dataList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            final long id) {
        // TODO �Զ����ɵķ������
        HashMap<String,String> text =  (HashMap<String,String>) listView.getItemAtPosition(position);
        String itemId = text.get("id");
        Toast.makeText(this, "position="+position +"text = "+text.get("id"), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MyRoutesCollectList.this,AllSightDetail.class);
        intent.putExtra("city_name", "����");
        intent.putExtra("rid", itemId);
        startActivity(intent);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO �Զ����ɵķ������
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Map<String, Object> map = new HashMap<String,Object>();
                map.put("pic", R.drawable.ic_launcher);
                map.put("text", "������");
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
        // TODO �Զ����ɵķ������

    }
    protected String getRouteName(int id){
        BmobQuery<Scenicroute> scenicroute_query = new BmobQuery<Scenicroute>();
        scenicroute_query.addWhereEqualTo("id", id);
        final String[] name = {""};
        scenicroute_query.findObjects(MyRoutesCollectList.this, new FindListener<Scenicroute>() {
            @Override
            public void onError(int arg0, String arg1) {
                // TODO �Զ����ɵķ������
                Log.i("fail---70", arg0 + arg1);
            }

            @Override
            public void onSuccess(final List<Scenicroute> scenicroutes) {
                //·����Ϣ

                // TODO �Զ����ɵķ������
                if (scenicroutes.get(0).getOne() != 0) {
                    success[0] = "0";
                    BmobQuery<Scenic> query_scenic = new BmobQuery<Scenic>();
                    query_scenic.addWhereEqualTo("id", scenicroutes.get(0).getOne());
                    query_scenic.findObjects(MyRoutesCollectList.this, new FindListener<Scenic>() {
                        @Override
                        public void onError(int arg0, String arg1) {
                            // TODO �Զ����ɵķ������
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
                    System.out.println("-----------------ִ��--"+arr_data[0]+"-----------------");
                    arr_data[0] = msg.obj.toString();
                    simp_adapter = new SimpleAdapter(MyRoutesCollectList.this, getData(), R.layout.routescollect_item,new String[]{"pic","text"} ,new int[]{R.id.image,R.id.title});
//
                    listView.setAdapter(simp_adapter);
//                    listView.setOnItemClickListener(MyRoutesCollectList.this);
//                    listView.setOnScrollListener(MyRoutesCollectList.this);
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