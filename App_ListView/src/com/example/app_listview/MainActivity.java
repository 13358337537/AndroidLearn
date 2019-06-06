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

		// ��ʼ����Ա����
		lv1 = (ListView) findViewById(R.id.lv1);
		data = getAllAppInfos();
		adapter = new AppAdapter();
		lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new OnItemClickListener() {
			
        	/**
        	 * 
        	 * @param parent    ListView
        	 * @param view      ��ǰ��Item��ͼ����
        	 * @param position  ��ǰ���±�
        	 * @param id
        	 */
        	
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO �Զ����ɵķ������
				//��ʾ��ǰ�е�Ӧ������
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
			// TODO �Զ����ɵķ������
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO �Զ����ɵķ������
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO �Զ����ɵķ������
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO �Զ����ɵķ������

			// ���convertViewΪnull ����item�Ĳ����ļ�
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
				//Log.e("TAG", "getView() load layout");
			}
			// �õ� ��ǰ�����ݶ���
			AppInfo appInfo = data.get(position);
			// �õ���ǰ����Ҫ���µ���View����
			ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_item_icon);
			TextView textView = (TextView) convertView.findViewById(R.id.tv_item_name);
			// ����ͼ��������
			imageView.setImageDrawable(appInfo.getIcon());
			textView.setText(appInfo.getAppName());

			return convertView;
		}

	}
	
	/*
	 * �õ��ֻ�������Ӧ����Ϣ���б�
	 * AppInfo
	 *  Drawable icon    ͼƬ���� 
	 *  String appName
	 *  String packageName
	 */
	protected List<AppInfo> getAllAppInfos() {

		List<AppInfo> list = new ArrayList<AppInfo>();
		// �õ�Ӧ�õ�packgeManager
		PackageManager packageManager = getPackageManager();
		// ����һ���������intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// �õ�����Ӧ����Ϣ���б�
		List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(intent, 0);
		// ����
		for (ResolveInfo ri : ResolveInfos) {
			// �õ�����
			String packageName = ri.activityInfo.packageName;
			// �õ�ͼ��
			Drawable icon = ri.loadIcon(packageManager);
			// �õ�Ӧ������
			String appName = ri.loadLabel(packageManager).toString();
			// ��װӦ����Ϣ����
			AppInfo appInfo = new AppInfo(icon, appName, packageName);
			// ��ӵ�list
			list.add(appInfo);
		}
		return list;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO �Զ����ɵķ������
		//ɾ����ǰ��
		     //ɾ����ǰ������
		data.remove(position);
		
		     //�����б� 
		//lv1.setAdapter(adapter);  ��ʾ�б�          �ص���ʼλ��         ���¼���Item��ͼ����
		adapter.notifyDataSetChanged();    //����ص���ʼλ��  ʹ�����Ի����Item��ͼ����
		
		return true;
	}

}
