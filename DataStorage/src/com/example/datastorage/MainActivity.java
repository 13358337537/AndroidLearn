package com.example.datastorage;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClickSP(View v) {
		// TODO 自动生成的方法存根
		Intent intent =new Intent(MainActivity.this, SpActivity.class) ;
		startActivity(intent);          
	}
	public void onClickIF(View v) {
		// TODO 自动生成的方法存根
		Intent intent =new Intent(MainActivity.this, IfActivity.class) ;
		startActivity(intent);
	}
	public void onClickSDF(View v) {
		// TODO 自动生成的方法存根
		Intent intent =new Intent(MainActivity.this, SDFActivity.class) ;
		startActivity(intent);
	}
	public void onClickSq(View v) {
		// TODO 自动生成的方法存根
		Intent intent =new Intent(MainActivity.this, SQLActivity.class) ;
		startActivity(intent);
	}
	public void onClickNW(View v) {
		// TODO 自动生成的方法存根
          
	}
	
}
