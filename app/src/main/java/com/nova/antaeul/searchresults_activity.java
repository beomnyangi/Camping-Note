package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class searchresults_activity extends AppCompatActivity {
	private static final String TAG= "log";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresults);
		Intent intent = getIntent();
		String test = intent.getStringExtra("toggle");
		Toast.makeText(searchresults_activity.this, "전달 받은 인텐트 값 = "+test, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();
		/*캠핑노트 목록 눌렀을 때*/
		ConstraintLayout lo_campzone1 = (ConstraintLayout) findViewById(R.id.lo_campingnote) ;
		lo_campzone1.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "lo_campzone1");
				Intent intent = new Intent(searchresults_activity.this, campgroundinformation_activity.class) ;
				startActivity(intent) ;
			}
		});
		/*검색*/
		SearchView searchView = (SearchView) findViewById(R.id.sv_search);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			/*검색 버튼 눌렀을 때*/
			@Override
			public boolean onQueryTextSubmit(String query) {
				Toast.makeText(searchresults_activity.this, "[검색버튼클릭] 검색어 = "+query, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(searchresults_activity.this, searchresults_activity.class) ;
				intent.putExtra("toggle", query);
				startActivity(intent) ;
				finish();
				return true;
			}
			/*검색중인 검색어 때*/
			@Override
			public boolean onQueryTextChange(String newText) {
				Toast.makeText(searchresults_activity.this, "입력하고있는 단어 = "+newText, Toast.LENGTH_LONG).show();
				return true;
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
		/*필터버튼 눌렀을 때*/
		Button filter = (Button) findViewById(R.id.filter);
		filter.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO : start New Activity
				Log.e(TAG, "bt_home");
				Intent intent = new Intent(searchresults_activity.this, searchfilter_activity.class);
				startActivity(intent);
			}
		});
	}
}