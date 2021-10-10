package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class campgroundinformation_activity extends AppCompatActivity {
	private static final String TAG= "log";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campgroundinformation);
		
		Intent intent = getIntent();
		String campzone = intent.getStringExtra("campzone");
		Toast.makeText(campgroundinformation_activity.this, "전달 받은 인텐트 값 = "+campzone, Toast.LENGTH_LONG).show();
		
		TextView tv_campingsitename = (TextView) findViewById(R.id.tv_campingsitename);
		tv_campingsitename.setText(campzone);
	}
	
	@Override
	protected void onResume(){
		Log.e(TAG, "onResume");
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
		/*찜하기버튼 눌렀을 때*/
		ImageButton ib_wish = (ImageButton) findViewById(R.id.ib_wish);
		ib_wish.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "ib_wish");
			}
		});
	}
}