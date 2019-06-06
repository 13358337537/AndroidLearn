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
		// TODO �Զ����ɵķ������

		//�ж�sd��״̬>>���ز��ܼ���
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			//��ȡ������ļ���/����
			String filename =et_sdf_name.getText().toString();
			String value =et_sdf_value.getText().toString();
			//�õ�ָ���ļ���OutputStream
			  //�õ�SD���µ�files·��
			String filesPath =getExternalFilesDir(null).getAbsolutePath();
			  //�������·��
			String filePath =filesPath+"/"+filename;
			  //����fileoutputstream
			FileOutputStream fos =new FileOutputStream(filePath);
			//д����
			fos.write(value.getBytes("utf-8"));
			fos.close();
			//��ʾ
			Toast.makeText(this, "�������", 0).show();
		}else {
			Toast.makeText(this, "sd��û�й���", 0).show();			
		}
		
	}
	
	public void read(View v) throws IOException {
		// TODO �Զ����ɵķ������
		// �ж�sd��״̬>>���ز��ܼ���
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// ��ȡ������ļ���/����
			String filename = et_sdf_name.getText().toString();
			// �õ�ָ���ļ���InputStream
			// �õ�SD���µ�files·��
			String filesPath = getExternalFilesDir(null).getAbsolutePath();
			// �������·��
			String filePath = filesPath + "/" + filename;
			// ����fileoutputstream
			FileInputStream fis = new FileInputStream(filePath);
			// ��ȡ����
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				;
			}
			String content = baos.toString();
			// ��ʾ
			et_sdf_value.setText(content);
			fis.close();
		} else {
			Toast.makeText(this, "sd��û�й���", 0).show();
		}
	}
	
	
   //  ·��   /storage/sdcard/xxx/xx
	public void save2(View v) throws IOException {
		// �ж�sd��״̬>>���ز��ܼ���
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// ��ȡ������ļ���/����
			String filename = et_sdf_name.getText().toString();
			String value = et_sdf_value.getText().toString();
			// �õ�ָ���ļ���OutputStream
			//  /storage/sdcard
			String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
			// /storage/sdcard/xxx
			File file =new File(sdPath+"/sdcard");
			if(!file.exists()) {
				file.mkdir();
			}
			// /storage/sdcard/xxx/xx
			String filePath =sdPath+"/sdcard/"+filename;
			//���������
			FileOutputStream fos =new FileOutputStream(filePath);
			// д����
			fos.write(value.getBytes("utf-8"));
			fos.close();
			// ��ʾ
			Toast.makeText(this, "�������", 0).show();
		} else {
			Toast.makeText(this, "sd��û�й���", 0).show();
		}

	}
	public void read2(View v) throws IOException {
		// TODO �Զ����ɵķ������
		// �ж�sd��״̬>>���ز��ܼ���
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// ��ȡ������ļ���/����
			String filename = et_sdf_name.getText().toString();
			// �õ�ָ���ļ���InputStream
			// �õ�SD���µ�files·��
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			String filePath = sdPath + "/sdcard/" + filename;
			// ����fileoutputstream
			FileInputStream fis = new FileInputStream(filePath);
			// ��ȡ����
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				;
			}
			String content = baos.toString();
			fis.close();
			// ��ʾ
			et_sdf_value.setText(content);
		} else {
			Toast.makeText(this, "sd��û�й���", 0).show();
		}
	}

}

