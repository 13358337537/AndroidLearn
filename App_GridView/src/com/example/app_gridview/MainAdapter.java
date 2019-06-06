package com.example.app_gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

	private String[] names;
	private int[] icons;
	private Context context;
	public MainAdapter(Context context, String[] names, int[] icons) {
		// TODO �Զ����ɵĹ��캯�����
		super();
		this.context=context;
		this.names=names;
		this.icons=icons;
	}

	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO �Զ����ɵķ������
		return names[position  ];
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �Զ����ɵķ������
		if(convertView==null) {
			
			convertView=View.inflate(context, R.layout.item_main , null);
		}
		  ImageView imageView=(ImageView) convertView.findViewById(R.id.iv_item_icon);
		  TextView textView=(TextView) convertView.findViewById(R.id.tv_item_name);
		
		  imageView.setImageResource(icons[position]);
		  textView.setText(names[position]);
		  
		return convertView;
	}

}
