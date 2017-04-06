package com.mahmud.contactlistapp1;

import com.mahmud.contactlistapp1.classes.ContactDAO;
import com.mahmud.contactlistapp1.classes.DatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class MainActivity extends Activity implements OnClickListener {
	
	private EditText nameEntryEditText, emailEntryEditText,
			phoneEntryEditText, addressEntryEditText;
	
	private Button saveButton, exitButton, manageButton,
			dropDBButton;
	
	private DatabaseHelper dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nameEntryEditText = (EditText) findViewById(R.id.nameEntryEditText);
		emailEntryEditText = (EditText) findViewById(R.id.emailEntryEditText);
		phoneEntryEditText = (EditText) findViewById(R.id.phoneEntryEditText);
		addressEntryEditText = (EditText) findViewById(R.id.addressEntryEditText);
		
		saveButton = (Button) findViewById(R.id.saveButton);
		exitButton = (Button) findViewById(R.id.exitButton);
		manageButton = (Button) findViewById(R.id.manageButton);
		dropDBButton = (Button) findViewById(R.id.dropDBButton);
		
		dbhelper = new DatabaseHelper(this);
		saveButton.setOnClickListener(this);
		exitButton.setOnClickListener(this);
		manageButton.setOnClickListener(this);
		dropDBButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		String name = nameEntryEditText.getText().toString();
		String email = emailEntryEditText.getText().toString();
		String phone = phoneEntryEditText.getText().toString();
		String address = addressEntryEditText.getText().toString();
		
		ContactDAO contact = new ContactDAO(name, email, phone, address);
		
		switch (view.getId()) {
		case R.id.saveButton:
			if(dbhelper.insertContact(contact) > 0){
				Toast.makeText(getApplicationContext(), 
						"Name : " + contact.getName()
						+ "\nEmail : " + contact.getEmail()
						+ "\nPhone : " + contact.getPhone()
						+ "\nAddress : " + contact.getAddress(), 
						Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), 
						"Contact Has Been SAVED Successfully", 
						Toast.LENGTH_LONG).show();
				nameEntryEditText.setText("");
				emailEntryEditText.setText("");
				phoneEntryEditText.setText("");
				addressEntryEditText.setText("");
			}
			break;
		case R.id.manageButton:
			startActivity(new Intent(MainActivity.this, ManageContacts.class));
			MainActivity.this.finish();
			break;
		case R.id.dropDBButton:
			new AlertDialog.Builder(this)
			.setTitle("Alert")
			.setMessage("Are You want to Drop Database of this App : "
					+ dbhelper.getDATABASE_NAME() + " ?")
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int whichButton) {
			    	getApplicationContext().deleteDatabase(dbhelper.getDATABASE_NAME());
			    	Toast.makeText(getApplicationContext(), 
							"Database : " + dbhelper.getDATABASE_NAME() 
							+ " has been DROPPED successfully", 
							Toast.LENGTH_LONG).show();
			    	MainActivity.this.finish();
			    }})
			 .setNegativeButton(android.R.string.no, null).show();
			break;
		case R.id.exitButton:
			new AlertDialog.Builder(this)
				.setTitle("Alert")
				.setMessage("Do you really want to Exit ?")
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	
				    public void onClick(DialogInterface dialog, int whichButton) {
				    	MainActivity.this.finish();
				    }})
				 .setNegativeButton(android.R.string.no, null).show();
			break;

		default:
			Toast.makeText(getApplicationContext(), 
					"Unknown Item has been clicked", 
					Toast.LENGTH_LONG).show();
			break;
		}
	}

}
