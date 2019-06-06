package com.example.datastorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SDFActivity extends Activity {

	
	private EditText et_sdf_name;
	private EditText et_sdf_value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sdf);
		
		et_sdf_name=(EditText) findViewById(R.id.et_sdf_name);
		et_sdf_value=(EditText) findViewById(R.id.et_sdf_value);
	}
	
	public void save(View v) throws IOException {
		// TODO 自动生成的方法存根

		//判断sd卡状态>>挂载才能继续
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			//读取输入的文件名/内容
			String filename =et_sdf_name.getText().toString();
			String value =et_sdf_value.getText().toString();
			//得到指定文件的OutputStream
			  //得到SD卡下的files路径
			String filesPath =getExternalFilesDir(null).getAbsolutePath();
			  //组成完整路径
			String filePath =filesPath+"/"+filename;
			  //创建fileoutputstream
			FileOutputStream fos =new FileOutputStream(filePath);
			//写数据
			fos.write(value.getBytes("utf-8"));
			fos.close();
			//提示
			Toast.makeText(this, "保存完成", 0).show();
		}else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();			
		}
		
	}
	
	public void read(View v) throws IOException {
		// TODO 自动生成的方法存根
		// 判断sd卡状态>>挂载才能继续
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 读取输入的文件名/内容
			String filename = et_sdf_name.getText().toString();
			// 得到指定文件的InputStream
			// 得到SD卡下的files路径
			String filesPath = getExternalFilesDir(null).getAbsolutePath();
			// 组成完整路径
			String filePath = filesPath + "/" + filename;
			// 创建fileoutputstream
			FileInputStream fis = new FileInputStream(filePath);
			// 读取数据
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				;
			}
			String content = baos.toString();
			// 显示
			et_sdf_value.setText(content);
			fis.close();
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
	}
	
	
   //  路径   /storage/sdcard/xxx/xx
	public void save2(View v) throws IOException {
		// 判断sd卡状态>>挂载才能继续
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 读取输入的文件名/内容
			String filename = et_sdf_name.getText().toString();
			String value = et_sdf_value.getText().toString();
			// 得到指定文件的OutputStream
			//  /storage/sdcard
			String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
			// /storage/sdcard/xxx
			File file =new File(sdPath+"/sdcard");
			if(!file.exists()) {
				file.mkdir();
			}
			// /storage/sdcard/xxx/xx
			String filePath =sdPath+"/sdcard/"+filename;
			//创建输出流
			FileOutputStream fos =new FileOutputStream(filePath);
			// 写数据
			fos.write(value.getBytes("utf-8"));
			fos.close();
			// 提示
			Toast.makeText(this, "保存完成", 0).show();
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}

	}
	public void read2(View v) throws IOException {
		// TODO 自动生成的方法存根
		// 判断sd卡状态>>挂载才能继续
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 读取输入的文件名/内容
			String filename = et_sdf_name.getText().toString();
			// 得到指定文件的InputStream
			// 得到SD卡下的files路径
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			String filePath = sdPath + "/sdcard/" + filename;
			// 创建fileoutputstream
			FileInputStream fis = new FileInputStream(filePath);
			// 读取数据
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				;
			}
			String content = baos.toString();
			fis.close();
			// 显示
			et_sdf_value.setText(content);
		} else {
			Toast.makeText(this, "sd卡没有挂载", 0).show();
		}
	}

}

