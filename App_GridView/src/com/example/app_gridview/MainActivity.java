package com.example.app_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GridView gv1;
	private MainAdapter adapter;
	String[] names = new String[] { "手机防盗", "通讯卫士", "软件管理", "流量管理", "进程管理", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

	int[] icons = new int[] { R.drawable.widget01, R.drawable.widget02, R.drawable.widget03, R.drawable.widget04,
			R.drawable.widget05, R.drawable.widget06, R.drawable.widget07, R.drawable.widget08, R.drawable.widget09 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		gv1=(GridView) findViewById(R.id.gv1);
		adapter = new MainAdapter(this,names,icons);
		gv1.setAdapter(adapter);
		
		gv1.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自动生成的方法存根
				String name =names[position];
				Toast.makeText(MainActivity.this, name, 0).show();
			}
		});
		
	}
}
