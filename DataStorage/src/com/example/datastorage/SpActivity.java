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
		
		//得到sp对象
		sp =getSharedPreferences("Android", Context.MODE_PRIVATE);
		
	}
	
	public void save(View v) {
		// TODO 自动生成的方法存根

		//得到Editor对象
		Editor edit=sp.edit();
		//得到输入的key/value
		String key =et_sp_key.getText().toString();
		String value =et_sp_value.getText().toString();
		//使用editor保存key-value
		edit.putString(key, value).commit();
		Toast.makeText(this, "保存完成", 0).show();
	}
	public void read(View v) {
		// TODO 自动生成的方法存根 
		//得到输入的key
		String key =et_sp_key.getText().toString();
	    //根据key读取数据
		String value=sp.getString(key, "null");
		//显示
		if(value==null) {
			Toast.makeText(this, "null", 0).show();			
		}else {
			et_sp_value.setText(value);
		}
	}
}
