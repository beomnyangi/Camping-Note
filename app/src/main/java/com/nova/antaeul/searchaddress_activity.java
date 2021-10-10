package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class searchaddress_activity extends AppCompatActivity {
	String REST_API_KEY_campingnote = "7c5c52498367b9ff59f5b16c693c796b";

	EditText et_search;
	Button bt_search;
	String get_value;
	String addr;
	String number;
	searchaddress_adapter adapter;
	RecyclerView recyclerView;
	double x;
	double y;

	private Handler handler_send = new Handler(Looper.myLooper());
	Runnable runnable_send = new Runnable() {

		@Override
		public void run() {
			Log.i("LoginData","thread on : ");
			setRecyclerView();
			handler_send.post(runnable_send);
			handler_send.removeCallbacks(runnable_send);
			Log.i("LoginData","thread off : ");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchaddress);

		adapter.searchaddress_info.clear();
		et_search = findViewById(R.id.et_search);
		bt_search = findViewById(R.id.bt_search);

		Intent intent = getIntent();
		String intent_address = intent.getStringExtra("address");

		//et_search.setText(intent_address);

		recyclerView = (RecyclerView) findViewById(R.id.recycle);
		recyclerView.addItemDecoration(new RecyclerViewDiv_height(30));
		recyclerView.addItemDecoration(new RecyclerViewDiv_width(30));
		setRecyclerView();

		bt_search.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				get_value = et_search.getText().toString();
				addr = get_value;

				Thread get_info_keyword = new Thread(new get_info_keyword());
				get_info_keyword.start();
			}
		});

	}

	//recyclerView 와 adapter 를 연결시켜주는 메소드
	void setRecyclerView(){
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.removeAllViewsInLayout();
		recyclerView.setLayoutManager(layoutManager);

		//액티비티에 context 와 item 데이터를 Adapter 에 넘겨준다.
		adapter = new searchaddress_adapter(this, R.layout.activity_searchaddressitem);
		recyclerView.setAdapter(adapter);
	}

	class get_info_keyword implements Runnable {
		@Override
		public void run() {
			String REST_API_KEY = REST_API_KEY_campingnote; // 다음 REST_API_KEY;
			String header = "KakaoAK " + REST_API_KEY; // KakaoAK 다음에 공백 추가

			String apiURL = "https://dapi.kakao.com/v2/local/search/keyword.json?page=45&query="+addr;

			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("Authorization", header);
			String responseBody = get(apiURL,requestHeaders);

			Log.i("LoginData1","responseBody : "+ responseBody);
			System.out.println(responseBody);

			try {
				//넘어온 result 값을 JSONObject 로 변환해주고, 값을 가져오면 되는데요.
				// result 를 Log에 찍어보면 어떻게 가져와야할 지 감이 오실거에요.
				String address_name = null;
				String road_address_address_name = null;
				String place_name;

				JSONObject object = new JSONObject(responseBody);
				JSONArray documents = object.getJSONArray("documents");
				JSONObject meta = object.getJSONObject("meta");

				adapter.searchaddress_info.clear();
				for (int i = 0; i < documents.length(); i++) {
					JSONObject obj = documents.getJSONObject(i);//0
					Log.i("LoginData1","//////////////////////");
					Log.i("LoginData1","documents.length() : "+ documents.length());

					place_name = obj.getString("place_name");//1
					address_name = obj.getString("address_name");//2
					road_address_address_name = obj.getString("road_address_name");//2
					x = obj.getDouble("x");
					y = obj.getDouble("y");


					adapter.addInfo(new searchaddress_data(place_name, address_name, road_address_address_name, x, y));
				}


				String total_count = meta.getString("total_count");



				Log.i("LoginData1","total_count : "+ total_count);

			}
			catch (Exception e) {
				Log.i("LoginData1","e : "+ e);
			}
			handler_send.post(runnable_send);
		}

		final private String get(String apiUrl, Map<String, String> requestHeaders){
			HttpURLConnection con = connect(apiUrl);
			try {
				con.setRequestMethod("GET");
				for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}

				int responseCode = con.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
					return readBody(con.getInputStream());
				} else { // 에러 발생
					return readBody(con.getErrorStream());
				}
			} catch (IOException e) {
				throw new RuntimeException("API 요청과 응답 실패", e);
			} finally {
				con.disconnect();
			}
		}
		private HttpURLConnection connect(String apiUrl){
			try {
				URL url = new URL(apiUrl);
				return (HttpURLConnection)url.openConnection();
			} catch (MalformedURLException e) {
				throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
			} catch (IOException e) {
				throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
			}
		}
		private String readBody(InputStream body){
			InputStreamReader streamReader = new InputStreamReader(body);

			try (BufferedReader lineReader = new BufferedReader(streamReader)) {
				StringBuilder responseBody = new StringBuilder();

				String line;
				while ((line = lineReader.readLine()) != null) {
					responseBody.append(line);
				}

				return responseBody.toString();
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}


	}

}