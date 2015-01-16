package ie.gmit.computing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UsersDisTree extends Activity implements View.OnClickListener{
	LinearLayout container=null;
	Button root;
	Node curroot = new Node("start",null);
	final Context context = this;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		container=(LinearLayout)findViewById(R.id.layoutAddederBTN);// container
		Button button = (Button) findViewById(R.id.addButtons);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);
                final EditText input = (EditText) promptView.findViewById(R.id.userInput);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get user input and set it to result
                        //editTextMainScreen.setText(input.getText());
                    	Log.i("input", input.getText().toString());
                    	Node n = new Node(input.getText().toString(), curroot);
                    	Button b = new Button(UsersDisTree.this);
                    	
                    	//n.setName(input.toString());
                    	curroot.addChild(n);
                    	b.setText(n.getName());
                    	container.addView(b);
                    	
                    	
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
			}
        });

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}	
}

