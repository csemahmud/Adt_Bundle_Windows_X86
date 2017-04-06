package com.mahmud.contactlistapp1;

import java.util.List;

import com.mahmud.contactlistapp1.classes.ContactDAO;
import com.mahmud.contactlistapp1.classes.DatabaseHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class ManageContacts extends Activity {
	
	private Spinner selectContactSpinner;
	
	private EditText nameUpdateEditText, emailUpdateEditText,
				phoneUpdateEditText, addressUpdateEditText;
	
	private Button updateButton, deleteButton, backButton;
	
	private DatabaseHelper dbhelper;
	
	private Builder confirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_contacts);
		
		selectContactSpinner = (Spinner) findViewById(R.id.selectContactSpinner);
		
		nameUpdateEditText = (EditText) findViewById(R.id.nameUpdateEditText);
		emailUpdateEditText = (EditText) findViewById(R.id.emailUpdateEditText);
		phoneUpdateEditText = (EditText) findViewById(R.id.phoneUpdateEditText);
		addressUpdateEditText = (EditText) findViewById(R.id.addressUpdateEditText);
		
		deleteButton = (Button) findViewById(R.id.deleteButton);
		updateButton = (Button) findViewById(R.id.updateButton);
		backButton = (Button) findViewById(R.id.backButton);
		
		confirm = new Builder(this);
		
		dbhelper = new DatabaseHelper(this);
		setContactItemsToSpinner();
		
		selectContactSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				ContactDAO contact = (ContactDAO) parent.getItemAtPosition(position);
				nameUpdateEditText.setText(contact.getName());
				emailUpdateEditText.setText(contact.getEmail());
				phoneUpdateEditText.setText(contact.getPhone());
				addressUpdateEditText.setText(contact.getAddress());
			}

			@Override
			public void onNothingSelected(AdapterView<?> view) {
				// TODO Auto-generated method stub
				
			}
		});
		
		updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				ContactDAO contact = (ContactDAO) selectContactSpinner.getSelectedItem();
				if(contact != null){
					contact.setName(nameUpdateEditText.getText().toString());
					contact.setEmail(emailUpdateEditText.getText().toString());
					contact.setPhone(phoneUpdateEditText.getText().toString());
					contact.setAddress(addressUpdateEditText.getText().toString());
					if(dbhelper.updateContactByID(contact) > 0){
						Toast.makeText(getApplicationContext(), 
								"Name : " + contact.getName()
								+ "\nEmail : " + contact.getEmail()
								+ "\nPhone : " + contact.getPhone()
								+ "\nAddress : " + contact.getAddress(), 
								Toast.LENGTH_LONG).show();
						Toast.makeText(getApplicationContext(), 
								"Contact Has Been UPDATED Successfully", 
								Toast.LENGTH_LONG).show();
						setContactItemsToSpinner();
					}
				} else {
					Toast.makeText(getApplicationContext(), 
							"No Contact to Edit", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				final ContactDAO contact = (ContactDAO) selectContactSpinner.getSelectedItem();
				if(contact != null){
					confirm.setTitle("Alert")
					.setMessage("Are You Sure, you want to delete " + contact + " ?")
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		
					    public void onClick(DialogInterface dialog, int whichButton) {
					    	if(dbhelper.deleteContactByID(contact.getContactID()) > 0){
								Toast.makeText(getApplicationContext(), 
										"Name : " + contact.getName()
										+ "\nEmail : " + contact.getEmail()
										+ "\nPhone : " + contact.getPhone()
										+ "\nAddress : " + contact.getAddress(), 
										Toast.LENGTH_LONG).show();
								Toast.makeText(getApplicationContext(), 
										"Contact : " + contact +
												" Has Been DELETED Successfully", 
										Toast.LENGTH_LONG).show();
								setContactItemsToSpinner();
							}
					    }})
					 .setNegativeButton(android.R.string.no, null).show();
				} else {
					Toast.makeText(getApplicationContext(), 
							"No Contact to Delete", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View viem) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ManageContacts.this, MainActivity.class));
				ManageContacts.this.finish();
			}
		});
	}

	private void setContactItemsToSpinner() {
		// TODO Auto-generated method stub
		nameUpdateEditText.setText("");
		emailUpdateEditText.setText("");
		phoneUpdateEditText.setText("");
		addressUpdateEditText.setText("");
		selectContactSpinner.setAdapter(null);
		List<ContactDAO> contactList = dbhelper.selectAllContacts();
		ArrayAdapter<ContactDAO> dataAdapter = new ArrayAdapter<ContactDAO>(this,
			android.R.layout.simple_list_item_1, contactList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
		selectContactSpinner.setAdapter(dataAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_contacts, menu);
		return true;
	}

}
