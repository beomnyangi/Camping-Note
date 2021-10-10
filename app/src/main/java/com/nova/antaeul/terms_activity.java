package com.nova.antaeul;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.nova.antaeul.campingnotewrite_activity.finish_activity;
import static com.nova.antaeul.campingnotewrite_activity.put_uri_string;
import static com.nova.antaeul.loading_activity.map_go;
import static com.nova.antaeul.loading_activity.more_go;
import static com.nova.antaeul.loading_activity.mynote_go;
import static com.nova.antaeul.loading_activity.timeline_go;

public class terms_activity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_terms);

		ImageButton ib_back = findViewById(R.id.ib_back);
		ib_back.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//버튼이 클릭 됐을 때
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

	}
}