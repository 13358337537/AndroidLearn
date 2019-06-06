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
	String[] names = new String[] { "�ֻ�����", "ͨѶ��ʿ", "�������", "��������", "���̹���", "�ֻ�ɱ��", "��������", "�߼�����", "��������" };

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
				// TODO �Զ����ɵķ������
				String name =names[position];
				Toast.makeText(MainActivity.this, name, 0).show();
			}
		});
		gv1.setOnItemLongClickListener(this);
		
		///////����item�����޸�  ��β���////////
		
		sp=getSharedPreferences("cmk", Context.MODE_PRIVATE);
	
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO �Զ����ɵķ������
		
		if(position==0) {
			//�õ���ǰ��ʾ������
			final TextView textView =(TextView) view.findViewById(R.id.tv_item_name);
			String hint =textView.getText().toString();
			//Ϊdialog׼���������
			final EditText editText =new EditText(this);
			editText.setHint(hint);
			//��ʾ
			new AlertDialog.Builder(this)
			.setTitle("�޸�����")
			.setView(editText)
			.setPositiveButton("�޸�", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO �Զ����ɵķ������
					String newname =editText.getText().toString();
					//�������
					textView.setText(newname);
					//���浽sp��
					sp.edit().putString("NAME", newname).commit();
				}
			})
			.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO �Զ����ɵķ������
					
				}
			})
			.show();
		}
		
		return true;
	}
}

