package ie.gmit.computing.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {
	public static final String COLLEGUES = "Collegues";
	    public static final String COLLEGUES_ID = "id";
	    public static final String COLLEGUES_NAME = "name";
	    private static final String DATABASE_NAME = "Collegues.db";
	    private static final int DATABASE_VERSION = 1;
	    // creation SQLite statement
	    private static final String DATABASE_CREATE = "create table " + COLLEGUES
	            + "(" + COLLEGUES_ID + " integer primary key autoincrement, "
	            + COLLEGUES_NAME + " text not null);";
	    public DataBaseWrapper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
			
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + COLLEGUES);
			onCreate(db);			
		}


}
