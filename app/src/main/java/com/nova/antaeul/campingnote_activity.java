package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import static com.nova.antaeul.loading_activity.*;
import static com.nova.antaeul.modifynote_activity.modify_item;
import static com.nova.antaeul.campingnotedetail_activity.remove_itme;
import static com.nova.antaeul.campingnotewrite_activity.*;
import static com.nova.antaeul.signup1_activity.id;

public class campingnote_activity extends AppCompatActivity {
	private static final String TAG= "log";

	int pos;
	RecyclerView recyclerView;
	campingnote_adapter adapter;
	Glide glide;

	private Gson gson;
	ArrayList<campingnote_data> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "campingnote_activity_onCreate");
		super.onCreate(savedInstanceState);
		//System.out.println("create");
		setContentView(R.layout.activity_campingnote);

		if(finish_activity == false){
			timeline_go = false;
			map_go = false;
			mynote_go = false;
			more_go = false;
		}
		if(modi_finish == false){
			mynote_modi = false;
			map_modi = false;
		}

		final SharedPreferences sp_data = getSharedPreferences(id, MODE_PRIVATE);
		SharedPreferences.Editor edit = sp_data.edit();
		gson = new GsonBuilder().create();
		final Type listType = new TypeToken<ArrayList<campingnote_data>>() {}.getType();

		Intent intent = getIntent();
		String intent_name = intent.getStringExtra("name");
		String intent_imageexplain = intent.getStringExtra("imageexplain");
		String intent_uri = intent.getStringExtra("uri");
		String intent_review = intent.getStringExtra("review");
		String intent_address = intent.getStringExtra("address");
		double intent_addressname_x = intent.getDoubleExtra("addressname_x",0);
		double intent_addressname_y = intent.getDoubleExtra("addressname_y",0);
		String intent_number = intent.getStringExtra("number");
		String intent_date = intent.getStringExtra("writedate");
		String intent_position = intent.getStringExtra("position");

		recyclerView = (RecyclerView) findViewById(R.id.recycle);
		recyclerView.addItemDecoration(new RecyclerViewDiv_height(30));
		recyclerView.addItemDecoration(new RecyclerViewDiv_width(30));
		setRecyclerView();

		if (remove_itme == true) {
			Log.v(TAG, "remove_itme");
			remove_itme = false;

			Log.e(TAG, "strpos" + intent_position);
			pos = Integer.parseInt(intent_position);
			Log.e(TAG, "intpos" + pos);

			adapter.remove(pos);

			String info = gson.toJson(adapter.info, listType);
			edit.putString("mynote", info);//test이름을 가진 키에 et_text에 입력 된 값을 저장
			edit.commit();
			Toast.makeText(getApplicationContext(), "노트가 삭제 되었습니다.", Toast.LENGTH_SHORT).show();

			if(map_modi){
				modi_finish = false;
				map_modi = false;
				Intent intent_go = new Intent(campingnote_activity.this, campingmap_activity.class) ;
				startActivity(intent_go) ;
				finish();
			}
			if(mynote_modi){
				modi_finish = false;
				mynote_modi = false;
				Intent intent_go = new Intent(campingnote_activity.this, campingnote_activity.class) ;
				startActivity(intent_go) ;
				finish();
			}
		}

		if (modify_item == true) {
			Log.v(TAG, "modify_item");

			modify_item = false;
			pos = Integer.parseInt(intent_position);
			Log.e(TAG, "strpos" + intent_position);

			String from = intent_position;
			int to = Integer.parseInt(from);

			Log.e(TAG, "intpos" + pos);

			System.out.println("mdifiy date : "+intent_date);

			adapter.modify(to, new campingnote_data(intent_name, intent_imageexplain, intent_uri, intent_review, intent_address, intent_addressname_x, intent_addressname_y, intent_number, intent_date));

			String info = gson.toJson(adapter.info, listType);
			edit.putString("mynote", info);//test이름을 가진 키에 et_text에 입력 된 값을 저장
			edit.commit();
			Toast.makeText(getApplicationContext(), "노트가 수정 되었습니다.", Toast.LENGTH_SHORT).show();

			if(map_modi){
				map_modi = false;
				Intent intent_go = new Intent(campingnote_activity.this, campingmap_activity.class) ;
				startActivity(intent_go) ;
				finish();
			}
			if(mynote_modi){
				mynote_modi = false;
				Intent intent_go = new Intent(campingnote_activity.this, campingnote_activity.class) ;
				startActivity(intent_go) ;
				finish();
			}
		}

		// add button에 대한 이벤트 처리.

		if (add_item == true) {
			add_item = false;

			adapter.addInfo(new campingnote_data(intent_name, intent_imageexplain, intent_uri, intent_review, intent_address, intent_addressname_x, intent_addressname_y, intent_number, intent_date));

			String info = gson.toJson(adapter.info, listType);
			edit.putString("mynote", info);//test이름을 가진 키에 et_text에 입력 된 값을 저장
			edit.commit();
			Toast.makeText(getApplicationContext(), "노트가 추가 되었습니다.", Toast.LENGTH_SHORT).show();

			if(finish_activity){
				ArrayList<timeline_data> datas_timeline;
				timeline_adapter.timeline_info.clear();
				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				String get_key;
				//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
				Map<String,?> keys = login_data.getAll();
				for(Map.Entry<String,?> entry : keys.entrySet()){
					get_key = entry.getKey();
					System.out.println("id : "+entry.getKey());
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
					SharedPreferences sp_data1 = getSharedPreferences(get_key, MODE_PRIVATE);
					SharedPreferences.Editor edit1 = sp_data1.edit();
					gson = new GsonBuilder().create();
					Type listType1 = new TypeToken<ArrayList<timeline_data>>() {}.getType();

					String str_data = sp_data1.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
					int size = str_data.length();
					//System.out.println(size);
					if(size > 0) {
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

						datas_timeline = gson.fromJson(str_data, listType1);
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

				if(timeline_go){
					timeline_go = false;
					Intent intent_go = new Intent(campingnote_activity.this, timeline_activity.class) ;
					startActivity(intent_go) ;
					finish();
				}
				if(map_go){
					map_go = false;
					Intent intent_go = new Intent(campingnote_activity.this, campingmap_activity.class) ;
					startActivity(intent_go) ;
					finish();
				}
				if(mynote_go){
					mynote_go = false;
					Intent intent_go = new Intent(campingnote_activity.this, campingnote_activity.class) ;
					startActivity(intent_go) ;
					finish();
				}
				if(more_go){
					more_go = false;
					Intent intent_go = new Intent(campingnote_activity.this, viewmore_activity.class) ;
					startActivity(intent_go) ;
					finish();
				}



				finish_activity = false;
				finish();
			}

		}

		/*타임라인 버튼 눌렀을 때*/
		Button bt_timeline = (Button) findViewById(R.id.bt_timeline) ;
		bt_timeline.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				ArrayList<timeline_data> datas_timeline;
				timeline_adapter.timeline_info.clear();
				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				String get_key;
				//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
				Map<String,?> keys = login_data.getAll();
				for(Map.Entry<String,?> entry : keys.entrySet()){
					get_key = entry.getKey();
					System.out.println("id : "+entry.getKey());
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
					SharedPreferences sp_data = getSharedPreferences(get_key, MODE_PRIVATE);
					SharedPreferences.Editor edit = sp_data.edit();
					gson = new GsonBuilder().create();
					Type listType = new TypeToken<ArrayList<timeline_data>>() {}.getType();

					String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
					int size = str_data.length();
					//System.out.println(size);
					if(size > 0) {
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
				Intent intent = new Intent(campingnote_activity.this, timeline_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});
		/*캠핑지도 버튼 눌렀을 때*/
		Button bt_searchbymap = (Button) findViewById(R.id.bt_searchbymap) ;
		bt_searchbymap.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Intent intent = new Intent(campingnote_activity.this, campingmap_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});
		//글쓰기 이미지버튼 눌렀을 때
		ImageButton ib_writenote = findViewById(R.id.ib_writenote);
		ib_writenote.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				put_uri_string = null;
				mynote_go = true;
				Intent intent = new Intent(campingnote_activity.this, campingnotewrite_activity.class) ;
				startActivity(intent) ;
			}
		});
		/*더보기 버튼 눌렀을 때*/
		Button bt_viewmore = (Button) findViewById(R.id.bt_viewmore) ;
		bt_viewmore.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Intent intent = new Intent(campingnote_activity.this, viewmore_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});

		adapter.notifyDataSetChanged();
	}

	//recyclerView 와 adapter 를 연결시켜주는 메소드
	void setRecyclerView(){
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);

		//액티비티에 context 와 item 데이터를 Adapter 에 넘겨준다.
		adapter = new campingnote_adapter(this, R.layout.activity_campingnote_item);
		recyclerView.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(TAG, "campingnote_activityonResume");
	}

	@Override
	public void onStart(){
		super.onStart();
		//System.out.println("start");
	}
	@Override
	public void onPause(){
		super.onPause();
		//System.out.println("pause");
	}
	@Override
	public void onStop(){
		super.onStop();
		//System.out.println("stop");
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		//System.out.println("destroy");
	}
}