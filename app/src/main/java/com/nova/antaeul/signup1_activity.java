package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class signup1_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static String id;
	String pw;
	String checkpw;
	
	boolean allcheck;
	boolean cb_1_check;
	boolean cb_2_check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup1);
		allcheck = false;
		cb_1_check = false;
		cb_2_check = false;
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
		
		//all약관동의 버튼
		final CheckBox cb_all = (CheckBox) findViewById(R.id.cb_all) ;
		final CheckBox cb_1 = (CheckBox) findViewById(R.id.cb_1) ;
		final CheckBox cb_2 = (CheckBox) findViewById(R.id.cb_2) ;
		cb_all.setOnClickListener(new CheckBox.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cb_all.isChecked()) {
					// TODO : CheckBox is checked.
					cb_all.setChecked(true);
					cb_1.setChecked(true);
					cb_2.setChecked(true);
					allcheck = true;
					cb_1_check = true;
					cb_2_check = true;
				} else {
					// TODO : CheckBox is unchecked.
					cb_all.setChecked(false);
					cb_1.setChecked(false);
					cb_2.setChecked(false);
					allcheck = false;
					cb_1_check = false;
					cb_2_check = false;
				}
			}
		}) ;
		
		//1약관동의 버튼
		cb_1.setOnClickListener(new CheckBox.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cb_1.isChecked()) {
					// TODO : CheckBox is checked.
					cb_1.setChecked(true);
					cb_1_check = true;
				} else {
					// TODO : CheckBox is unchecked.
					cb_1.setChecked(false);
					cb_all.setChecked(false);
					cb_1_check = false;
					allcheck = false;
				}
			}
		}) ;
		
		//2약관동의 버튼
		cb_2.setOnClickListener(new CheckBox.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cb_2.isChecked()) {
					// TODO : CheckBox is checked.
					cb_2.setChecked(true);
					cb_2_check = true;
				} else {
					// TODO : CheckBox is unchecked.
					cb_2.setChecked(false);
					cb_all.setChecked(false);
					cb_2_check = false;
					allcheck = false;
				}
			}
		}) ;
		
		final SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
		
		Button bt_next = (Button) findViewById(R.id.bt_next);
		bt_next.setOnClickListener(new Button.OnClickListener() {
		  @Override
		  public void onClick(View v) {
			  //버튼이 클릭 됐을 때
			  EditText et_id = (EditText) findViewById(R.id.et_email);
			  id = String.valueOf(et_id.getText());
			  
			  EditText et_pw = (EditText) findViewById(R.id.et_pw);
			  pw = String.valueOf(et_pw.getText());
			
			  EditText et_checkpw = (EditText) findViewById(R.id.et_checkpw);
			  checkpw = String.valueOf(et_checkpw.getText());
			  
			  String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
			  if(!Objects.equals(get_id, "no_id")){
				  Toast.makeText(getApplicationContext(), "이미 존재 하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
			  }
			  
			  if(Objects.equals(id, "")){
				  Toast.makeText(getApplicationContext(), "만들 아이디를 입력 하세요.", Toast.LENGTH_SHORT).show();
			  }
			
			  if(Objects.equals(pw, "")){
				  Toast.makeText(getApplicationContext(), "비밀번호를 입력 하세요.", Toast.LENGTH_SHORT).show();
			  }
			
			  if(!Objects.equals(pw, "") && Objects.equals(checkpw, "")){
				  Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력 하세요.", Toast.LENGTH_SHORT).show();
			  }
			  
			  if(!Objects.equals(pw, "") && !Objects.equals(checkpw, "") && !Objects.equals(pw, checkpw)){
			  	Toast.makeText(getApplicationContext(), "비밀번호가 서로 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
			  }
			
			  if(Objects.equals(get_id, "no_id") && !Objects.equals(id, "") && !Objects.equals(pw, "") && !Objects.equals(checkpw, "")){
				  if(!cb_1_check || !cb_2_check){
					  Toast.makeText(getApplicationContext(), "필수약관에 동의 하세요.", Toast.LENGTH_SHORT).show();
				  }
				  
			  }
			  
			  
			  if(cb_1_check && cb_2_check && Objects.equals(get_id, "no_id") && !Objects.equals(id, "") && !Objects.equals(pw, "") && !Objects.equals(checkpw, "")){
				  SharedPreferences.Editor edit = login_data.edit();
				  edit.putString(id, pw);//test이름을 가진 키에 et_text에 입력 된 값을 저장
				  edit.commit();
				  //Intent intent = new Intent(signup1_activity.this, loginpage_activity.class);
				  Intent intent = new Intent(signup1_activity.this, signup2_activity.class);
				  startActivity(intent);
				  finish();
				  
				  System.out.println("id = "+id);
				  System.out.println("pw = "+pw);
			  }
			  
		  }
		});
		
	}
}