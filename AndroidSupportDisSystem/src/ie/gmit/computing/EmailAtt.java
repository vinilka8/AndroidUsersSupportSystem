package ie.gmit.computing;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailAtt extends Activity implements View.OnClickListener{
	EditText editTextEmail, editTextSubject, editTextMessage;
    Button btnSend, btnAttachment;
    String email, subject, message, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    
 

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_email);
    	
	      editTextEmail = (EditText) findViewById(R.id.editTextTo);
	      editTextSubject = (EditText) findViewById(R.id.editTextSubject);
	      editTextMessage = (EditText) findViewById(R.id.editTextMessage);
	      btnAttachment = (Button) findViewById(R.id.buttonAttachment);
	      btnSend = (Button) findViewById(R.id.btnSend);
	      btnSend.setOnClickListener(this);
	      btnAttachment.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnAttachment) {
			openGallery();
			}
		
		  if (v == btnSend) {
	         try {
	        	 email = editTextEmail.getText().toString();
	        	 subject = editTextSubject.getText().toString();
	        	 message = editTextMessage.getText().toString();
	        	 final Intent emailIntent = new Intent(
	        			android.content.Intent.ACTION_SEND);
	        	 		emailIntent.setType("plain/text");
	        	 		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
	        	 		new String[] { email });
	        	 	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
	           if (URI != null) {
	                  emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
	           }
		           emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		           this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
	           } catch (Throwable t) {
	        	   	Toast.makeText(this,"Request failed try again: " + t.toString(),Toast.LENGTH_LONG).show();
		         }
		  }
	}

	private void openGallery() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("return-data", true);
		startActivityForResult(Intent.createChooser(intent, "Complete action using"),PICK_FROM_GALLERY);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
        cursor.moveToFirst();
        columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        attachmentFile = cursor.getString(columnIndex);
        Log.e("Attachment Path:", attachmentFile);
        URI = Uri.parse("file://" + attachmentFile);
        cursor.close();
        }

}
