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
		// TODO �Զ����ɵķ������
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, 3, 0, "���");
		menu.add(0, 4, 0, "ɾ��");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO �Զ����ɵķ������
		switch (item.getItemId()) {
		case 3:
			Toast.makeText(this, "���", 0).show();
			break;
		case 4:
			Toast.makeText(this, "ɾ��", 0).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �Զ����ɵķ������
		// �����뷽ʽ
		menu.add(0, 1, 0, "����");
		menu.add(0, 2, 0, "ɾ��");
		
		//�˵��ļ���ʽ
		// 1.�õ��˵�����������   
//		�½�һ��xml�˵��ļ�
//		MenuInflater menuInflater =getMenuInflater();
		// 2.���ز˵��ļ�
//		menuInflater.inflate(R.menu.xxx,menu);
		return super.onCreateOptionsMenu(menu);
				
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �Զ����ɵķ������
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "����", 0).show();
			break;
		case 2:
			Toast.makeText(this, "ɾ��", 0).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
