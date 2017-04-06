package com.mahmud.sqlitedbdemoapp1;

import java.util.List;

import com.mahmud.sqlitedbdemoapp1.classes.DataHandler;
import com.mahmud.sqlitedbdemoapp1.classes.PersonDAO;

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
public class MainActivity extends Activity {
	
	private Button saveButton, loadButton;
	private EditText nameEditText, emailEditText;
	private DataHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        saveButton = (Button) findViewById(R.id.saveButton);
        loadButton = (Button) findViewById(R.id.loadButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        handler = new DataHandler(getApplicationContext());
        
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				String name = nameEditText.getText().toString();
				String email = emailEditText.getText().toString();
				
				if(handler.insertData(name, email) > 0){
					Toast.makeText(getApplicationContext(), 
							"Name : " + name + "; Email : " + email, 
							Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(), 
							"Data has been saved successfully", 
							Toast.LENGTH_LONG).show();
				}
			}
		});
        
        loadButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				List<PersonDAO> personList = handler.returnData();
				if(personList.size() > 0){
					for (PersonDAO person : personList) {
						Toast.makeText(getApplicationContext(), 
								person.toString(), 
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(), 
							"No Data to Show", 
							Toast.LENGTH_LONG).show();
				}
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
