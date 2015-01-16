package ie.gmit.computing;

import java.util.List;
import com.activeandroid.ActiveAndroid;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class TreeStructure extends Activity implements View.OnClickListener{
	//private static final int ID = 0;
	LinearLayout container = null;
	final Context context = this;
	
	Node currentNode = new Node("Start");
		Node solid = new Node("Solid",currentNode);
			Node hard = new Node("Hard",solid);
				Node smoothedge = new Node("Smooth Edge",hard);
					Node resinpellet  = new Node("Resin Pellet",smoothedge);
					    Node cylindrical = new Node("Cylindrical",resinpellet);
						    Node longcyl = new Node("Long",cylindrical);
						    Node flatcyl = new Node("Flat",cylindrical);
					    Node rounded  = new Node("Rounded",resinpellet);
						    Node oval  = new Node("Oval",rounded);
						    Node sphere  = new Node("Sphere",rounded);

			    Node iregularedge = new Node("Iregular Edge",hard);
					  Node fraglay  = new Node("Fragments",iregularedge);
						  Node edge = new Node("Edges",fraglay);
							  Node allangul = new Node("All angular",edge);
							  Node moangul = new Node("Most angular",edge);
							  Node allround = new Node("All raunded",edge);
							  Node moround = new Node("Most raunded",edge);
							  
		Node flex = new Node("Flexible",currentNode);
			Node filma = new Node("Filmatous",currentNode);
				  Node flor = new Node("Flore",currentNode);
				  
	  
	
	Node parentNode = null;
	boolean leafLevel = false;
	
	//NodeStructure str = new NodeStructure();
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActiveAndroid.initialize(this);
		
		currentNode.addChild(solid);
		solid.addChild(hard);
		hard.addChild(iregularedge);
		rounded.addChild(oval);
		resinpellet.addChild(rounded);
		resinpellet.addChild(cylindrical);
		smoothedge.addChild(resinpellet);
		hard.addChild(smoothedge);
		cylindrical.addChild(longcyl);
		cylindrical.addChild(flatcyl);
		fraglay.addChild(edge);
		hard.addChild(iregularedge);
		iregularedge.addChild(fraglay);
		edge.addChild(allangul);
		edge.addChild(allround);
		edge.addChild(moangul);
		edge.addChild(moround);
				
				  
		currentNode.addChild(flex);
			flex.addChild(filma);
				filma.addChild(flor);
		refff(currentNode);
	}

	private void refff(Node node) {
		setContentView(R.layout.activity_usertree);
		// TODO Auto-generated method stub
		Button backBtn = (Button) findViewById(R.id.btnBack);
		backBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				if(currentNode.getParent() != null){
						refff(currentNode.getParent());
					}
				}
			});
				
		Button addBtn = (Button) findViewById(R.id.btnAddBtn);
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptView = layoutInflater.inflate(R.layout.prompts, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptView);
                final EditText input = (EditText) promptView.findViewById(R.id.userInput);
                
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// TODO Auto-generated method stub
						Node n = new Node();
						Button b = new Button(getApplicationContext());
						n.setName(input.getText().toString());
						currentNode.addChild(n);
						//n.setParent(currentNode);
		            	b.setText(n.getName());
		            	container.addView(b);
		            	refff(currentNode);		            	
					}
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
			}
		});
		

		currentNode = node;
		container = (LinearLayout)findViewById(R.id.ButtonsAdded);// container
			if(currentNode.getChildren() != null){
				List<Node> children = currentNode.getChildren();
					for (int i = 0; i < children.size(); i++) {
					Button button = new Button(this);
					button.setText(children.get(i).getName().toString());
					button.setId(i);
					final int buttonID = button.getId();
					container.addView(button);
					
					button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						refff(currentNode.getChildren().get(buttonID));
					
					}
				});
			}
		
		}
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}

