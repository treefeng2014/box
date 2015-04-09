package com.example.boxplatform.define;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDB {
	private SQLiteDatabase db;
	public MessageDB(Context context) {
		db = context.openOrCreateDatabase("boxplatform2015.sql",
				Context.MODE_PRIVATE, null);
	}

	public void saveMsg(String tablename, Msg2Innserver entity) {
		System.out.println("---saveMsg(String tablename, Msg2Innserver entity)---enter");
		db.execSQL("CREATE table IF NOT EXISTS "
				+ tablename
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,message TEXT)");
		db.execSQL(
				"insert into " + tablename
						+ " (flag,message) values(?,?)",
				new Object[] { entity.strFlag,entity.strMsg });
	}

	public void DeleteMsg(String tablename, Msg2Innserver entity) {
		db.execSQL("CREATE table IF NOT EXISTS "
				+ tablename
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,message TEXT)");
		db.execSQL("delete from " + tablename + " where flag = " + "\"" + entity.strFlag + "\"");
	}
	public void DeleteAll(String  tablename) {
		db.execSQL("CREATE table IF NOT EXISTS "
				+ tablename
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,message TEXT)");
		db.execSQL("delete from " + tablename);
		System.out.println("---DeleteAll(String  tablename) ---enter");
	}
	public List<Msg2Innserver> getMsg(String tablename) {
		List<Msg2Innserver> list = new ArrayList<Msg2Innserver>();
		System.out.println("---getMsg(String tablename)---enter");
		db.execSQL("CREATE table IF NOT EXISTS "
				+ tablename
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,flag TEXT,message TEXT)");
		Cursor c = db.rawQuery("SELECT * from " + tablename + " ORDER BY _id DESC LIMIT 100", null);
		while (c.moveToNext()) {
			String strFlag = c.getString(c.getColumnIndex("flag"));
			String strMsg = c.getString(c.getColumnIndex("message"));
			Msg2Innserver entity = new Msg2Innserver();
			entity.strFlag = strFlag;
			entity.strMsg = strMsg;
			//System.out.println( "Message DB test" + name + date + message + img + isComMsg);
			list.add(entity);
		}
		c.close();
		return list;
	}
	public void close() {
		if (db != null)
			db.close();
	}
}
