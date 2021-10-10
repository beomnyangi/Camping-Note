package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class signup_activity extends AppCompatActivity {
	private static final String TAG= "log";
	public static String id;

	Button bt_sendmail;
	Button bt_check;
	Button bt_signup_ok;

	CheckBox cb_all;
	CheckBox cb_1;
	CheckBox cb_2;

	String pw;
	String checkpw;

	boolean allcheck;
	boolean cb_1_check;
	boolean cb_2_check;
	boolean signup_ok = false;

	String get_code;
	String get_code_value;
	String get_email_check;

	TextView tv_count;
	TextView tv_cb1;

	EditText et_email;
	EditText et_email_value;
	EditText et_id;
	EditText et_pw;
	EditText et_checkpw;

	Resources res;

	SharedPreferences login_data;

	public Handler handler_send = new Handler(Looper.myLooper());
	Runnable runnable_send = new Runnable() {
		int second = 180;
		int print_second;
		int minute;

		@Override
		public void run() {
			minute = second / 60;
			print_second = second % 60;
			if (second < 0) {
				tv_count.setText("인증 시간 만료");
				et_email_value.setEnabled(false);
				handler_send.removeCallbacks(runnable_send);
			} else {
				res = getResources();
				String text = String.format(res.getString(R.string.timer), minute, print_second);
				tv_count.setText(text);
				handler_send.postDelayed(runnable_send, 1000);
			}
			second--;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		allcheck = false;
		cb_1_check = false;
		cb_2_check = false;
		signup_ok = false;
		tv_count = findViewById(R.id.tv_count);
		et_email = findViewById(R.id.et_email);
		et_email_value = findViewById(R.id.et_email_value);
		bt_sendmail = findViewById(R.id.bt_sendmail);
		bt_check = findViewById(R.id.bt_check);
		cb_all = findViewById(R.id.cb_all) ;
		cb_1 = findViewById(R.id.cb_1) ;
		//cb_2 = findViewById(R.id.cb_2) ;
		bt_signup_ok = findViewById(R.id.bt_signup_ok);
		et_id = findViewById(R.id.et_email);
		et_pw = findViewById(R.id.et_pw);
		et_checkpw = findViewById(R.id.et_checkpw);
		tv_cb1 = findViewById(R.id.tv_cb1);

		login_data = getSharedPreferences("login_data",MODE_PRIVATE);

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

		//인증번호보내기 버튼 눌렀을 때
		bt_sendmail.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				id = String.valueOf(et_id.getText());
				String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
				if(!Objects.equals(get_id, "no_id")){
					Toast.makeText(getApplicationContext(), "이미 존재 하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
				}
				else{
					//이메일 형식 체크
					get_email_check = String.valueOf(et_email.getText());
					if(!Objects.equals(get_email_check, "") && !android.util.Patterns.EMAIL_ADDRESS.matcher(get_email_check).matches())
					{
						Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
					}
					if(Objects.equals(get_email_check, "")){
						Toast.makeText(getApplicationContext(), "아이디(이메일)를 입력 하세요.", Toast.LENGTH_SHORT).show();
					}
					if(!Objects.equals(get_email_check, "") && android.util.Patterns.EMAIL_ADDRESS.matcher(get_email_check).matches()) {
						bt_sendmail.setVisibility(View.INVISIBLE);
						tv_count.setVisibility(View.VISIBLE);
						tv_count.setText("인증번호 전송 중");

						Thread thread = new Thread(new Send_email());
						thread.start();
					}
				}
			}
		});

		//인증번호 확인 버튼 눌렀을 때
		bt_check.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				get_code_value = String.valueOf(et_email_value.getText());
				if (Objects.equals(get_code_value, get_code)) {
					signup_ok = true;
					handler_send.removeCallbacks(runnable_send);
					tv_count.setText("인증 완료");
					et_email_value.setEnabled(false);
					Toast.makeText(getApplicationContext(), "인증 완료", Toast.LENGTH_SHORT).show();
				} else {
					signup_ok = false;
					Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_SHORT).show();
				}
			}
		});

		tv_cb1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				//스틱코드에 접속하여 생산성을 향상시켜 보세요, https://stickode.com/
				Intent intent = new Intent(signup_activity.this, terms_activity.class);
				startActivity(intent);
			}
		});

		/*//all약관동의 버튼
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
		}) ;*/

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
					//cb_all.setChecked(false);
					cb_1_check = false;
					//allcheck = false;
				}
			}
		}) ;

		/*//2약관동의 버튼
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
		}) ;*/



		bt_signup_ok.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				id = String.valueOf(et_id.getText());

				pw = String.valueOf(et_pw.getText());

				checkpw = String.valueOf(et_checkpw.getText());

				String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
				if(!Objects.equals(get_id, "no_id")){
					Toast.makeText(getApplicationContext(), "이미 존재 하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
				}
				if(Objects.equals(get_id, "no_id")){
					if(Objects.equals(id, "")){
						Toast.makeText(getApplicationContext(), "만들 아이디를 입력 하세요.", Toast.LENGTH_SHORT).show();
					}

					if(Objects.equals(pw, "")){
						Toast.makeText(getApplicationContext(), "비밀번호를 입력 하세요.", Toast.LENGTH_SHORT).show();
					}

					if(!Objects.equals(pw, "") && Objects.equals(checkpw, "")){
						Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력 하세요.", Toast.LENGTH_SHORT).show();
					}


					//비밀번호 형식 체크
					if(!Pattern.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,15}$", pw))
					{
						Toast.makeText(getApplicationContext(), "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show();
					}
					if(Pattern.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,15}$", pw))
					{
						if(!Objects.equals(pw, "") && !Objects.equals(checkpw, "") && !Objects.equals(pw, checkpw)){
							Toast.makeText(getApplicationContext(), "비밀번호가 서로 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
						}
					}



					if(Objects.equals(get_id, "no_id") && !Objects.equals(id, "") && !Objects.equals(pw, "") && !Objects.equals(checkpw, "")){
						/*if(!cb_1_check || !cb_2_check){
							Toast.makeText(getApplicationContext(), "필수약관에 동의 하세요.", Toast.LENGTH_SHORT).show();
						}*/
						if(!cb_1_check){
							Toast.makeText(getApplicationContext(), "필수약관에 동의 하세요.", Toast.LENGTH_SHORT).show();
						}
					}

					if(!signup_ok){
						Toast.makeText(getApplicationContext(), "이메일 인증을 완료 하세요.", Toast.LENGTH_SHORT).show();
					}
				}

				/*if(signup_ok && cb_1_check && cb_2_check && Objects.equals(get_id, "no_id") && !Objects.equals(id, "") && !Objects.equals(pw, "") && !Objects.equals(checkpw, "")){
					SharedPreferences.Editor edit = login_data.edit();
					edit.putString(id, pw);//test이름을 가진 키에 et_text에 입력 된 값을 저장
					edit.commit();
					Intent intent = new Intent(signup_activity.this, loginpage_activity.class);
					startActivity(intent);
					finish();

					System.out.println("id = "+id);
					System.out.println("pw = "+pw);
				}*/
				if(signup_ok && cb_1_check && Objects.equals(get_id, "no_id") && !Objects.equals(id, "") && !Objects.equals(pw, "") && !Objects.equals(checkpw, "")){
					SharedPreferences.Editor edit = login_data.edit();
					edit.putString(id, pw);//test이름을 가진 키에 et_text에 입력 된 값을 저장
					edit.commit();
					Intent intent = new Intent(signup_activity.this, loginpage_activity.class);
					startActivity(intent);
					finish();

					System.out.println("id = "+id);
					System.out.println("pw = "+pw);
				}
			}
		});
	}

	class Send_email implements Runnable {
		@Override
		public void run() {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.permitDiskReads()
					.permitDiskWrites()
					.permitNetwork().build());
			try {
				GMailSender gMailSender = new GMailSender("dev.beomnyangi@gmail.com", "gozldgkwlakgo724");
				//GMailSender.sendMail(제목, 본문내용, 받는사람);
				get_code = gMailSender.getEmailCode();
				gMailSender.sendMail("인증번호 입니다.", get_code, String.valueOf(et_email.getText()));
				//Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
				handler_send.post(runnable_send);
			} catch (SendFailedException e) {
				//Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
				tv_count.setVisibility(View.INVISIBLE);
				bt_sendmail.setVisibility(View.VISIBLE);
			} catch (MessagingException e) {
				//Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
				tv_count.setVisibility(View.INVISIBLE);
				bt_sendmail.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}