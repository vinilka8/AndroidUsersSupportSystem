package ie.gmit.computing;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import ie.gmit.computing.database.Colleague;
import ie.gmit.computing.database.Operations;

public class MyGroup extends ListActivity{
	private Operations DBoperation;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sqllite);
        DBoperation = new Operations(this);
        DBoperation.open();
        List values = DBoperation.getAllColleagues();
	        // Use the SimpleCursorAdapter to show the
	        // elements in a ListView
	        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, values);
	        setListAdapter(adapter);
	    }
	    public void addUser(View view) {
	        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
	        EditText text = (EditText) findViewById(R.id.editText1);
	        Colleague stud = DBoperation.addColleague(text.getText().toString());
	        adapter.add(stud);
	    }
	    public void deleteFirstUser(View view) {
	        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
	        Colleague coll = null;
	        if (getListAdapter().getCount() > 0) {
	            coll = (Colleague) getListAdapter().getItem(0);
	            DBoperation.deleteColleague(coll);
	            adapter.remove(coll);
	        }

	    }
	    @Override
	    protected void onResume() {
	       DBoperation.open();
	        super.onResume();
	    }
	    @Override
	    protected void onPause() {
	        DBoperation.close();
	        super.onPause();
	    }
	 


}
