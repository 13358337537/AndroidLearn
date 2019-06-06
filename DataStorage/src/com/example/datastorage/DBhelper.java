package com.example.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

	public DBhelper(Context context, int version) {
		super(context, "mkgg.db", null, version);
		// TODO �Զ����ɵĹ��캯�����
	}
	/**
	 * ʲôʱ��Żᴴ�����ݿ��ļ�
	 *  1.���ݿ��ļ�������
	 *  2.�������ݿ�
	 * 
	 * ʲôʱ�����
	 * �����ݿ��ļ�������ʱ�����--->һ��
	 * �ڴ˷�������ʲô
	 * ����
	 * ����һЩ��ʼ������
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO �Զ����ɵķ������
		Log.e("TAG", "DBhelper");
		//����
        String sql= "create table person(_id integer primary key autoincrement,name varchar,age int)";
        	db.execSQL(sql);
        //
        	db.execSQL("insert into person (name,age) values ('Tom1',11)");
        	db.execSQL("insert into person (name,age) values ('Tom2',12)");
        	db.execSQL("insert into person (name,age) values ('Tom3',13)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������

	}

}
