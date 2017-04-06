package com.mahmud.spinnerapp1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Spinner girlfriendSpinner, carSpinner;
	private Button showButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		girlfriendSpinner = (Spinner) findViewById(R.id.girlfriendSpinner);
		carSpinner = (Spinner) findViewById(R.id.carSpinner);
		showButton = (Button) findViewById(R.id.showButton);
		
		List<Car> carList = new ArrayList<Car>();
		carList.add(new Car("Lamborghini", "Red", 55000000));
		carList.add(new Car("Proshe", "Silver", 45000000));
		carList.add(new Car("Mclaren F1", "Blue", 75000000));
		
		ArrayAdapter<Car> dataAdapter = new ArrayAdapter<Car>(this,
				android.R.layout.simple_spinner_dropdown_item, carList);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			carSpinner.setAdapter(dataAdapter);
			
		girlfriendSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), 
						"Your Girl Friend is : " + parent.getItemAtPosition(pos) , 
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		carSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), 
						"Your Car is : " + parent.getItemAtPosition(pos) , 
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		showButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Toast.makeText(MainActivity.this,
			                "Your Girl Friend is : "+ String.valueOf(girlfriendSpinner.getSelectedItem()) +
			                "\nAnd Your Car is : "+ String.valueOf(carSpinner.getSelectedItem()),
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class Car{
		private String CarName;
		private String Color;
		private double Price;
		
		public Car(String carName, String color, double price) {
			this();
			CarName = carName;
			Color = color;
			Price = price;
		}
		
		public Car() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return CarName + "[ Color=" + Color + ", Price="
					+ Price + "]";
		}
	}

}
