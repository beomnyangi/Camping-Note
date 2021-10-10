package com.nova.antaeul;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.nova.antaeul.loading_activity.mynote_modi;

public class campingnote_adapter extends RecyclerView.Adapter<campingnote_adapter.ViewHolder> {
	private static final String TAG= "log";

	Context context;
	LayoutInflater inflacter;
	Glide glide;
	int layout;
	//adapter에 들어갈 list
	public static ArrayList<campingnote_data> info = new ArrayList<>();

	//MainActivity 에서 context, info 받아오기
	public campingnote_adapter(Context context, int layout){
		this.context = context;
		inflacter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layout = layout;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		//recycler_view_item.xml을 inflate 시킨다.
		View view = LayoutInflater.from(context).inflate(R.layout.activity_campingnote_item, parent, false);

		return new ViewHolder(view);
	}


	/*onbindviewholder 란 ListView / RecyclerView 는 inflate를 최소화 하기 위해서 뷰를 재활용 하는데,
	이 때 각 뷰의 내용을 업데이트 하기 위해 findViewById 를 매번 호출 해야합니다.
	이로 인해 성능저하가 일어남에 따라 ItemView의 각 요소를 바로 엑세스 할 수 있도록 저장해두고 사용하기 위한 객체입니다.*/
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
		//list 에 아이템 하나하나 보여주는 메소드 입니다.

		campingnote_data item = info.get(position);
		holder.tv_name.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context,position,Toast.LENGTH_LONG).show();
			}
		});

		holder.tv_name.setText(item.getName());
		holder.tv_address.setText(item.getAddress());
		//glide.with(holder.itemView.getContext()).load(item.getImageuri()).into(holder.iv_image);
		System.out.println("테스트"+item.getImageuri());

		if(item.getImageuri() != null){
			glide.with(holder.itemView.getContext()).load(item.getImageuri()).into(holder.iv_image);
		}

		//holder.iv_image.setImageURI(item.getImageuri());
		holder.tv_writedate.setText(item.getWritedate());
		//holder.imageView.getResources().getDrawable(item.getImageResource());
	}

	//리스트의 아이템 갯수
	@Override
	public int getItemCount() {
		return info.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView tv_name;
		TextView tv_address;
		ImageView iv_image;
		TextView tv_writedate;

		ViewHolder(View view) {
			super(view);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_address = (TextView) view.findViewById(R.id.tv_address);
			iv_image = (ImageView) view.findViewById(R.id.iv_image);
			tv_writedate = (TextView) view.findViewById(R.id.tv_writedate);

			//아이템을 클릭 했을 때
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mynote_modi = true;
					int pos = getAdapterPosition() ;
					System.out.println("pos"+pos);

					if (pos != RecyclerView.NO_POSITION) {
						campingnote_data data_pos;
						data_pos = info.get(pos);
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

						Intent intent = new Intent(context, campingnotedetail_activity.class);

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

						context.startActivity(intent);
					}
				}
			});
		}
	}

	public void addInfo(campingnote_data data){
		info.add(data);
	}

	public void remove(int pos) {
		info.remove(pos);
	}

	public void modify(int pos, campingnote_data data){
		info.set(pos, data);
	}
}