/**
 * 
 */
package com.mahmud.sqliteloginapp1.classes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db_contacts.db";
	private static final String TABLE_NAME = "tblContacts";
	private static final String ID_COLUMN = "id";
	private static final String NAME_COLUMN = "name";
	private static final String EMAIL_COLUMN = "email";
	private static final String USERNAME_COLUMN = "username";
	private static final String PASSWORD_COLUMN = "password";
	public static final String TABLE_CREATE 
		= "CREATE TABLE " + TABLE_NAME + " ("
			+ ID_COLUMN + " INTEGER PRIMARY KEY NOT NULL, "
			+ NAME_COLUMN + " TEXT NOT NULL, "
			+ EMAIL_COLUMN +  " TEXT NOT NULL, "
			+ USERNAME_COLUMN +  " TEXT NOT NULL, "
			+ PASSWORD_COLUMN + " TEXT NOT NULL); ";
	
	private SQLiteDatabase db;
	
	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	@SuppressLint("NewApi")
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(TABLE_CREATE);
		this.db = db;

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}
	
	public long insertContact(ContactDAO contact){
		long id = 1;
		long returnValue = -10;
		try{
			db = this.getReadableDatabase();
			String query = "SELECT * FROM " + TABLE_NAME;
			Cursor cursor = db.rawQuery(query, null);
			int count = cursor.getCount();
			id = count + 1;
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		try{
			db = this.getWritableDatabase();
			ContentValues content = new ContentValues();
			content.put(ID_COLUMN, id);
			content.put(NAME_COLUMN, contact.getName());
			content.put(EMAIL_COLUMN, contact.getEmail());
			content.put(USERNAME_COLUMN, contact.getUsername());
			content.put(PASSWORD_COLUMN, contact.getPassword());
			returnValue = db.insertOrThrow(TABLE_NAME, null, content);
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		return returnValue;
	}
	
	public String checkPass(String username){
		try{
			db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_NAME, new String[]{USERNAME_COLUMN,PASSWORD_COLUMN}, null, null, null, null, null);
			if((cursor !=null) && (cursor.moveToFirst())){
				do{
					if(cursor.getString(0).equals(username)){
						String password = cursor.getString(1);
						db.close();
						return password;
					}
				}while(cursor.moveToNext());
			}
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		return "";
	}

}
