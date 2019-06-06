package com.example.datastorage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SpActivity extends Activity {

	private EditText et_sp_key;
	private EditText et_sp_value;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sp);
		
		et_sp_key=(EditText) findViewById(R.id.et_sp_key);
		et_sp_value=(EditText) findViewById(R.id.et_sp_value);
		
		//�õ�sp����
		sp =getSharedPreferences("Android", Context.MODE_PRIVATE);
		
	}
	
	public void save(View v) {
		// TODO �Զ����ɵķ������

		//�õ�Editor����
		Editor edit=sp.edit();
		//�õ������key/value
		String key =et_sp_key.getText().toString();
		String value =et_sp_value.getText().toString();
		//ʹ��editor����key-value
		edit.putString(key, value).commit();
		Toast.makeText(this, "�������", 0).show();
	}
	public void read(View v) {
		// TODO �Զ����ɵķ������ 
		//�õ������key
		String key =et_sp_key.getText().toString();
	    //����key��ȡ����
		String value=sp.getString(key, "null");
		//��ʾ
		if(value==null) {
			Toast.makeText(this, "null", 0).show();			
		}else {
			et_sp_value.setText(value);
		}
	}
}
