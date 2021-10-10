package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class timelinedetail_activity extends AppCompatActivity {
	private static final String TAG= "log";
	String pos;
	Glide glide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline_detail);
		Log.e(TAG, "viewnote_activity_onCreate");

		Intent intent = getIntent();
		String intent_name = intent.getStringExtra("name");
		String intent_imageexplain = intent.getStringExtra("imageexplain");
		String intent_uri = intent.getStringExtra("uri");
		String intent_review = intent.getStringExtra("review");
		String intent_address = intent.getStringExtra("address");
		String intent_number = intent.getStringExtra("number");
		String intent_date = intent.getStringExtra("writedate");
		String intent_position = intent.getStringExtra("position");
		String intent_writername = intent.getStringExtra("writername");

		pos = intent_position;
		Log.e(TAG, "position"+pos);

		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		tv_name.setText(intent_name);
		TextView tv_review = (TextView) findViewById(R.id.tv_review);
		tv_review.setText(intent_review);
		TextView tv_address = (TextView) findViewById(R.id.tv_address);
		tv_address.setText(intent_address);
		TextView tv_number = (TextView) findViewById(R.id.tv_number);
		tv_number.setText(intent_number);
		TextView tv_writername = (TextView) findViewById(R.id.tv_writername);
		tv_writername.setText(intent_writername);

		if(intent_uri != null){
			ImageView iv_image = findViewById(R.id.iv_image);
			glide.with(this).load(intent_uri).into(iv_image);

			TextView tv_imageexplain = (TextView) findViewById(R.id.tv_imageexplain);
			tv_imageexplain.setText(intent_imageexplain);
		}
		if(intent_uri == null){
			ImageView iv_image = findViewById(R.id.iv_image);
			iv_image.setVisibility(View.GONE);

			TextView tv_imageexplain = (TextView) findViewById(R.id.tv_imageexplain);
			tv_imageexplain.setVisibility(View.GONE);

			TextView imageexplain = (TextView) findViewById(R.id.imageexplain);
			imageexplain.setVisibility(View.GONE);
		}
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

	}
}