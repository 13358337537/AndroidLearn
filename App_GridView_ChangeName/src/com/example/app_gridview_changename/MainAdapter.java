package com.example.app_gridview_changename;

import com.example.app_gridview.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private String[] names;
	private int[] icons;
	private Context context;
	private SharedPreferences sp;
	
	public MainAdapter(Context context, String[] names, int[] icons) {
		// TODO 自动生成的构造函数存根
		super();
		this.context=context;
		this.names=names;
		this.icons=icons;
		sp=context.getSharedPreferences("cmk", Context.MODE_PRIVATE);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return names[position  ];
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		if(convertView==null) {
			
			convertView=View.inflate(context, R.layout.item_main , null);
		}
		  ImageView imageView=(ImageView) convertView.findViewById(R.id.iv_item_icon);
		  TextView textView=(TextView) convertView.findViewById(R.id.tv_item_name);
		
		  imageView.setImageResource(icons[position]);
		  textView.setText(names[position]);
		  
		  //从sp中读取保存的名称
		  if(position==0) {
			 String savedName= sp.getString("NAME", null);
			 if(savedName!=null) {
				 textView.setText(savedName);
			 }
		  }
		  
		return convertView;
	}

}
