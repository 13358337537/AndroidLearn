package com.example.component;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity {

	
	private Button btn_menu_show_cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		btn_menu_show_cm = (Button) findViewById(R.id.btn_menu_show_cm);
		btn_menu_show_cm.setOnCreateContextMenuListener(this);
    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO 自动生成的方法存根
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 3, 0, "添加");
		menu.add(0, 4, 0, "删除");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO 自动生成的方法存根
		switch (item.getItemId()) {
		case 3:
			Toast.makeText(this, "添加", 0).show();
			break;
		case 4:
			Toast.makeText(this, "删除", 0).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO 自动生成的方法存根
		// 纯编码方式
		menu.add(0, 1, 0, "增加");
		menu.add(0, 2, 0, "删除");
		
		//菜单文件方式
		// 1.得到菜单加载器对象   
//		新建一个xml菜单文件
//		MenuInflater menuInflater =getMenuInflater();
		// 2.加载菜单文件
//		menuInflater.inflate(R.menu.xxx,menu);
		return super.onCreateOptionsMenu(menu);
				
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自动生成的方法存根
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "增加", 0).show();
			break;
		case 2:
			Toast.makeText(this, "删除", 0).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
