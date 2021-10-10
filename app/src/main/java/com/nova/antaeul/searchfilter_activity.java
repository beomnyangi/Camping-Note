package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class searchfilter_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static boolean autocamping = false;
	public static boolean glamping = false;
	public static boolean caravan = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchfilter);
		
		Intent intent = getIntent();
		String test = intent.getStringExtra("toggle");
		Toast.makeText(searchfilter_activity.this, "전달 받은 인텐트 값 = "+test, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		/*뒤로가기버튼 눌렀을 때*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.ib_back);
		ib_back.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "ib_back");
				finish();
			}
		});
		/*초기화버튼 눌렀을 때*/
		Button bt_reset = (Button) findViewById(R.id.bt_reset);
		bt_reset.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_reset");
			}
		});
	}
}