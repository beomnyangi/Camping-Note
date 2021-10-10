package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.nova.antaeul.campingnotewrite_activity.finish_activity;
import static com.nova.antaeul.campingnotewrite_activity.put_uri_string;
import static com.nova.antaeul.loading_activity.map_go;
import static com.nova.antaeul.loading_activity.more_go;
import static com.nova.antaeul.loading_activity.mynote_go;
import static com.nova.antaeul.loading_activity.timeline_go;

public class timeline_activity extends AppCompatActivity {
	private static final String TAG= "log";
	timeline_adapter adapter;
	RecyclerView recyclerView;

	private Gson gson;
	ArrayList<timeline_data> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

		if(finish_activity == false){
			timeline_go = false;
			map_go = false;
			mynote_go = false;
			more_go = false;
		}

		recyclerView = (RecyclerView) findViewById(R.id.recycle1);
		recyclerView.addItemDecoration(new RecyclerViewDiv_height(30));
		recyclerView.addItemDecoration(new RecyclerViewDiv_width(30));
		setRecyclerView();

		/*캠핑지도 버튼 눌렀을 때*/
		Button bt_searchbymap = (Button) findViewById(R.id.bt_searchbymap) ;
		bt_searchbymap.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_searchbymap");
				Intent intent = new Intent(timeline_activity.this, campingmap_activity.class) ;
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
				timeline_go = true;
				Intent intent = new Intent(timeline_activity.this, campingnotewrite_activity.class) ;
				startActivity(intent) ;
			}
		});
		/*내 캠핑노트 버튼 눌렀을 때*/
		Button bt_wishlist = (Button) findViewById(R.id.bt_mycampingnote) ;
		bt_wishlist.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_wishlist");
				Intent intent = new Intent(timeline_activity.this, campingnote_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});
		/*더보기 버튼 눌렀을 때*/
		Button bt_viewmore = (Button) findViewById(R.id.bt_viewmore) ;
		bt_viewmore.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e(TAG, "bt_viewmore");
				// TODO : start New Activity
				Intent intent = new Intent(timeline_activity.this, viewmore_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});
	}

	//recyclerView 와 adapter 를 연결시켜주는 메소드
	void setRecyclerView(){
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);

		//액티비티에 context 와 item 데이터를 Adapter 에 넘겨준다.
		adapter = new timeline_adapter(this, R.layout.activity_timeline_item);
		recyclerView.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}