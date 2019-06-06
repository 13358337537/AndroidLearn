package com.example.app_listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemLongClickListener {

	private ListView lv1;
	private List<AppInfo> data;
	private AppAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化成员变量
		lv1 = (ListView) findViewById(R.id.lv1);
		data = getAllAppInfos();
		adapter = new AppAdapter();
		lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new OnItemClickListener() {
			
        	/**
        	 * 
        	 * @param parent    ListView
        	 * @param view      当前行Item视图对象
        	 * @param position  当前行下标
        	 * @param id
        	 */
        	
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自动生成的方法存根
				//提示当前行的应用名称
				String appName=data.get(position).getAppName();
				//Toast
				Toast.makeText(MainActivity.this, appName, 0).show();
			}
		});
        lv1.setOnItemLongClickListener(this);
	}

	class AppAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自动生成的方法存根

			// 如果convertView为null 加载item的布局文件
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
				//Log.e("TAG", "getView() load layout");
			}
			// 得到 当前行数据对象
			AppInfo appInfo = data.get(position);
			// 得到当前行需要更新的子View对象
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			TextView textView = (TextView) convertView.findViewById(R.id.tv_item_name);
			// 给视图设置数据
			imageView.setImageDrawable(appInfo.getIcon());
			textView.setText(appInfo.getAppName());

			return convertView;
		}

	}
	
	/*
	 * 得到手机中所有应用信息的列表
	 * AppInfo
	 *  Drawable icon    图片对象 
	 *  String appName
	 *  String packageName
	 */
	protected List<AppInfo> getAllAppInfos() {

		List<AppInfo> list = new ArrayList<AppInfo>();
		// 得到应用的packgeManager
		PackageManager packageManager = getPackageManager();
		// 创建一个主界面的intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// 得到包含应用信息的列表
		List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(intent, 0);
		// 遍历
		for (ResolveInfo ri : ResolveInfos) {
			// 得到包名
			String packageName = ri.activityInfo.packageName;
			// 得到图标
			Drawable icon = ri.loadIcon(packageManager);
			// 得到应用名称
			String appName = ri.loadLabel(packageManager).toString();
			// 封装应用信息对象
			AppInfo appInfo = new AppInfo(icon, appName, packageName);
			// 添加到list
			list.add(appInfo);
		}
		return list;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自动生成的方法存根
		//删除当前行
		     //删除当前行数据
		data.remove(position);
		
		     //更新列表 
		//lv1.setAdapter(adapter);  显示列表          回到起始位置         重新加载Item视图对象
		adapter.notifyDataSetChanged();    //不会回到起始位置  使用所以缓存的Item视图对象
		
		return true;
	}

}
