package com.wg.wxrzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	// ==============================================================

	/*** 0是否是新数据库,1当前关卡,2总关卡,3金钱,4分数,5游戏模式 */
	private int gamedata[] = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	private int otherdata[] = new int[] { 0, 0, 0, 0, 0, 0, 2 };

	public boolean isold() {
		return gamedata[0] == 1;
	}

	public void setGameData(int gamedata[]) {
		this.gamedata = gamedata;
	}

	public void setGameData(int index, int value) {
		gamedata[index] = value;
	}

	public int[] getGameData() {
		return gamedata;
	}

	public int[] getOtherData() {
		return otherdata;
	}

	public void setOtherData(int[] otherdata) {
		this.otherdata = otherdata;
	}

	public void setOtherData(int index, int value) {
		otherdata[index] = value;
	}

	// ==============================================================
	private MyHelper m_Helper;
	private SQLiteDatabase dbsql;
	private final String DB_NAME = "wxrzz" + "wggame.db";
	private final int VERSION = 1;
	private final String TB_NAME = "wxrzz" + "wggame";
	private final String ID = "_id";
	private final String OTHER = "other";
	private String other = "";

	private void getSAnalysis(String other) {
		if (!other.trim().equals("")) {
			String tem[] = other.split(";");
			String tem1[] = tem[0].split(",");
			for (int i = 0; i < gamedata.length; i++) {
				gamedata[i] = Integer.parseInt(tem1[i]);
			}
			String tem2[] = tem[1].split(",");
			for (int i = 0; i < otherdata.length; i++) {
				otherdata[i] = Integer.parseInt(tem2[i]);
			}
		}
	}

	private void setSAnalysis(boolean bool) {
		if (bool) {
			gamedata = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			otherdata = new int[] { 0, 0, 0, 0, 0, 0, 2 };
		} else {
			gamedata[0] = 1;
		}
		other = "";
		for (int i = 0; i < gamedata.length; i++) {
			other += gamedata[i] + ",";
		}
		other += ";";
		for (int i = 0; i < otherdata.length; i++) {
			other += otherdata[i] + ",";
		}
		other += ";";
	}

	public void saveDB() {
		dbsql.update(TB_NAME, getValue(false), ID + " = 1", null);
	}

	private void initDB() {
		Cursor c;
		c = dbsql.query(TB_NAME, null, null, null, null, null, null);
		if (c.isAfterLast()) {
			dbsql.insert(TB_NAME, ID, getValue(true));
		} else {
			final int c_0 = c.getColumnIndexOrThrow(OTHER);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				other = c.getString(c_0);
			}
			getSAnalysis(other);
		}
		c.close();
	}

	public void delDB() {
		dbsql.update(TB_NAME, getValue(true), ID + " = 1", null);
	}

	private ContentValues getValue(boolean bool) {
		ContentValues values = new ContentValues();
		if (bool) {
			setSAnalysis(bool);
			values.put(OTHER, other);
		} else {
			setSAnalysis(bool);
			values.put(OTHER, other);
		}
		return values;
	}

	public String toString() {
		return other;
	}

	class MyHelper extends SQLiteOpenHelper {

		public MyHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		public void onCreate(SQLiteDatabase args) {
			args.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" + ID
					+ " INTEGER PRIMARY KEY," + OTHER + " VARCHAR " + ")");
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
			onCreate(db);
		}
	}

	private void initData() {
		m_Helper = new MyHelper(WGActivity.activity, DB_NAME, null, VERSION);
		dbsql = m_Helper.getWritableDatabase();
		initDB();
	}

	public static DB db = new DB();

	public DB() {
		initData();
	}

}
