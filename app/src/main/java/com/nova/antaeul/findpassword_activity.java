package com.nova.antaeul;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
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

public class findpassword_activity extends AppCompatActivity {
	String id;

	Button bt_sendmail;
	Button bt_check;

	boolean allcheck;
	boolean cb_1_check;
	boolean cb_2_check;
	boolean signup_ok = false;

	String get_code;
	String get_code_value;
	String get_email_check;

	TextView tv_count;

	EditText et_email;
	EditText et_email_value;

	Resources res;

	SharedPreferences login_data;

	Handler handler_send = new Handler(Looper.myLooper());
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

	Handler handler_send_email = new Handler(Looper.myLooper());
	Runnable runnable_send_email = new Runnable() {
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
				Toast.makeText(getApplicationContext(), "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show();
				handler_send.post(runnable_send);
			} catch (SendFailedException e) {
				Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
				tv_count.setVisibility(View.INVISIBLE);
				bt_sendmail.setVisibility(View.VISIBLE);
			} catch (MessagingException e) {
				Toast.makeText(getApplicationContext(), "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
				tv_count.setVisibility(View.INVISIBLE);
				bt_sendmail.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			handler_send_email.removeCallbacks(runnable_send_email);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword);

		allcheck = false;
		cb_1_check = false;
		cb_2_check = false;
		signup_ok = false;
		tv_count = findViewById(R.id.tv_count);
		et_email = findViewById(R.id.et_email);
		et_email_value = findViewById(R.id.et_email_value);
		bt_sendmail = findViewById(R.id.bt_sendmail);
		bt_check = findViewById(R.id.bt_check);

		login_data = getSharedPreferences("login_data",MODE_PRIVATE);

		/*뒤로가기버튼 눌렀을 때*/
		ImageButton ib_back = (ImageButton) findViewById(R.id.ib_back);
		ib_back.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				finish();
			}
		});

		bt_sendmail.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				id = String.valueOf(et_email.getText());
				String get_id = login_data.getString(id,"no_id");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
				if(!Objects.equals(get_id, "no_id")){
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
						//handler_send_email.post(runnable_send_email);
						Thread thread = new Thread(new Send_email());
						thread.start();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "존재하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(getApplicationContext(), "인증 완료", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(findpassword_activity.this, findpasswordsuccess_activity.class);
					intent.putExtra("id", id);
					startActivity(intent);
					finish();
				} else {
					signup_ok = false;
					Toast.makeText(getApplicationContext(), "인증 실패", Toast.LENGTH_SHORT).show();
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