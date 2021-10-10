package com.nova.antaeul;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import static com.nova.antaeul.searchfilter_activity.*;


public class main_activity extends Activity {
    private static final String TAG= "log";
    
    private Gson gson;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        Log.e(TAG, "onStart");
        super.onStart();
    }
    
    @Override
    protected void onResume() {
        Log.e(TAG, "onResume");
        super.onResume();
        /*검색*/
        SearchView searchView = (SearchView) findViewById(R.id.sv_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /*검색 버튼 눌렀을 때*/
            @Override
            public boolean onQueryTextSubmit(String query) {
                /*Toast.makeText(main_activity.this, "[검색버튼클릭] 검색어 = "+query, Toast.LENGTH_LONG).show();*/
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", query);
                startActivity(intent) ;
                return true;
            }
            /*검색중인 검색어 때*/
            @Override
            public boolean onQueryTextChange(String newText) {
                /*Toast.makeText(main_activity.this, "입력하고있는 단어 = "+newText, Toast.LENGTH_LONG).show();*/
                return true;
            }
        });
        
        /*오토캠핑 눌렀을 때*/
        ImageButton ib_autocamping = (ImageButton) findViewById(R.id.ib_autocamping) ;
        ib_autocamping.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "ib_autocamping");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "ib_autocamping");
                startActivity(intent) ;
                autocamping = true;
                glamping = false;
                caravan = false;
            }
        });
        TextView tv_autocamping = (TextView) findViewById(R.id.tv_autocamping) ;
        tv_autocamping.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "tv_autocamping");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "tv_autocamping");
                startActivity(intent) ;
                autocamping = true;
                glamping = false;
                caravan = false;
            }
        });
        /*글램핑 눌렀을 때*/
        ImageButton ib_glamping = (ImageButton) findViewById(R.id.ib_glamping) ;
        ib_glamping.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "ib_glamping");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "ib_glamping");
                startActivity(intent) ;
                autocamping = false;
                glamping = true;
                caravan = false;
            }
        });
        TextView tv_glamping = (TextView) findViewById(R.id.tv_glamping) ;
        tv_glamping.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "tv_glamping");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "tv_glamping");
                startActivity(intent) ;
                autocamping = false;
                glamping = true;
                caravan = false;
            }
        });
        /*카라반 눌렀을 때*/
        ImageButton ib_caravan = (ImageButton) findViewById(R.id.ib_caravan) ;
        ib_caravan.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "ib_caravan");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "ib_caravan");
                startActivity(intent) ;
                autocamping = false;
                glamping = false;
                caravan = true;
            }
        });
        TextView tv_caravan = (TextView) findViewById(R.id.tv_caravan) ;
        tv_caravan.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "tv_caravan");
                Intent intent = new Intent(main_activity.this, searchresults_activity.class) ;
                intent.putExtra("toggle", "tv_caravan");
                startActivity(intent) ;
                autocamping = false;
                glamping = false;
                caravan = true;
            }
        });
        /*최근 검색 한 캠핑장1 눌렀을 때*/
        ConstraintLayout lo_campzone1 = (ConstraintLayout) findViewById(R.id.lo_campingnote) ;
        lo_campzone1.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "lo_campzone1");
                Intent intent = new Intent(main_activity.this, campgroundinformation_activity.class) ;
                intent.putExtra("campzone", "A캠핑장");
                startActivity(intent) ;
            }
        });
        /*최근 검색 한 캠핑장2 눌렀을 때*/
        ConstraintLayout lo_campzone2 = (ConstraintLayout) findViewById(R.id.lo_campzone2) ;
        lo_campzone2.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "lo_campzone2");
                Intent intent = new Intent(main_activity.this, campgroundinformation_activity.class) ;
                intent.putExtra("campzone", "B캠핑장");
                startActivity(intent) ;
            }
        });
        /*최근 검색 한 캠핑장3 눌렀을 때*/
        ConstraintLayout lo_campzone3 = (ConstraintLayout) findViewById(R.id.lo_campzone3) ;
        lo_campzone3.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "lo_campzone3");
                Intent intent = new Intent(main_activity.this, campgroundinformation_activity.class) ;
                intent.putExtra("campzone", "C캠핑장");
                startActivity(intent) ;
            }
        });
        /*최근 검색 한 캠핑장4 눌렀을 때*/
        ConstraintLayout lo_campzone4 = (ConstraintLayout) findViewById(R.id.lo_campzone4) ;
        lo_campzone4.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "lo_campzone4");
                Intent intent = new Intent(main_activity.this, campgroundinformation_activity.class) ;
                intent.putExtra("campzone", "D캠핑장");
                startActivity(intent) ;
            }
        });
        /**/
        /*하단 버튼 모음*/
        /**/
        /*캠핑지도 버튼 눌렀을 때*/
        Button bt_searchbymap = (Button) findViewById(R.id.bt_searchbymap) ;
        bt_searchbymap.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "bt_searchbymap");
                Intent intent = new Intent(main_activity.this, campingmap_activity.class) ;
                startActivity(intent) ;
                finish();
            }
        });
        /*캠핑노트 버튼 눌렀을 때*/
        Button bt_wishlist = (Button) findViewById(R.id.bt_campingnote) ;
        bt_wishlist.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "bt_campingnote");
                
                //Intent intent = new Intent(main_activity.this, campingnote_listview_activity.class) ;
                Intent intent = new Intent(main_activity.this, campingnote_activity.class) ;
                startActivity(intent) ;
            }
        });
        /*타임라인 버튼 눌렀을 때*/
        Button bt_timeline = (Button) findViewById(R.id.bt_timeline) ;
        bt_timeline.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "bt_mypage");
                Intent intent = new Intent(main_activity.this, timeline_activity.class) ;
                startActivity(intent) ;
    
                ArrayList<timeline_data> datas_timeline;
                timeline_adapter.timeline_info.clear();
                SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
                String get_key;
                //SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
                Map<String,?> keys = login_data.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()) {
                    get_key = entry.getKey();
                    System.out.println("id : " + entry.getKey());
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                    SharedPreferences sp_data = getSharedPreferences(get_key, MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp_data.edit();
                    gson = new GsonBuilder().create();
                    Type listType = new TypeToken<ArrayList<timeline_data>>() {
                    }.getType();
    
                    String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
                    int size = str_data.length();
                    //System.out.println(size);
                    if (size > 0) {
                        String getName = null;
                        String getAddress = null;
                        double getX;
                        double getY;
                        String getImageexplain = null;
                        String getImageuri = null;
                        String getNumber = null;
                        String getReview = null;
                        String getWritedate = null;
                        String getWritername = null;

                        datas_timeline = gson.fromJson(str_data, listType);
                        System.out.println(datas_timeline.size());
                        for (timeline_data data : datas_timeline) {
                            getName = data.getName();
                            getAddress = data.getAddress();
                            getX = data.getX();
                            getY = data.getY();
                            getImageexplain = data.getImageexplain();
                            getImageuri = data.getImageuri();
                            getNumber = data.getNumber();
                            getReview = data.getReview();
                            getWritedate = data.getWritedate();
                            getWritername = get_key;

                            timeline_adapter.timeline_info.add(new timeline_data(getName, getImageexplain, getImageuri, getReview, getAddress, getX, getY, getNumber, getWritedate, getWritername));
                        }
                    }
                }
            }
        });
        /*더보기 버튼 눌렀을 때*/
        Button bt_viewmore = (Button) findViewById(R.id.bt_viewmore) ;
        bt_viewmore.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : start New Activity
                Log.e(TAG, "bt_viewmore");
                Intent intent = new Intent(main_activity.this, viewmore_activity.class) ;
                startActivity(intent) ;
            }
        });
    }
    
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        super.onPause();
    }
    
    @Override
    protected void onStop() {
        Log.e(TAG, "onStop");
        super.onStop();
    }
    
    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart");
        super.onRestart();
    }
    
    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
    
}
