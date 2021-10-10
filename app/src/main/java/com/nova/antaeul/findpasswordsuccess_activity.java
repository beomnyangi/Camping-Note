package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class findpasswordsuccess_activity extends AppCompatActivity {

	String id;
	Button bt_login;
	TextView tv_result;

	SharedPreferences login_data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpasswordsuccess);

		Intent intent = getIntent();
		String intent_id = intent.getStringExtra("id");

		login_data = getSharedPreferences("login_data",MODE_PRIVATE);

		bt_login = findViewById(R.id.bt_login);
		tv_result = findViewById(R.id.tv_result);

		id = intent_id;
		String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
		tv_result.setText(get_id);

		/*뒤로가기버튼 눌렀을 때*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.ib_back);
		ib_back.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				finish();
			}
		});

		//로그인 하기 버튼 눌렀을 때
		bt_login.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				Intent intent = new Intent(findpasswordsuccess_activity.this, loginpage_activity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}