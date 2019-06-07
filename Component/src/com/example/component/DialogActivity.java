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
		
		//һ�����
		
		// TODO �Զ����ɵķ������
        new AlertDialog.Builder(this)
                .setTitle("ɾ������")
                .setMessage("��ȷ��ɾ��������")
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO �Զ����ɵķ������
						Toast.makeText(DialogActivity.this, "ɾ������", 0).show();
					}
				})
                .setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO �Զ����ɵķ������
						Toast.makeText(DialogActivity.this, "ȡ��ɾ������", 0).show();
					}
				})
                .show();
	}
    public void showad2(View v) {
    	
    	//��ѡ����
    	
    	final String[] items = {"��","��","��","��"};    //final�ı����ڷ���ִ����ɺ󻹴���
    	new AlertDialog.Builder(this)
    	        .setTitle("ָ��������ɫ")
    	        .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO �Զ����ɵķ������
						//��ʾ��ɫ
						Toast.makeText(DialogActivity.this,items[which],0).show();
						//�Ƴ�dialog
						dialog.dismiss();
					}
				} )
    	        .show();
    }
    
    public void showad3(View v){
    	
    	//�Զ������
    	//��̬���ز����ļ����õ���Ӧ��view����
    	
    	View view=View.inflate(this, R.layout.dialog_view, null);
    	
    	//view����ʵ�����ǲ����ļ�����ǩ������,��������view����
    	//��εõ�һ��������view����view
    	
    	final EditText nameET=(EditText) view.findViewById(R.id.et_dialog_name);
    	final EditText namePSW=(EditText) view.findViewById(R.id.et_dialog_password);
    	
    	new AlertDialog.Builder(this)
    	        .setView(view)
    	        .setNegativeButton("ȡ��",null)
    	        .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO �Զ����ɵķ������
						String username =nameET.getText().toString();
						String password =namePSW.getText().toString();
						Toast.makeText(DialogActivity.this,username+ ":" +password,0).show();
					}
				})
    	        .show();
    	
    }
    
      //��ʾԲ�ν���
	public void showpd1(View v) {  //�ص��������������߳�ִ��
		final ProgressDialog dialog=ProgressDialog.show(this, "���ݼ���", "���ݼ����С�����");
		
		//ģ������һ����ʱ��Ĺ���
		//��ʱ��Ĺ������������߳����󣬵��������߳����
		new Thread() {
			public void run() {   //���߳�
				for(int i=0;i<20;i++) {
					try {
						Thread.sleep(100); 
					}catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				dialog.dismiss();  //�����ڷ��߳�ִ�У����ڲ�ʹ��Handlerʵ�����߳�ִ��
				//�����ڷ��߳�ֱ�Ӹ���UI������ֱ��ʹ��Toast��ʹ��runOnUiThread()��ʵ��
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(DialogActivity.this,"��������ˡ�����",0).show();
					}
				});
			}  
		}.start();	
		
	}
	
	public void showpd2(View v) {
		//1.����dialog����
		  final ProgressDialog pd=new ProgressDialog(this);
		//2.������ʽ
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		//3.��ʾ
		pd.show();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO �Զ����ɵķ������
				int count=20;
				pd.setMax(count);
		        //���߳�
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
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
				Log.e("TAG", hourOfDay+"::"+minute);
			}
		}, hourOfDay, minute, true).show();
	
    }
}










