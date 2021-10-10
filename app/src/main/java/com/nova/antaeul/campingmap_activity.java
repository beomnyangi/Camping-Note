package com.nova.antaeul;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import static com.nova.antaeul.campingnotewrite_activity.finish_activity;
import static com.nova.antaeul.campingnotewrite_activity.put_uri_string;
import static com.nova.antaeul.loading_activity.map_go;
import static com.nova.antaeul.loading_activity.map_modi;
import static com.nova.antaeul.loading_activity.more_go;
import static com.nova.antaeul.loading_activity.mynote_go;
import static com.nova.antaeul.loading_activity.timeline_go;
import static com.nova.antaeul.signup1_activity.id;
import static net.daum.mf.map.api.MapView.CurrentLocationTrackingMode.TrackingModeOff;
import static net.daum.mf.map.api.MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading;
import static net.daum.mf.map.api.MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading;

public class campingmap_activity extends AppCompatActivity {
	private static final String TAG= "log";
	
	private Gson gson;
	ImageButton ib_loc_off;
	ImageButton ib_loc_on;
	ImageButton ib_compass_on;
	MapView mapView;

	public static String loc_check;

	ArrayList<campingnote_data> datas;

	private static double longitude_d, latitude_d;

	public Handler handler_send = new Handler(Looper.myLooper());
	Runnable runnable_send = new Runnable() {

		@Override
		public void run() {
			mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
				public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
					Log.d("test", "10");

					mapView.setMapCenterPointAndZoomLevel(mapPOIItem.getMapPoint(), 2, true);
					//mapView.setMapCenterPoint(mapPOIItem, true);
				}
				public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {


					Log.d("test", "11");
					int pos = mapPOIItem.getTag();
					System.out.println("pos"+pos);

					if (pos != RecyclerView.NO_POSITION) {
						campingnote_data data_pos;
						data_pos = campingnote_adapter.info.get(pos);
						Log.v(TAG, String.valueOf(data_pos));

						String nameStr = data_pos.getName() ;
						String imageStr = data_pos.getImageexplain() ;
						String uriStr = data_pos.getImageuri() ;
						String reviewStr = data_pos.getReview() ;
						String addressStr = data_pos.getAddress() ;
						double x = data_pos.getX() ;
						double y = data_pos.getY() ;
						String numberStr = data_pos.getNumber() ;
						String writedateStr = data_pos.getWritedate() ;

						Intent intent = new Intent(campingmap_activity.this, campingnotedetail_activity.class);

						intent.putExtra("name", nameStr);
						intent.putExtra("imageexplain", imageStr);
						intent.putExtra("uri", uriStr);
						intent.putExtra("review", reviewStr);
						intent.putExtra("address", addressStr);
						intent.putExtra("addressname_x", x);
						intent.putExtra("addressname_y", y);
						intent.putExtra("number", numberStr);
						intent.putExtra("writedate", writedateStr);

						System.out.println("uri : "+uriStr);

						int from = pos;
						String to = Integer.toString(from);

						intent.putExtra("position", to);

						Log.e(TAG, "position"+to);

						startActivity(intent);
						map_modi = true;
					}
				}
				public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
					Log.d("test", "12");
				}
				public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
					Log.d("test", "13");
				}
			});
			handler_send.post(runnable_send);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_campingmap);

		if(finish_activity == false){
			timeline_go = false;
			map_go = false;
			mynote_go = false;
			more_go = false;
		}

		mapView = new MapView(this);
		ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
		mapViewContainer.addView(mapView);

		mapView.removeAllPOIItems();

		ib_loc_off = findViewById(R.id.ib_loc_off);
		ib_loc_on = findViewById(R.id.ib_loc_on);
		ib_compass_on = findViewById(R.id.ib_compass_on);

		loc_check = "off";
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(campingmap_activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
		}

		mapView.setCurrentLocationTrackingMode(TrackingModeOff);

		if(loc_check == "off"){
			ib_loc_off.setVisibility(View.VISIBLE);
			ib_loc_on.setVisibility(View.INVISIBLE);
			ib_compass_on.setVisibility(View.INVISIBLE);
		}
		if(loc_check == "on"){
			ib_loc_off.setVisibility(View.INVISIBLE);
			ib_loc_on.setVisibility(View.VISIBLE);
			ib_compass_on.setVisibility(View.INVISIBLE);
		}
		if(loc_check == "comp"){
			ib_loc_off.setVisibility(View.INVISIBLE);
			ib_loc_on.setVisibility(View.INVISIBLE);
			ib_compass_on.setVisibility(View.VISIBLE);
		}



		mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.566826004661, 126.978652258309), true);

		handler_send.post(runnable_send);

		ib_loc_off.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapView.setCurrentLocationTrackingMode(TrackingModeOnWithoutHeading);
				ib_loc_off.setVisibility(View.INVISIBLE);
				ib_loc_on.setVisibility(View.VISIBLE);
				ib_compass_on.setVisibility(View.INVISIBLE);
			}
		});
		ib_loc_on.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapView.setCurrentLocationTrackingMode(TrackingModeOnWithHeading);
				ib_loc_off.setVisibility(View.INVISIBLE);
				ib_loc_on.setVisibility(View.INVISIBLE);
				ib_compass_on.setVisibility(View.VISIBLE);
			}
		});
		ib_compass_on.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mapView.setCurrentLocationTrackingMode(TrackingModeOff);
				ib_loc_off.setVisibility(View.VISIBLE);
				ib_loc_on.setVisibility(View.INVISIBLE);
				ib_compass_on.setVisibility(View.INVISIBLE);
			}
		});

		//타임라인 버튼 눌렀을 때
		Button bt_timeline = findViewById(R.id.bt_timeline);
		bt_timeline.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				ArrayList<timeline_data> datas_timeline;
				timeline_adapter.timeline_info.clear();
				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				String get_key;
				//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
				Map<String,?> keys = login_data.getAll();
				for(Map.Entry<String,?> entry : keys.entrySet()){
					get_key = entry.getKey();
					System.out.println("id : "+entry.getKey());
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
					SharedPreferences sp_data = getSharedPreferences(get_key, MODE_PRIVATE);
					SharedPreferences.Editor edit = sp_data.edit();
					gson = new GsonBuilder().create();
					Type listType = new TypeToken<ArrayList<timeline_data>>() {}.getType();

					String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
					int size = str_data.length();
					//System.out.println(size);
					if(size > 0) {
						String getName = null;
						String getAddress = null;
						double getX;
						double getY;
						String getImageexplain = null;
						String getImageuri = null;
						String getNumber = null;
						String getReview = null;
						String getWritedate = null;
						String getWritername = null;

						datas_timeline = gson.fromJson(str_data, listType);
						System.out.println(datas_timeline.size());
						for (timeline_data data : datas_timeline) {
							getName = data.getName();
							getAddress = data.getAddress();
							getX = data.getX();
							getY = data.getY();
							getImageexplain = data.getImageexplain();
							getImageuri = data.getImageuri();
							getNumber = data.getNumber();
							getReview = data.getReview();
							getWritedate = data.getWritedate();
							getWritername = get_key;

							timeline_adapter.timeline_info.add(new timeline_data(getName, getImageexplain, getImageuri, getReview, getAddress, getX, getY, getNumber, getWritedate, getWritername));
						}
					}
				}

				Intent intent = new Intent(campingmap_activity.this, timeline_activity.class) ;
				startActivity(intent) ;
				finish();
				handler_send.removeCallbacks(runnable_send);
			}
		});

		//글쓰기 이미지버튼 눌렀을 때
		ImageButton ib_writenote = findViewById(R.id.ib_writenote);
		ib_writenote.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				put_uri_string = null;
				map_go = true;
				Intent intent = new Intent(campingmap_activity.this, campingnotewrite_activity.class) ;
				startActivity(intent) ;
			}
		});

		/*내 노트 버튼 눌렀을 때*/
		Button bt_mycampingnote = (Button) findViewById(R.id.bt_mycampingnote);
		bt_mycampingnote.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e(TAG, "bt_mypage");
				// TODO : start New Activity

				ArrayList<timeline_data> datas_timeline;
				timeline_adapter.timeline_info.clear();
				SharedPreferences login_data = getSharedPreferences("login_data",MODE_PRIVATE);
				String get_key;
				//SharedPreferences에 저장된 모든 키값과 벨류 값 가져오기
				Map<String,?> keys = login_data.getAll();
				for(Map.Entry<String,?> entry : keys.entrySet()) {
					get_key = entry.getKey();
					System.out.println("id : " + entry.getKey());
					System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
					SharedPreferences sp_data = getSharedPreferences(get_key, MODE_PRIVATE);
					SharedPreferences.Editor edit = sp_data.edit();
					gson = new GsonBuilder().create();
					Type listType = new TypeToken<ArrayList<timeline_data>>() {
					}.getType();

					String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
					int size = str_data.length();
					//System.out.println(size);
					if (size > 0) {
						String getName = null;
						String getAddress = null;
						double getX;
						double getY;
						String getImageexplain = null;
						String getImageuri = null;
						String getNumber = null;
						String getReview = null;
						String getWritedate = null;
						String getWritername = null;

						datas_timeline = gson.fromJson(str_data, listType);
						System.out.println(datas_timeline.size());
						for (timeline_data data : datas_timeline) {
							getName = data.getName();
							getAddress = data.getAddress();
							getX = data.getX();
							getY = data.getY();
							getImageexplain = data.getImageexplain();
							getImageuri = data.getImageuri();
							getNumber = data.getNumber();
							getReview = data.getReview();
							getWritedate = data.getWritedate();
							getWritername = get_key;

							timeline_adapter.timeline_info.add(new timeline_data(getName, getImageexplain, getImageuri, getReview, getAddress, getX, getY, getNumber, getWritedate, getWritername));
						}
					}
				}

				Intent intent = new Intent(campingmap_activity.this, campingnote_activity.class);
				startActivity(intent);
				finish();
				handler_send.removeCallbacks(runnable_send);
			}
		});
		/*더보기 버튼 눌렀을 때*/
		Button bt_viewmore = (Button) findViewById(R.id.bt_viewmore);
		bt_viewmore.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e(TAG, "bt_viewmore");
				// TODO : start New Activity
				Intent intent = new Intent(campingmap_activity.this, viewmore_activity.class);
				startActivity(intent);
				finish();
				handler_send.removeCallbacks(runnable_send);
			}
		});

		SharedPreferences sp_data = getSharedPreferences(id, MODE_PRIVATE);
		SharedPreferences.Editor edit = sp_data.edit();
		gson = new GsonBuilder().create();
		Type listType = new TypeToken<ArrayList<campingnote_data>>() {}.getType();
		String str_data = sp_data.getString("mynote", "");//test이름을 가진 키에 저장 된 값을 text에 저장. 값이 없을 시"저장 된 값 없음"이라는 값을 반환
		int size = str_data.length();

		if(size > 0) {
			String getName;
			String getAddress;
			double getX;
			double getY;
			int count = 0;
			datas = gson.fromJson(str_data, listType);
			for (campingnote_data data : datas) {
				getName = data.getName();
				getX = data.getX();
				getY = data.getY();

				MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(getY,getX);// api에서 위도와 경도값을 받아 마커포인트 값으로 받는다

				MapPOIItem marker = new MapPOIItem();
				marker.setItemName(getName);
				marker.setTag(count);
				marker.setMapPoint(MARKER_POINT);
				marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
				marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
				mapView.addPOIItem(marker);
				count++;
			}
		}

		handler_send.post(runnable_send);
		mapView.fitMapViewAreaToShowAllPOIItems();

	} // end of onCreate


	class event implements Runnable {
		@Override
		public void run() {
			while (true){

			}
		}

	}


	@Override
	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();

	}
}