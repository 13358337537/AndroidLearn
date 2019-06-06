package com.example.app_gridview_changename;

import com.example.app_gridview.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemLongClickListener {

	private GridView gv1;
	private MainAdapter adapter;
	String[] names = new String[] { "手机防盗", "通讯卫士", "软件管理", "流量管理", "进程管理", "手机杀毒", "缓存清理", "高级工具", "设置中心" };

	int[] icons = new int[] { R.drawable.widget01, R.drawable.widget02, R.drawable.widget03, R.drawable.widget04,
			R.drawable.widget05, R.drawable.widget06, R.drawable.widget07, R.drawable.widget08, R.drawable.widget09 };
	
	private SharedPreferences sp;

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
		gv1.setOnItemLongClickListener(this);
		
		///////所有item都能修改  如何操作////////
		
		sp=getSharedPreferences("cmk", Context.MODE_PRIVATE);
	
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自动生成的方法存根
		
		if(position==0) {
			//得到当前显示的名称
			final TextView textView =(TextView) view.findViewById(R.id.tv_item_name);
			String hint =textView.getText().toString();
			//为dialog准备输入对象
			final EditText editText =new EditText(this);
			editText.setHint(hint);
			//显示
			new AlertDialog.Builder(this)
			.setTitle("修改名称")
			.setView(editText)
			.setPositiveButton("修改", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					String newname =editText.getText().toString();
					//界面更新
					textView.setText(newname);
					//保存到sp中
					sp.edit().putString("NAME", newname).commit();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO 自动生成的方法存根
					
				}
			})
			.show();
		}
		
		return true;
	}
}

