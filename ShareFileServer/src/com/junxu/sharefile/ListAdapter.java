package com.junxu.sharefile;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
	
	List<FileInfo> files = new ArrayList<FileInfo>();
	private Context context;
	
	public ListAdapter(List<FileInfo> files,Context context) {
		this.files = files;
		this.context = context;
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.path = (TextView) convertView.findViewById(R.id.path);
			
			
			viewHolder.image.setImageBitmap(files.get(position).getBm());
			viewHolder.name.setText(files.get(position).getName());
			viewHolder.path.setText("文件路径："+files.get(position).getPath());
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			
			viewHolder.image.setImageBitmap(files.get(position).getBm());
			viewHolder.name.setText(files.get(position).getName());
			viewHolder.path.setText("文件路径："+files.get(position).getPath());
		}
		
		
		return convertView;
	}
	
	public static class ViewHolder{
		private ImageView image;
		private TextView name;
		private TextView path;
	}

}
