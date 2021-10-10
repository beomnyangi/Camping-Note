package com.nova.antaeul;
import android.content.res.Resources;
import android.os.Handler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class signup2_activity extends AppCompatActivity {
	private static final String TAG = "log";

	String get_code;
	String get_code_value;
	String get_email_check;

	boolean signup_ok;
	boolean send_num = false;
	boolean send_result = false;

	TextView tv_count;
	EditText et_email;
	EditText et_email_value;

	Resources res;

	Handler handler_send = new Handler(Looper.myLooper());
	Runnable runnable_send = new Runnable() {
		int second = 120;
		int print_second;
		int minute;

		@Override
		public void run() {
			minute = second / 60;
			print_second = second % 60;
			if (second < 0) {
				handler_send.removeCallbacks(runnable_send);
				//tv_count.setVisibility(View.GONE);
				tv_count.setText("시간 만료");
			} else {
				res = getResources();
				String text = String.format(res.getString(R.string.timer), minute, print_second);
				tv_count.setText(text);
				handler_send.postDelayed(runnable_send, 1000);
				//System.out.println("타이머 작동 중");
			}
			second--;
		}
	};

	Handler handler_send_email = new Handler(Looper.myLooper());
	Runnable runnable_send_email = new Runnable() {
		@Override
		public void run() {
			if(send_result == false) {
				StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
						.permitDiskReads()
						.permitDiskWrites()
						.permitNetwork().build());
				try {
					handler_send.post(runnable_send_email);
					send_result = true;
					GMailSender gMailSender = new GMailSender("dev.beomnyangi@gmail.com", "gozldgkwlakgo724");
					//GMailSender.sendMail(제목, 본문내용, 받는사람);
					get_code = gMailSender.getEmailCode();
					gMailSender.sendMail("인증번호 입니다.", get_code, String.valueOf(et_email.getText()));
					Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
				} catch (SendFailedException e) {
					send_num = false;
					Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
				} catch (MessagingException e) {
					send_num = false;
					Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					send_num = false;
					e.printStackTrace();
				}
			}
			else{
				handler_send.removeCallbacks(runnable_send_email);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup2);
		signup_ok = false;
		tv_count = findViewById(R.id.tv_count);
		et_email = findViewById(R.id.et_email);
		et_email_value = findViewById(R.id.et_email_value);

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
		Button bt_sendmail = (Button) findViewById(R.id.bt_sendmail);
		bt_sendmail.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {

				get_email_check = String.valueOf(et_email.getText());
				if(get_email_check.contains("@") && get_email_check.contains(".")){
					if (send_num == false) {
						send_num = true;
						tv_count.setVisibility(View.VISIBLE);
						handler_send.post(runnable_send);
						handler_send_email.post(runnable_send_email);
					}
					else{
						Toast.makeText(getApplicationContext(), "인증번호를 입력 하세요.", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
				}

			}
		});

		//확인 버튼 눌렀을 때
		Button bt_check = (Button) findViewById(R.id.bt_check);
		bt_check.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				get_code_value = String.valueOf(et_email_value.getText());
				if (Objects.equals(get_code_value, get_code)) {
					signup_ok = true;
					Toast.makeText(getApplicationContext(), "인증 완료", Toast.LENGTH_SHORT).show();
				} else {
					signup_ok = false;
					Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_SHORT).show();
				}
			}
		});

		//회원가입 완료 버튼 눌렀을 때
		Button bt_signupok = (Button) findViewById(R.id.bt_signupok);
		bt_signupok.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				if (signup_ok) {
					Intent intent = new Intent(signup2_activity.this, loginpage_activity.class);
					startActivity(intent);
					finish();
					Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "인증번호를 확인 하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

}