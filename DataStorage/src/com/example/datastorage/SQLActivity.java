package com.example.datastorage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SQLActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql);
	}
	
	/*
	 * 创建库
	 */
	public void testCreateDB(View v) {

		DBhelper dbhelper =new DBhelper(this,1);
		
		//获取连接
		SQLiteDatabase database = dbhelper.getReadableDatabase();   //空间不足时不会
	    //dbhelper.getWritableDatabase()   //会报错
		Toast.makeText(this, "创建数据库", 0).show();
	}

	/*
	 * 更新库
	 */
	public void testUpdateDB(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		
		//获取连接
	    SQLiteDatabase database = dbhelper.getReadableDatabase();
	    
	    Toast.makeText(this, "更新数据库", 0).show();
	}

	/*
	 * 添加记录
	 */
	public void testInsert(View v) {
		//1.得到连接
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//2.执行insert   insert into person (name,age) values ('Tom1',11)
		ContentValues contentValues =new ContentValues();
		contentValues.put("name", "Tom");
		contentValues.put("age", 11);
		long id = database.insert("person", null, contentValues);
		//3.关闭
		database.close();
		//4.提示
		Toast.makeText(this, "id="+id, 1).show();
	}

	/*
	 * 更新
	 */
	public void testUpdate(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//执行update
		ContentValues values =new ContentValues();
		values.put("name", "jack");
		values.put("age", "13");
		int updataCount = database.update("person", values, "_id=?", new String[] {"4"});
		database.close();
		Toast.makeText(this, "updataCount"+updataCount, 1).show();
	}
	

	/*
	 * 删除
	 */
	public void testDelete(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//执行delete		
		int deleteCount = database.delete("person", "_id=2", null);
		database.close();
		Toast.makeText(this, "deleteCount"+deleteCount, 1).show();
	
	}

	/*
	 * 查询
	 */
	public void testQuery(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//执行query select * from person
		Cursor cursor = database.query("person", null, null, null, null, null, null);
		       cursor = database.query("person", null, "_id=?", new String[] {"3"}, null, null, null);
		
		//得到匹配的总记录数
		int count =cursor.getCount();
		
		//取数据
		while(cursor.moveToNext()) {
			int id=cursor.getInt(0);
			String name =cursor.getString(1);
			int age =cursor.getInt(cursor.getColumnIndex("age"));
			Log.e("TAG", id+"-"+name+"-"+age);
		}
		cursor.close();
		database.close();
		Toast.makeText(this, "count"+count, 1).show();
	}


	/*
	 * 测试事务处理
	 * updata person set age=13 where  _id=1
	 * updata person set age=15 where  _id=3	
	 *  
	 * 一个功能中对数据库进行多个操作,要就是都成功或者都失败
	 * 
	 * 事务处理的3步
	 * 1.开启事务(获取连接后)
	 * 2.设置事务成功(在全部正常执行完后)
	 * 3.结束事务(finally中)
	 */
	public void testTransaction(View v) {
		SQLiteDatabase database =null;
		try {					
		DBhelper dbhelper =new DBhelper(this,2);
		         database = dbhelper.getReadableDatabase();
		//1.开启事务(获取连接后)
		database.beginTransaction();
		
		
		//执行update
		ContentValues values =new ContentValues();		
		values.put("age", 13);
		int updataCount = database.update("person", values, "_id=?", new String[] {"1"});
		Log.e("TAG", "updataCount="+updataCount );
		
		//异常
		boolean flag =true;
		if(flag) {
			throw new RuntimeException("异常");
		}
		
		
		values =new ContentValues();		
		values.put("age", 15);
		int updataCount2 = database.update("person", values, "_id=?", new String[] {"3"});
		Log.e("TAG", "updataCount2="+updataCount2 );
		
		//2.设置事务成功(在全部正常执行完后)
		database.setTransactionSuccessful();
	
		
		}  catch (Exception e) {
			
			e.printStackTrace();
			Toast.makeText(this, "异常", 1).show();
		}
		
		finally {
			
			// 3.结束事务(finally中)
			if(database!=null) {
				database.endTransaction();
				database.close();
		 }
	  }
   }
}
