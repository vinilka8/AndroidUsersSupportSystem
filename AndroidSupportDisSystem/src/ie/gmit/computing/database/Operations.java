package ie.gmit.computing.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Operations {
	private DataBaseWrapper dbHelper;
	private String[] COLLEAGUES_TABLE_COLUMNS = {DataBaseWrapper.COLLEGUES_ID, DataBaseWrapper.COLLEGUES_NAME };
	
	private SQLiteDatabase database;
    public Operations(Context context) {
        dbHelper = new DataBaseWrapper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    
    public Colleague addColleague(String name) {
        ContentValues values = new ContentValues();
        values.put(DataBaseWrapper.COLLEGUES_NAME, name);
 
        long studId = database.insert(DataBaseWrapper.COLLEGUES, null, values);
 
        // now that the student is created return it ...
        Cursor cursor = database.query(DataBaseWrapper.COLLEGUES,
                COLLEAGUES_TABLE_COLUMNS, DataBaseWrapper.COLLEGUES_ID + " = "
                        + studId, null, null, null, null);
 
        cursor.moveToFirst();
        Colleague newComment = parseColleague(cursor);
        cursor.close();
        return newComment;
    }
    public void deleteColleague(Colleague comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(DataBaseWrapper.COLLEGUES, DataBaseWrapper.COLLEGUES_ID
                + " = " + id, null);
    }
    public List<Colleague> getAllColleagues() {
        List<Colleague> colleagues = new ArrayList<Colleague>();
        Cursor cursor = database.query(DataBaseWrapper.COLLEGUES,
                COLLEAGUES_TABLE_COLUMNS, null, null, null, null, null);
 
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
        	Colleague colleague = parseColleague(cursor);
        	colleagues.add(colleague);
            cursor.moveToNext();
        }
        cursor.close();
        return colleagues;
}
    private Colleague parseColleague(Cursor cursor) {
    	Colleague colleague = new Colleague();
    	colleague.setId((cursor.getInt(0)));
    	colleague.setName(cursor.getString(1));
        return colleague;
    }

	 

}
