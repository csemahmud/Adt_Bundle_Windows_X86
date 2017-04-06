package com.mahmud.sqliteloginapp1;

import com.mahmud.sqliteloginapp1.classes.ContactDAO;
import com.mahmud.sqliteloginapp1.classes.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
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
public class SignUp extends Activity {
	
	private Button signButtonbutton;
	private EditText nameEditText, emailEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
	private DatabaseHelper dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		signButtonbutton = (Button) findViewById(R.id.signButtonbutton);
		nameEditText = (EditText) findViewById(R.id.nameEditText);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
		
		dbhelper = new DatabaseHelper(this);
		
		signButtonbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				String name = nameEditText.getText().toString();
				String email = emailEditText.getText().toString();
				String username = usernameEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				String confirmPassword = confirmPasswordEditText.getText().toString();
				
				if(username.equals("")){
					Toast.makeText(getApplicationContext(), 
							"Username is Required", 
							Toast.LENGTH_LONG).show();
					return;
				} else if(password.equals("")){
					Toast.makeText(getApplicationContext(), 
							"Password is Required", 
							Toast.LENGTH_LONG).show();
					return;
				}
				
				if(password.equals(confirmPassword)){
					ContactDAO contact = new ContactDAO(
							name, email, username, password);
					if(dbhelper.insertContact(contact) > 0){
						Toast.makeText(getApplicationContext(), 
								contact.toString(), 
								Toast.LENGTH_LONG).show();
						Toast.makeText(getApplicationContext(), 
								"Contact Has Been Saved Successfully", 
								Toast.LENGTH_LONG).show();
						SignUp.this.finish();
					}
				} else {
					Toast.makeText(getApplicationContext(), 
							"Password Don't Match", 
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

}
