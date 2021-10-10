package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;


import static com.nova.antaeul.campingnotewrite_activity.put_uri_string;
import static com.nova.antaeul.signup1_activity.id;

public class loginpage_activity extends AppCompatActivity {
	private static final String TAG= "log";
	
	Hashtable<String, String> ht = new Hashtable<String, String>();
	//String id;
	String pw;
	
	campingnote_adapter adapter;
	
	private Gson gson;
	ArrayList<campingnote_data> datas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpage);

		//로그인 버튼 눌렀을 때
		Button bt_login = (Button) findViewById(R.id.bt_login);
		bt_login.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_login");
				put_uri_string = null;
				EditText et_id = (EditText) findViewById(R.id.et_email) ;
				EditText et_pw = (EditText) findViewById(R.id.et_pw) ;
				id = et_id.getText().toString();
				pw = et_pw.getText().toString();
				//id = "kim_sung_bum@naver.com";
				//pw = "gozldgkwlak724";

				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);

				/*//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
				Map<String,?> keys = login_data.getAll();
				for(Map.Entry<String,?> entry : keys.entrySet()){
					System.out.println("id : "+entry.getKey());
					System.out.println("pw : "+entry.getValue());
					System.out.println("//////////////////////////////////");
				}*/

				String get_pw = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환

				if(!Objects.equals(id, "") && Objects.equals(get_pw, "no_id")){
					Toast.makeText(getApplicationContext(), "존재 하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show();
				}

				if(!Objects.equals(pw, "") && !Objects.equals(get_pw, "no_id") && !Objects.equals(get_pw, pw)){
					Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
				}

				if(Objects.equals(id, "")){
					Toast.makeText(getApplicationContext(), "아이디를 입력 하세요.", Toast.LENGTH_SHORT).show();
				}

				if(Objects.equals(pw, "")){
					Toast.makeText(getApplicationContext(), "비밀번호를 입력 하세요.", Toast.LENGTH_SHORT).show();
				}

				if(!Objects.equals(id, "no_id") && Objects.equals(get_pw, pw)){
					SharedPreferences sp_data = getSharedPreferences(id, MODE_PRIVATE);
					SharedPreferences.Editor edit = sp_data.edit();
					gson = new GsonBuilder().create();
					Type listType = new TypeToken<ArrayList<campingnote_data>>() {}.getType();

					String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
					int size = str_data.length();
					//System.out.println(size);
					if(size > 0) {
						String getName = null;
						String getAddress = null;
						double getX;
						double getY;
						String getImageexplain = null;
						String getUri = null;
						String getNumber = null;
						String getReview = null;
						String getWritedate = null;

						datas = gson.fromJson(str_data, listType);
						for (campingnote_data data : datas) {
							getName = data.getName();
							getAddress = data.getAddress();
							getX = data.getX();
							getY = data.getY();
							getImageexplain = data.getImageexplain();
							getUri = data.getImageuri();
							getNumber = data.getNumber();
							getReview = data.getReview();
							getWritedate = data.getWritedate();

							adapter.info.add(new campingnote_data(getName, getImageexplain, getUri, getReview, getAddress, getX, getY, getNumber, getWritedate));
						}
					}

					// TODO : start New Activity
					ArrayList<timeline_data> datas_timeline;
					timeline_adapter.timeline_info.clear();
					SharedPreferences login_data_timeline = getSharedPreferences("login_data",MODE_PRIVATE);
					String get_key;
					//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
					Map<String,?> keys = login_data_timeline.getAll();
					for(Map.Entry<String,?> entry : keys.entrySet()){
						get_key = entry.getKey();
						System.out.println("id : "+entry.getKey());
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
						SharedPreferences sp_data_timeline = getSharedPreferences(get_key, MODE_PRIVATE);
						gson = new GsonBuilder().create();
						Type listType_timeline = new TypeToken<ArrayList<timeline_data>>() {}.getType();

						String str_data_timeline = sp_data_timeline.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
						int size_timeline = str_data_timeline.length();
						//System.out.println(size);
						if(size_timeline > 0) {
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

							datas_timeline = gson.fromJson(str_data_timeline, listType_timeline);
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

					Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(loginpage_activity.this, timeline_activity.class);
					startActivity(intent);
					finish();
				}

			}
		});

		//회원가입 버튼 눌렀을 때
		Button bt_signup = (Button) findViewById(R.id.bt_signup);
		bt_signup.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
			//버튼이 클릭 됐을 때
			/*버튼 클릭 이벤트 발생 됐을 때*/
			//클릭 됐을 때 할 내용 작성
			/*SharedPreferences rm = getSharedPreferences("login_data",MODE_PRIVATE);
			SharedPreferences.Editor edit = rm.edit();
			edit.clear();//test라는 이름을 가진 키의 값을 지움
			edit.commit();*/

			//Intent intent = new Intent(loginpage_activity.this, signup1_activity.class);
			Intent intent = new Intent(loginpage_activity.this, signup_activity.class);
			startActivity(intent);
			}
		});

		//비밀번호 찾기 버튼 눌렀을 때
		TextView tv_findpw = (TextView) findViewById(R.id.tv_findpw);
		tv_findpw.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(loginpage_activity.this, findpassword_activity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();

	}
}