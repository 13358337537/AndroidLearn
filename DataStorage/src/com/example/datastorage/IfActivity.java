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
		// TODO 自动生成的方法存根

		//得到InputStream -->读取assets下的logo.png
		  //得到AssetsManager
		AssetManager manager =getAssets();
		InputStream inputStream = manager.open("logo.png");
		
		//读取OutputStream -->/data/data/packageName/files/logo.png
		FileOutputStream fileOutputStream = openFileOutput("logo.png", Context.MODE_PRIVATE);
		
		//边读边写     固定写法
		byte[] buffer =new byte[1024];
		int len=-1;
		while((len=inputStream.read(buffer))!=-1) {
			fileOutputStream.write(buffer, 0, len);;
		}
		fileOutputStream.close();
		inputStream.close();
		//提示
		Toast.makeText(this, "保存完成", 0).show();
	}
	
	public void read(View v) {
		// TODO 自动生成的方法存根

		//得到图片文件路径
		String filespath =getFilesDir().getAbsolutePath(); 
		String imagepath =filespath+"/logo.png";
		//加载图片文件得到bitmap对象
		Bitmap bitmap=BitmapFactory.decodeFile(imagepath);
		//将其设置到imageview中显示
		iv_if.setImageBitmap(bitmap);
	}
}
