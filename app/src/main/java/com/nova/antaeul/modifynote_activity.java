package com.nova.antaeul;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


import static com.nova.antaeul.loading_activity.modi_finish;
import static com.nova.antaeul.searchaddress_adapter.search_ok;

public class modifynote_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static boolean modify_item = false;
	String pos;

	Glide glide;

	public static boolean modify_on;

	public static boolean modify_addsearch;

	public static boolean modify_check;

	public static String modi_put_name;
	public static String modi_put_address;
	public static String modi_put_imageexplain;
	public static String modi_put_uri_string;
	public static String modi_put_review;
	public static String modi_put_number;
	public static double modi_put_x;
	public static double modi_put_y;
	public static String modi_put_position;

	private static final int GET_GALLERY_IMAGE = 200;

	String uri_string;

	ImageView iv_addimage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campingnote_write);
		Log.e(TAG, "writenote_activity_onCreate");

		modify_addsearch = true;

		Button bt_addimage = findViewById(R.id.bt_addimage);
		bt_addimage.setText("사진 수정");

		Intent intent = getIntent();
		String intent_name = intent.getStringExtra("name");
		String intent_imageexplain = intent.getStringExtra("imageexplain");
		final String intent_uri = intent.getStringExtra("uri");
		String intent_review = intent.getStringExtra("review");
		String intent_address = intent.getStringExtra("address");
		final double intent_addressname_x = intent.getDoubleExtra("addressname_x",0);
		final double intent_addressname_y = intent.getDoubleExtra("addressname_y",0);
		String intent_number = intent.getStringExtra("number");
		String intent_date = intent.getStringExtra("writedate");
		String intent_position = intent.getStringExtra("position");

		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_imageexplain = (EditText) findViewById(R.id.et_imageexplain);
		EditText et_review = (EditText) findViewById(R.id.et_review);
		EditText et_address = (EditText) findViewById(R.id.et_address);
		EditText et_number = (EditText) findViewById(R.id.et_number);

		if(modify_on) {
			System.out.println("modify_on");
			modi_put_name = intent_name;
			modi_put_imageexplain = intent_imageexplain;
			modi_put_uri_string = intent_uri;
			modi_put_review = intent_review;
			modi_put_address = intent_address;
			modi_put_number = intent_number;
			modi_put_x = intent_addressname_x;
			modi_put_y = intent_addressname_y;
			modi_put_position = intent_position;

			modify_on = false;
		}


		if(search_ok == true){
			System.out.println("search_ok");
			modi_put_address = intent_address;
			modi_put_x = intent_addressname_x;
			modi_put_y = intent_addressname_y;
			search_ok = false;

		}
		if (modi_put_uri_string == null) {
			System.out.println("modi_put_uri_string");
			et_imageexplain.setVisibility(View.GONE);
		}
		if (modi_put_uri_string != null) {
			System.out.println("modi_put_uri_string111");
			ImageView iv_addimage = findViewById(R.id.iv_addimage);
			glide.with(this).load(modi_put_uri_string).into(iv_addimage);
			TextView tv_imagetext = findViewById(R.id.tv_imagetext);
			tv_imagetext.setVisibility(View.GONE);
		}

		et_name.setText(modi_put_name);
		et_imageexplain.setText(modi_put_imageexplain);
		et_review.setText(modi_put_review);
		et_address.setText(modi_put_address);
		et_number.setText(modi_put_number);

		//검색 버튼 눌렀을 때
		Button bt_search =  findViewById(R.id.bt_search);
		bt_search.setOnClickListener(new EditText.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때

				EditText et_name = (EditText)findViewById(R.id.et_name);
				EditText et_imageexplain = (EditText)findViewById(R.id.et_imageexplain);
				EditText et_review = (EditText)findViewById(R.id.et_review);
				EditText et_number = (EditText)findViewById(R.id.et_number);
				EditText et_address = (EditText)findViewById(R.id.et_address);

				String name = String.valueOf(et_name.getText());
				String imageexplain = String.valueOf(et_imageexplain.getText());
				String review = String.valueOf(et_review.getText());
				String number = String.valueOf(et_number.getText());
				String address = String.valueOf(et_address.getText());

				modi_put_name = name;
				modi_put_imageexplain = imageexplain;
				modi_put_review = review;
				modi_put_address = address;
				modi_put_number = number;


				// 아이템 정보 전달.
				Intent intent = new Intent(modifynote_activity.this, searchaddress_activity.class) ;
				startActivity(intent) ;
			}
		});

		/*뒤로가기버튼 눌렀을 때*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.ib_back);
		ib_back.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "ib_back");
				finish();
				Button bt_addimage = findViewById(R.id.bt_addimage);
				bt_addimage.setText("사진 추가");

			}
		});

		Button bt_addimage1 = (Button) findViewById(R.id.bt_addimage);
		bt_addimage1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, GET_GALLERY_IMAGE);
			}
		});

		/*확인버튼 눌렀을 때*/
		Button bt_ok = (Button) findViewById(R.id.bt_ok);
		bt_ok.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_ok");
				modify_item = true;

				modi_finish = true;

				//현재날짜(시간) 값 가져오기
				long now = System.currentTimeMillis();
				Date date = new Date(now);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String getTime = sdf.format(date);

				EditText et_name = (EditText)findViewById(R.id.et_name);
				EditText et_imageexplain = (EditText)findViewById(R.id.et_imageexplain);
				EditText et_review = (EditText)findViewById(R.id.et_review);
				EditText et_address = (EditText)findViewById(R.id.et_address);
				EditText et_number = (EditText)findViewById(R.id.et_number);

				String name = String.valueOf(et_name.getText());
				String imageexplain = String.valueOf(et_imageexplain.getText());
				String review = String.valueOf(et_review.getText());
				String address = String.valueOf(et_address.getText());
				String number = String.valueOf(et_number.getText());
				String writedate = getTime;

				// 아이템 정보 전달.
				Intent intent = new Intent(modifynote_activity.this, campingnote_activity.class) ;
				intent.putExtra("name", name);
				intent.putExtra("imageexplain", imageexplain);
				intent.putExtra("uri", modi_put_uri_string);
				intent.putExtra("review", review);
				intent.putExtra("address", address);
				intent.putExtra("addressname_x", modi_put_x);
				intent.putExtra("addressname_y", modi_put_y);
				intent.putExtra("number", number);
				intent.putExtra("writedate", writedate);
				intent.putExtra("position", modi_put_position);

				System.out.println("testtmodi_put_position//////////////"+modi_put_position);

				startActivity(intent) ;
				finish();
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

			Uri selectedImageUri = data.getData();
			uri_string = String.valueOf(selectedImageUri);
			iv_addimage = findViewById(R.id.iv_addimage);
			glide.with(this).load(selectedImageUri).into(iv_addimage);
			System.out.println("selectedImageUri : "+selectedImageUri);

			if(!Objects.equals(uri_string, "")){
				modi_put_uri_string = uri_string;
				EditText et_imageexplain = (EditText) findViewById(R.id.et_imageexplain);
				et_imageexplain.setVisibility(View.VISIBLE);
				TextView tv_imagetext = findViewById(R.id.tv_imagetext);
				tv_imagetext.setVisibility(View.GONE);
			}
		}

	}

}