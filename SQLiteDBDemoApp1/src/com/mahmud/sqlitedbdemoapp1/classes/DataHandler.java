/**
 * 
 */
package com.mahmud.sqlitedbdemoapp1.classes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class DataHandler {
	
	public static final String NAME_COL = "name";
	public static final String EMAIL_COL = "email";
	public static final String TABLE_NAME = "tblDemo";
	public static final String DATABASE_NAME = "dbDemo";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_CREATE
		= "CREATE TABLE " + TABLE_NAME + " ("
			+ NAME_COL + " TEXT NOT NULL, "
			+ EMAIL_COL + " TEXT NOT NULL); ";
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;

	/**
	 * 
	 */
	public DataHandler(Context ctx) {
		dbHelper = new DatabaseHelper(ctx);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			try {
				db.execSQL(TABLE_CREATE);
			} catch (SQLException ex) {
				// TODO: handle exception
				Log.e("tableCreateError", ex.getMessage());
				ex.printStackTrace();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
			
		}
		
	}
	
	public long insertData(String name, String email){
		db = dbHelper.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(NAME_COL, name);
		content.put(EMAIL_COL, email);
		long returnValue = db.insertOrThrow(TABLE_NAME, null, content);
		db.close();
		return returnValue;
	}
	
	public List<PersonDAO> returnData(){
		List<PersonDAO> personList = new ArrayList<PersonDAO>();
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[]{NAME_COL,EMAIL_COL}, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				personList.add(new PersonDAO(
						cursor.getString(0),
						cursor.getString(1)));
			}while(cursor.moveToNext());
		}
		db.close();
		return personList;
	}

}
