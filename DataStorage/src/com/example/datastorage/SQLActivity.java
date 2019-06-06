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
	 * ������
	 */
	public void testCreateDB(View v) {

		DBhelper dbhelper =new DBhelper(this,1);
		
		//��ȡ����
		SQLiteDatabase database = dbhelper.getReadableDatabase();   //�ռ䲻��ʱ����
	    //dbhelper.getWritableDatabase()   //�ᱨ��
		Toast.makeText(this, "�������ݿ�", 0).show();
	}

	/*
	 * ���¿�
	 */
	public void testUpdateDB(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		
		//��ȡ����
	    SQLiteDatabase database = dbhelper.getReadableDatabase();
	    
	    Toast.makeText(this, "�������ݿ�", 0).show();
	}

	/*
	 * ��Ӽ�¼
	 */
	public void testInsert(View v) {
		//1.�õ�����
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//2.ִ��insert   insert into person (name,age) values ('Tom1',11)
		ContentValues contentValues =new ContentValues();
		contentValues.put("name", "Tom");
		contentValues.put("age", 11);
		long id = database.insert("person", null, contentValues);
		//3.�ر�
		database.close();
		//4.��ʾ
		Toast.makeText(this, "id="+id, 1).show();
	}

	/*
	 * ����
	 */
	public void testUpdate(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//ִ��update
		ContentValues values =new ContentValues();
		values.put("name", "jack");
		values.put("age", "13");
		int updataCount = database.update("person", values, "_id=?", new String[] {"4"});
		database.close();
		Toast.makeText(this, "updataCount"+updataCount, 1).show();
	}
	

	/*
	 * ɾ��
	 */
	public void testDelete(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//ִ��delete		
		int deleteCount = database.delete("person", "_id=2", null);
		database.close();
		Toast.makeText(this, "deleteCount"+deleteCount, 1).show();
	
	}

	/*
	 * ��ѯ
	 */
	public void testQuery(View v) {
		
		DBhelper dbhelper =new DBhelper(this,2);
		SQLiteDatabase database = dbhelper.getReadableDatabase();
		//ִ��query select * from person
		Cursor cursor = database.query("person", null, null, null, null, null, null);
		       cursor = database.query("person", null, "_id=?", new String[] {"3"}, null, null, null);
		
		//�õ�ƥ����ܼ�¼��
		int count =cursor.getCount();
		
		//ȡ����
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
	 * ����������
	 * updata person set age=13 where  _id=1
	 * updata person set age=15 where  _id=3	
	 *  
	 * һ�������ж����ݿ���ж������,Ҫ���Ƕ��ɹ����߶�ʧ��
	 * 
	 * �������3��
	 * 1.��������(��ȡ���Ӻ�)
	 * 2.��������ɹ�(��ȫ������ִ�����)
	 * 3.��������(finally��)
	 */
	public void testTransaction(View v) {
		SQLiteDatabase database =null;
		try {					
		DBhelper dbhelper =new DBhelper(this,2);
		         database = dbhelper.getReadableDatabase();
		//1.��������(��ȡ���Ӻ�)
		database.beginTransaction();
		
		
		//ִ��update
		ContentValues values =new ContentValues();		
		values.put("age", 13);
		int updataCount = database.update("person", values, "_id=?", new String[] {"1"});
		Log.e("TAG", "updataCount="+updataCount );
		
		//�쳣
		boolean flag =true;
		if(flag) {
			throw new RuntimeException("�쳣");
		}
		
		
		values =new ContentValues();		
		values.put("age", 15);
		int updataCount2 = database.update("person", values, "_id=?", new String[] {"3"});
		Log.e("TAG", "updataCount2="+updataCount2 );
		
		//2.��������ɹ�(��ȫ������ִ�����)
		database.setTransactionSuccessful();
	
		
		}  catch (Exception e) {
			
			e.printStackTrace();
			Toast.makeText(this, "�쳣", 1).show();
		}
		
		finally {
			
			// 3.��������(finally��)
			if(database!=null) {
				database.endTransaction();
				database.close();
		 }
	  }
   }
}
