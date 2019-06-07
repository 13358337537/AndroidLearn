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
		
		
		//��Seekbar���ü���
		sb_progress_loading.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {  //�뿪����
				// TODO �Զ����ɵķ������
				Log.e("TAG", "�뿪����");
				//1.�õ�Seekbar�Ľ���
				int progress = sb_progress_loading.getProgress(); 
				//2.����ΪPrigressBar����
				pb_progress_loading.setProgress(progress);
				//3.�ж��Ƿ�ﵽ���ֵ
				if (progress==sb_progress_loading.getMax()) {
					ll_progress_loading.setVisibility(View.INVISIBLE);  //���ɼ�����ռ�ÿռ�
					//    GONE   ���ɼ��Ҳ�ռ�ÿռ�
				}else {
					ll_progress_loading.setVisibility(View.VISIBLE);
				}
				 //����ﵽ�ˣ�����ll���������ɼ�
				 //���û�ﵽ������ll��������ʾ
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {  //���»���
				// TODO �Զ����ɵķ������
				Log.e("TAG", "���»���");
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO �Զ����ɵķ������                           �����ƶ�
				
			}
		});
	}
}
