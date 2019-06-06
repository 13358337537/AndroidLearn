package com.example.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

	public DBhelper(Context context, int version) {
		super(context, "mkgg.db", null, version);
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 什么时候才会创建数据库文件
	 *  1.数据库文件不存在
	 *  2.连接数据库
	 * 
	 * 什么时候调用
	 * 当数据库文件创建的时候调用--->一次
	 * 在此方法中做什么
	 * 建表
	 * 插入一些初始化数据
	 */

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自动生成的方法存根
		Log.e("TAG", "DBhelper");
		//建表
        String sql= "create table person(_id integer primary key autoincrement,name varchar,age int)";
        	db.execSQL(sql);
        //
        	db.execSQL("insert into person (name,age) values ('Tom1',11)");
        	db.execSQL("insert into person (name,age) values ('Tom2',12)");
        	db.execSQL("insert into person (name,age) values ('Tom3',13)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根

	}

}
