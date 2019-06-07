package com.example.component;

import java.util.Calendar;
import java.util.zip.Inflater;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	
	}
	public void showad1(View v) {
		
		//一般界面
		
		// TODO 自动生成的方法存根
        new AlertDialog.Builder(this)
                .setTitle("删除数据")
                .setMessage("你确定删除数据吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						Toast.makeText(DialogActivity.this, "删除数据", 0).show();
					}
				})
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						Toast.makeText(DialogActivity.this, "取消删除数据", 0).show();
					}
				})
                .show();
	}
    public void showad2(View v) {
    	
    	//单选界面
    	
    	final String[] items = {"红","蓝","绿","灰"};    //final的变量在方法执行完成后还存在
    	new AlertDialog.Builder(this)
    	        .setTitle("指定背景颜色")
    	        .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						//提示颜色
						Toast.makeText(DialogActivity.this,items[which],0).show();
						//移除dialog
						dialog.dismiss();
					}
				} )
    	        .show();
    }
    
    public void showad3(View v){
    	
    	//自定义界面
    	//动态加载布局文件，得到对应的view对象
    	
    	View view=View.inflate(this, R.layout.dialog_view, null);
    	
    	//view的真实类型是布局文件根标签的类型,包含了子view对象
    	//如何得到一个独立的view的子view
    	
    	final EditText nameET=(EditText) view.findViewById(R.id.et_dialog_name);
    	final EditText namePSW=(EditText) view.findViewById(R.id.et_dialog_password);
    	
    	new AlertDialog.Builder(this)
    	        .setView(view)
    	        .setNegativeButton("取消",null)
    	        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						String username =nameET.getText().toString();
						String password =namePSW.getText().toString();
						Toast.makeText(DialogActivity.this,username+ ":" +password,0).show();
					}
				})
    	        .show();
    	
    }
    
      //显示圆形进度
	public void showpd1(View v) {  //回调方法都是在主线程执行
		final ProgressDialog dialog=ProgressDialog.show(this, "数据加载", "数据加载中。。。");
		
		//模拟器做一个长时间的工作
		//长时间的工作不能在主线程上左，的启动分线程完成
		new Thread() {
			public void run() {   //分线程
				for(int i=0;i<20;i++) {
					try {
						Thread.sleep(100); 
					}catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				dialog.dismiss();  //方法在分线程执行，但内部使用Handler实现主线程执行
				//不能在分线程直接更新UI（不能直接使用Toast）使用runOnUiThread()来实现
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(DialogActivity.this,"加载完成了。。。",0).show();
					}
				});
			}  
		}.start();	
		
	}
	
	public void showpd2(View v) {
		//1.创建dialog对象
		  final ProgressDialog pd=new ProgressDialog(this);
		//2.设置样式
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//3.显示
		pd.show();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO 自动生成的方法存根
				int count=20;
				pd.setMax(count);
		        //分线程
				for(int i=0;i<20;i++) {
					try {
						Thread.sleep(100); 
					}catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					pd.setProgress(pd.getProgress()+1);
				}
				pd.dismiss();
			}
	}).start();
	}

	
	public void showdpd(View v) {
		
		Calendar calendar =Calendar.getInstance();
		
		int year =calendar.get(Calendar.YEAR);
		int monthOfYear =calendar.get(Calendar.MONTH);
		int dayOfMonth =calendar.get(Calendar.DAY_OF_MONTH);
		Log.e("TAG", year+"-"+monthOfYear+"-"+dayOfMonth);
		
		new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO 自动生成的方法存根
				Log.e("TAG", year+"--"+(monthOfYear+1)+"--"+dayOfMonth);
			}
		}, year, monthOfYear, dayOfMonth).show();
	}
	
     public void showtpd(View v) {
		
		Calendar calendar =Calendar.getInstance();
		
		int hourOfDay =calendar.get(Calendar.HOUR_OF_DAY);
		int minute =calendar.get(Calendar.MINUTE);
		
		Log.e("TAG", hourOfDay+":"+minute);
		
		new TimePickerDialog(this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO 自动生成的方法存根
				Log.e("TAG", hourOfDay+"::"+minute);
			}
		}, hourOfDay, minute, true).show();
	
    }
}










