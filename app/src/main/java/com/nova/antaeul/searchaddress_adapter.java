package com.nova.antaeul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.nova.antaeul.modifynote_activity.modify_addsearch;

public class searchaddress_adapter extends RecyclerView.Adapter<searchaddress_adapter.ViewHolder> {
	private static final String TAG= "log";

	public static boolean search_ok = false;

	Context context;
	LayoutInflater inflacter;
	Glide glide;
	int layout;
	//adapter에 들어갈 list
	public static ArrayList<searchaddress_data> searchaddress_info = new ArrayList<>();

	//MainActivity 에서 context, info 받아오기
	public searchaddress_adapter(Context context, int layout){
		this.context = context;
		inflacter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layout = layout;
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		//recycler_view_item.xml을 inflate 시킨다.
		View view = LayoutInflater.from(context).inflate(R.layout.activity_searchaddressitem, parent, false);
		
		return new ViewHolder(view);
	}
	
	
	/*onbindviewholder 란 ListView / RecyclerView 는 inflate를 최소화 하기 위해서 뷰를 재활용 하는데,
	이 때 각 뷰의 내용을 업데이트 하기 위해 findViewById 를 매번 호출 해야합니다.
	이로 인해 성능저하가 일어남에 따라 ItemView의 각 요소를 바로 엑세스 할 수 있도록 저장해두고 사용하기 위한 객체입니다.*/
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
		//list 에 아이템 하나하나 보여주는 메소드 입니다.
		
		searchaddress_data item = searchaddress_info.get(position);
		holder.tv_address_name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(context,position,Toast.LENGTH_LONG).show();
			}
		});
		System.out.println("테스트"+item.getAddress_name());
		System.out.println("테스트"+item.getRoad_address_name());

		holder.tv_placename.setText(item.getPlace_name());

		if(item.getAddress_name() == null){
			holder.tv_address_name.setText("검색결과가 없습니다.");
			holder.tv_road_address_name.setText(item.getRoad_address_name());
		}
		if(item.getRoad_address_name() == null){
			holder.tv_address_name.setText(item.getAddress_name());
			holder.tv_road_address_name.setText("검색결과가 없습니다.");
		}
		if(item.getAddress_name() == null && item.getRoad_address_name() == null){
			holder.tv_address_name.setText("검색결과가 없습니다.");
			holder.tv_road_address_name.setText("검색결과가 없습니다.");
		}
		if(item.getAddress_name() != null && item.getRoad_address_name() != null){
			holder.tv_address_name.setText(item.getAddress_name());
			holder.tv_road_address_name.setText(item.getRoad_address_name());
		}
		//holder.tv_address_name.setText(item.getAddress_name());
		//holder.tv_road_address_name.setText(item.getRoad_address_name());
		//holder.imageView.getResources().getDrawable(item.getImageResource());
	}
	
	//리스트의 아이템 갯수
	@Override
	public int getItemCount() {
		return searchaddress_info.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView tv_address_name;
		TextView tv_road_address_name;
		TextView tv_placename;

		
		ViewHolder(final View view) {
			super(view);

			tv_placename = (TextView) view.findViewById(R.id.tv_placename);
			tv_address_name = (TextView) view.findViewById(R.id.tv_address_name);
			tv_road_address_name = (TextView) view.findViewById(R.id.tv_road_address_name);
			
			//아이템을 클릭 했을 때
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					int pos = getAdapterPosition() ;
					System.out.println("pos"+pos);
					
					if (pos != RecyclerView.NO_POSITION) {

						search_ok = true;
						searchaddress_data test;
						test = searchaddress_info.get(pos);
						Log.v(TAG, String.valueOf(test));

						String addressnameStr = test.getAddress_name() ;
						String road_addressnameStr = test.getRoad_address_name() ;
						double addressname_xStr = test.getX() ;
						double addressname_yStr = test.getY() ;

						Log.i("LoginData","addressnameStr : "+ addressnameStr);
						Log.i("LoginData","road_addressnameStr : "+ road_addressnameStr);
						Log.i("LoginData","addressname_xStr : "+ addressname_xStr);
						Log.i("LoginData","addressname_yStr : "+ addressname_yStr);

						if(modify_addsearch == true) {
							Intent intent = new Intent(context, modifynote_activity.class);
							if(addressnameStr == null){
								intent.putExtra("address", road_addressnameStr);
							}
							if(road_addressnameStr == null){
								intent.putExtra("address", addressnameStr);
							}
							if(addressnameStr != null && road_addressnameStr != null){
								intent.putExtra("address", road_addressnameStr);
							}
							intent.putExtra("addressname_x", addressname_xStr);
							intent.putExtra("addressname_y", addressname_yStr);

							int from = pos;
							String to = Integer.toString(from);

							intent.putExtra("position", to);

							Log.e(TAG, "position"+to);
							context.startActivity(intent) ;
							((Activity)context).finish();
						}

						if(modify_addsearch == false) {
							Intent intent = new Intent(context, campingnotewrite_activity.class);
							if(addressnameStr == null){
								intent.putExtra("address", road_addressnameStr);
							}
							if(road_addressnameStr == null){
								intent.putExtra("address", addressnameStr);
							}
							if(addressnameStr != null && road_addressnameStr != null){
								intent.putExtra("address", road_addressnameStr);
							}
							intent.putExtra("addressname_x", addressname_xStr);
							intent.putExtra("addressname_y", addressname_yStr);

							int from = pos;
							String to = Integer.toString(from);

							intent.putExtra("position", to);

							Log.e(TAG, "position"+to);
							context.startActivity(intent) ;
							((Activity)context).finish();
						}


					}
				}
			});
		}
	}
	
	public void addInfo(searchaddress_data data){
		searchaddress_info.add(data);
	}
	
	public void remove(int pos) {
		searchaddress_info.remove(pos);
	}
	
	public void modify(int pos, searchaddress_data data){
		searchaddress_info.set(pos, data);
	}
}