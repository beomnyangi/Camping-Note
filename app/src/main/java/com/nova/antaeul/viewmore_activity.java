package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import static com.nova.antaeul.campingnotewrite_activity.finish_activity;
import static com.nova.antaeul.campingnotewrite_activity.put_uri_string;
import static com.nova.antaeul.loading_activity.map_go;
import static com.nova.antaeul.loading_activity.more_go;
import static com.nova.antaeul.loading_activity.mynote_go;
import static com.nova.antaeul.loading_activity.timeline_go;
import static com.nova.antaeul.signup1_activity.id;

public class viewmore_activity extends AppCompatActivity {
	private static final String TAG= "log";
	campingnote_adapter adapter;
	
	private Gson gson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewmore);

		if(finish_activity == false){
			timeline_go = false;
			map_go = false;
			mynote_go = false;
			more_go = false;
		}

		//내 정보 버튼 눌렀을 때
		TextView tv_id =  findViewById(R.id.tv_id);
		tv_id.setText("Login Id : "+id);
		/*bt_myinfo.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때

				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
				Toast.makeText(getApplicationContext(), "나의 ID : "+id, Toast.LENGTH_SHORT).show();
			}
		});*/

		Button bt_terms = findViewById(R.id.bt_terms);
		bt_terms.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				Intent intent = new Intent(viewmore_activity.this, terms_activity.class);
				startActivity(intent);
			}
		});

		//회원 탈퇴 버튼 눌렀을 때
		Button bt_signout = (Button) findViewById(R.id.bt_signout);
		bt_signout.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				adapter.info.clear();

				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				SharedPreferences.Editor edit = login_data.edit();
				edit.remove(id);//test라는 이름을 가진 키의 값을 지움
				edit.commit();

				SharedPreferences sp_data = getSharedPreferences(id, MODE_PRIVATE);
				SharedPreferences.Editor edit_data = sp_data.edit();
				edit_data.remove("mynote");//test라는 이름을 가진 키의 값을 지움
				edit_data.commit();

				Intent intent = new Intent(viewmore_activity.this, loginpage_activity.class) ;
				startActivity(intent) ;
				finish();
				Toast.makeText(getApplicationContext(), "회원탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
			}
		});

		//로그아웃 버튼 눌렀을 때
		Button bt_logout = (Button) findViewById(R.id.bt_logout);
		bt_logout.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				adapter.info.clear();
				Intent intent = new Intent(viewmore_activity.this, loginpage_activity.class) ;
				startActivity(intent) ;
				finish();
				Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
			}
		});

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
				Intent intent = new Intent(viewmore_activity.this, timeline_activity.class) ;
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
				Intent intent = new Intent(viewmore_activity.this, campingmap_activity.class) ;
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
				more_go = true;
				put_uri_string = null;
				Intent intent = new Intent(viewmore_activity.this, campingnotewrite_activity.class) ;
				startActivity(intent) ;
			}
		});
		//내 캠핑노트 버튼 눌렀을 때*/
		Button bt_mycampingnote = (Button) findViewById(R.id.bt_mycampingnote) ;
		bt_mycampingnote.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Intent intent = new Intent(viewmore_activity.this, campingnote_activity.class) ;
				startActivity(intent) ;
				finish();
			}
		});
		
	}

	@Override
	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();

	}
}