package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.nova.antaeul.modifynote_activity.modify_addsearch;
import static com.nova.antaeul.searchaddress_adapter.search_ok;
import static com.nova.antaeul.signup1_activity.id;

public class campingnotewrite_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static boolean add_item = false;
	public static boolean finish_activity = false;



	String pos;

	private static final int GET_GALLERY_IMAGE = 200;

	String uri_string;

	ImageView iv_addimage;

	public static String put_name;
	public static String put_imageexplain;
	public static String put_uri_string;
	public static String put_review;
	public static String put_number;
	public static boolean addimage = false;

	Glide glide;

	campingnote_adapter adapter;

	private Gson gson;
	ArrayList<campingnote_data> datas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campingnote_write);
		Log.e(TAG, "writenote_activity_onCreate");

		modify_addsearch = false;

		final SharedPreferences sp_data = getSharedPreferences(id, MODE_PRIVATE);
		final SharedPreferences.Editor edit = sp_data.edit();
		gson = new GsonBuilder().create();
		final Type listType = new TypeToken<ArrayList<campingnote_data>>() {}.getType();
		
		Intent intent = getIntent();
		String intent_name = intent.getStringExtra("name");
		String intent_imageexplain = intent.getStringExtra("imageexplain");
		String intent_uri = intent.getStringExtra("uri");
		String intent_review = intent.getStringExtra("review");
		String intent_address = intent.getStringExtra("address");
		String intent_number = intent.getStringExtra("number");
		String intent_date = intent.getStringExtra("writedate");
		String intent_position = intent.getStringExtra("position");

		final String intent_addressname = intent.getStringExtra("addressname");
		final String intent_road_addressname = intent.getStringExtra("road_addressname");
		final double intent_addressname_x = intent.getDoubleExtra("addressname_x", 0);
		final double intent_addressname_y = intent.getDoubleExtra("addressname_y", 0);


		pos = intent_position;

		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_imageexplain = (EditText) findViewById(R.id.et_imageexplain);
		EditText et_review = (EditText) findViewById(R.id.et_review);
		final EditText et_address = (EditText) findViewById(R.id.et_address);
		EditText et_number = (EditText) findViewById(R.id.et_number);

		if(search_ok == true){
			et_name.setText(put_name);
			et_imageexplain.setText(put_imageexplain);
			et_review.setText(put_review);
			et_address.setText(intent_address);
			et_number.setText(put_number);
			search_ok = false;
		}
		if (put_uri_string == null) {
			et_imageexplain.setVisibility(View.GONE);
		}
		if (put_uri_string != null) {
			ImageView iv_addimage = findViewById(R.id.iv_addimage);
			glide.with(this).load(put_uri_string).into(iv_addimage);
			TextView tv_imagetext = findViewById(R.id.tv_imagetext);
			tv_imagetext.setVisibility(View.GONE);
		}

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

				String name = String.valueOf(et_name.getText());
				String imageexplain = String.valueOf(et_imageexplain.getText());
				String review = String.valueOf(et_review.getText());
				String number = String.valueOf(et_number.getText());

				String put_add = String.valueOf(et_address.getText());

				put_name = name;
				put_imageexplain = imageexplain;
				put_review = review;
				put_number = number;

				// 아이템 정보 전달.
				Intent intent = new Intent(campingnotewrite_activity.this, searchaddress_activity.class) ;
				intent.putExtra("address", put_add);
				startActivity(intent);
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
			}
		});

		Button bt_addimage = (Button) findViewById(R.id.bt_addimage);
		bt_addimage.setOnClickListener(new Button.OnClickListener() {
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
				add_item = true;
				finish_activity = true;

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
				Intent intent = new Intent(campingnotewrite_activity.this, campingnote_activity.class) ;
				intent.putExtra("name", name);
				intent.putExtra("imageexplain", imageexplain);
				intent.putExtra("uri", put_uri_string);
				intent.putExtra("review", review);
				intent.putExtra("address", address);
				intent.putExtra("addressname_x", intent_addressname_x);
				intent.putExtra("addressname_y", intent_addressname_y);
				intent.putExtra("number", number);
				intent.putExtra("writedate", writedate);
				intent.putExtra("position", pos);

				System.out.println("name : "+name);
				System.out.println("imageexplain : "+imageexplain);

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
				put_uri_string = uri_string;
				EditText et_imageexplain = (EditText) findViewById(R.id.et_imageexplain);
				et_imageexplain.setVisibility(View.VISIBLE);
				TextView tv_imagetext = findViewById(R.id.tv_imagetext);
				tv_imagetext.setVisibility(View.GONE);
			}
		}

	}

}