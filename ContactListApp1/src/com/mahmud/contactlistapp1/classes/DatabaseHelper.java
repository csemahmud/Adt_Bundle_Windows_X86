/**
 * 
 */
package com.mahmud.contactlistapp1.classes;

import java.util.ArrayList;
import java.util.List;

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
	private static final String DATABASE_NAME = "dbContactListApp1.db";
	private static final String TABLE_NAME = "tblContacts";
	private static final String CONTACTID_COLUMN = "contactID";
	private static final String NAME_COLUMN = "name";
	private static final String EMAIL_COLUMN = "email";
	private static final String PHONE_COLUMN = "phone";
	private static final String ADDRESS_COLUMN = "address";
	public static final String TABLE_CREATE 
		= "CREATE TABLE " + TABLE_NAME + " ("
			+ CONTACTID_COLUMN + " INTEGER PRIMARY KEY NOT NULL, "
			+ NAME_COLUMN + " TEXT NOT NULL, "
			+ EMAIL_COLUMN +  " TEXT NOT NULL, "
			+ PHONE_COLUMN +  " TEXT NOT NULL, "
			+ ADDRESS_COLUMN + " TEXT NOT NULL); ";
	
	private SQLiteDatabase db;
	
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
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
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
		int conatactID = 1;
		long returnValue = -10;
		try{
			db = this.getReadableDatabase();
			String query = "SELECT MAX(" + CONTACTID_COLUMN + ") AS maxContactID FROM " + TABLE_NAME;
			Cursor cursor = db.rawQuery(query, null);
			if((cursor !=null) && (cursor.moveToFirst())) {
				conatactID = cursor.getInt(0) + 1;
			}
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		try{
			db = this.getWritableDatabase();
			ContentValues content = new ContentValues();
			content.put(CONTACTID_COLUMN, conatactID);
			content.put(NAME_COLUMN, contact.getName());
			content.put(EMAIL_COLUMN, contact.getEmail());
			content.put(PHONE_COLUMN, contact.getPhone());
			content.put(ADDRESS_COLUMN, contact.getAddress());
			returnValue = db.insertOrThrow(TABLE_NAME, null, content);
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		return returnValue;
	}
	
	public List<ContactDAO> selectAllContacts(){
		List<ContactDAO> personList = new ArrayList<ContactDAO>();
		try{
			db = this.getReadableDatabase();
			Cursor cursor = db.query(TABLE_NAME, 
					new String[]{CONTACTID_COLUMN, NAME_COLUMN, EMAIL_COLUMN, PHONE_COLUMN, ADDRESS_COLUMN}, 
					null, null, null, null, null);
			if(cursor.moveToFirst()){
				do{
					personList.add(new ContactDAO(
							cursor.getInt(0),
							cursor.getString(1),
							cursor.getString(2),
							cursor.getString(3),
							cursor.getString(4)));
				}while(cursor.moveToNext());
			}
			db.close();
		} catch (Exception ex) {
			// TODO: handle exception
			Log.e("Exception", ex.getMessage());
		}
		return personList;
	}
	
	public long updateContactByID(ContactDAO contact){
		long returnValue = -10;
		try{
			db = this.getWritableDatabase();
			ContentValues content = new ContentValues();
			content.put(NAME_COLUMN, contact.getName());
			content.put(EMAIL_COLUMN, contact.getEmail());
			content.put(PHONE_COLUMN, contact.getPhone());
			content.put(ADDRESS_COLUMN, contact.getAddress());
			returnValue = db.update(TABLE_NAME, content, 
					CONTACTID_COLUMN + "=" + contact.getContactID(), null);
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		return returnValue;
	}
	
	public long deleteContactByID(int contactID){
		long returnValue = -10;
		try{
			db = this.getWritableDatabase();
			returnValue = db.delete(TABLE_NAME, 
					CONTACTID_COLUMN + "=" + contactID, null);
			db.close();
		} catch (Exception ex){
			Log.e("Exception", ex.getMessage());
		}
		return returnValue;
	}
	
	public String getDATABASE_NAME(){
		return DATABASE_NAME;
	}

}
