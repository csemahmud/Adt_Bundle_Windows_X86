package com.mahmud.sqliteloginapp1;

import com.mahmud.sqliteloginapp1.classes.DatabaseHelper;

import android.os.Bundle;
import android.app.Activity;
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
public class MainActivity extends Activity {
	
	private Button loginButton, goSignUpButton;
	private EditText userLogInEditText, passLogInEditText;
	private DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        loginButton = (Button) findViewById(R.id.loginButton);
        goSignUpButton = (Button) findViewById(R.id.goSignUpButton);
        userLogInEditText = (EditText) findViewById(R.id.userLogInEditText);
        passLogInEditText = (EditText) findViewById(R.id.passLogInEditText);
        dbhelper = new DatabaseHelper(this);
        
        loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				String username = userLogInEditText.getText().toString();
				String password = passLogInEditText.getText().toString();
				
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
				
				if(dbhelper.checkPass(username).equals(password)){
					Intent goToDisplayActivity = new Intent(MainActivity.this, Display.class);
					goToDisplayActivity.putExtra("username", username);
					startActivity(goToDisplayActivity);
				} else {
					Toast.makeText(getApplicationContext(), 
							"username or password is incorrect", Toast.LENGTH_LONG).show();
				}
				
			}
		});
        
        goSignUpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent goToSignUpActivity = new Intent(MainActivity.this, SignUp.class);
				startActivity(goToSignUpActivity);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
