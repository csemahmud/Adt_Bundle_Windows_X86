package com.mahmud.sqliteloginapp1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Display extends Activity {
	
	private Button closeButton;
	private TextView usernameDisplaytextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		String username = getIntent().getStringExtra("username");
		closeButton = (Button) findViewById(R.id.closeButton);
		usernameDisplaytextView = (TextView) findViewById(R.id.usernameDisplaytextView);
		usernameDisplaytextView.setText(username);
		closeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Display.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}

}
