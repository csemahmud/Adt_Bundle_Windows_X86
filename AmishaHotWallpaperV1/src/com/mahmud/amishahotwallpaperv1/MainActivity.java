package com.mahmud.amishahotwallpaperv1;

import android.os.Bundle;
import android.app.Activity;
//import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.Toast;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class MainActivity extends Activity {
	
	private Button closeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		closeButton = (Button) findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		
		/*DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		
		Toast.makeText(getApplicationContext(), 
				"Display Screen Heaght = " + height
				+ "\nAnd Width = " + width, 
				Toast.LENGTH_LONG).show();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
