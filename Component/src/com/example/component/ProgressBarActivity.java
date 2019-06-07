package com.example.component;

import android.app.Activity;import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class ProgressBarActivity extends Activity {

	private LinearLayout ll_progress_loading;
	private ProgressBar pb_progress_loading;
	private SeekBar sb_progress_loading;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
		
		ll_progress_loading=(LinearLayout) findViewById(R.id.ll_progress_loading);
		pb_progress_loading=(ProgressBar) findViewById(R.id.pb_progress_loading);
		sb_progress_loading=(SeekBar) findViewById(R.id.sb_progress_loading);
		
		
		//给Seekbar设置监听
		sb_progress_loading.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {  //离开滑杆
				// TODO 自动生成的方法存根
				Log.e("TAG", "离开滑杆");
				//1.得到Seekbar的进度
				int progress = sb_progress_loading.getProgress(); 
				//2.设置为PrigressBar进度
				pb_progress_loading.setProgress(progress);
				//3.判断是否达到最大值
				if (progress==sb_progress_loading.getMax()) {
					ll_progress_loading.setVisibility(View.INVISIBLE);  //不可见，但占用空间
					//    GONE   不可见且不占用空间
				}else {
					ll_progress_loading.setVisibility(View.VISIBLE);
				}
				 //如果达到了，设置ll。。。不可见
				 //如果没达到，设置ll。。。显示
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {  //按下滑杆
				// TODO 自动生成的方法存根
				Log.e("TAG", "按下滑杆");
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO 自动生成的方法存根                           滑杆移动
				
			}
		});
	}
}
