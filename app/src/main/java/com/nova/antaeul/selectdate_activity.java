package com.nova.antaeul;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class selectdate_activity extends AppCompatActivity {
	private static final String TAG= "log";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectdate);
	}
	
	@Override
	protected void onResume() {
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