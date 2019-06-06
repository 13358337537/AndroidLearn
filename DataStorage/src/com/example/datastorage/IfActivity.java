package com.example.datastorage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class IfActivity extends Activity {

	private ImageView iv_if;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_if);
		
		iv_if = (ImageView) findViewById(R.id.iv_if);
		
	}
	
	public void save(View v) throws IOException {
		// TODO �Զ����ɵķ������

		//�õ�InputStream -->��ȡassets�µ�logo.png
		  //�õ�AssetsManager
		AssetManager manager =getAssets();
		InputStream inputStream = manager.open("logo.png");
		
		//��ȡOutputStream -->/data/data/packageName/files/logo.png
		FileOutputStream fileOutputStream = openFileOutput("logo.png", Context.MODE_PRIVATE);
		
		//�߶���д     �̶�д��
		byte[] buffer =new byte[1024];
		int len=-1;
		while((len=inputStream.read(buffer))!=-1) {
			fileOutputStream.write(buffer, 0, len);;
		}
		fileOutputStream.close();
		inputStream.close();
		//��ʾ
		Toast.makeText(this, "�������", 0).show();
	}
	
	public void read(View v) {
		// TODO �Զ����ɵķ������

		//�õ�ͼƬ�ļ�·��
		String filespath =getFilesDir().getAbsolutePath(); 
		String imagepath =filespath+"/logo.png";
		//����ͼƬ�ļ��õ�bitmap����
		Bitmap bitmap=BitmapFactory.decodeFile(imagepath);
		//�������õ�imageview����ʾ
		iv_if.setImageBitmap(bitmap);
	}
}
