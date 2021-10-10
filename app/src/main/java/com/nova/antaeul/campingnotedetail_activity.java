package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import static com.nova.antaeul.loading_activity.modi_finish;
import static com.nova.antaeul.modifynote_activity.modify_on;

public class campingnotedetail_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static boolean remove_itme = false;
	String pos;
	Glide glide;
	String uri_string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campingnote_detail);
		Log.e(TAG, "viewnote_activity_onCreate");

		Intent intent = getIntent();
		String intent_name = intent.getStringExtra("name");
		String intent_imageexplain = intent.getStringExtra("imageexplain");
		String intent_uri = intent.getStringExtra("uri");
		String intent_review = intent.getStringExtra("review");
		String intent_address = intent.getStringExtra("address");
		final double intent_addressname_x = intent.getDoubleExtra("addressname_x",0);
		final double intent_addressname_y = intent.getDoubleExtra("addressname_y",0);
		String intent_number = intent.getStringExtra("number");
		String intent_date = intent.getStringExtra("writedate");
		final String intent_position = intent.getStringExtra("position");

		uri_string = intent_uri;

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

		/*수정 버튼 눌렀을 때*/
		Button bt_modify = (Button) findViewById(R.id.bt_modify);
		bt_modify.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_modify");
				modify_on = true;

				TextView tv_name = (TextView)findViewById(R.id.tv_name);
				TextView tv_imageexplain = (TextView)findViewById(R.id.tv_imageexplain);
				TextView tv_review = (TextView)findViewById(R.id.tv_review);
				TextView tv_address = (TextView)findViewById(R.id.tv_address);
				TextView tv_number = (TextView)findViewById(R.id.tv_number);

				String name = String.valueOf(tv_name.getText());
				String imageexplain = String.valueOf(tv_imageexplain.getText());
				String review = String.valueOf(tv_review.getText());
				String address = String.valueOf(tv_address.getText());
				String number = String.valueOf(tv_number.getText());

				// 아이템 정보 전달.
				Intent intent = new Intent(campingnotedetail_activity.this, modifynote_activity.class) ;
				intent.putExtra("name", name);
				intent.putExtra("imageexplain", imageexplain);
				intent.putExtra("uri", uri_string);
				intent.putExtra("review", review);
				intent.putExtra("address", address);
				intent.putExtra("addressname_x", intent_addressname_x);
				intent.putExtra("addressname_y", intent_addressname_y);
				intent.putExtra("number", number);
				intent.putExtra("position", pos);

				System.out.println("name : "+name);
				System.out.println("imageexplain : "+imageexplain);

				startActivity(intent) ;

			}
		});

		/*삭제 버튼 눌렀을 때*/
		Button bt_delete = (Button) findViewById(R.id.bt_delete);
		bt_delete.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_delete");

				modi_finish = true;

				Intent intent = new Intent(campingnotedetail_activity.this, campingnote_activity.class) ;
				intent.putExtra("position", intent_position);
				Log.e(TAG, "position"+intent_position);
				remove_itme = true;
				startActivity(intent) ;
				finish();
			}
		});

	}

	@Override
	protected void onResume(){
		Log.e(TAG, "onResume");
		super.onResume();

	}
}