package com.mahmud.unitconverterapp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mahmud.unitconverterapp1.classes.ConvertUnit;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.text.method.KeyListener;
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
public class MainActivity extends Activity {
	
	private Spinner conversionTypespinner, convertFromSpinner,
			convertToSpinner;
	
	private EditText convertFromEditText, convertToEditText;
	
	private Button convertButton, switchButton, resetButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		conversionTypespinner = (Spinner) findViewById(R.id.conversionTypespinner);
		convertFromSpinner = (Spinner) findViewById(R.id.convertFromSpinner);
		convertToSpinner = (Spinner) findViewById(R.id.convertToSpinner);

		convertFromEditText = (EditText) findViewById(R.id.convertFromEditText);
		convertToEditText = (EditText) findViewById(R.id.convertToEditText);

		convertButton = (Button) findViewById(R.id.convertButton);
		switchButton = (Button) findViewById(R.id.switchButton);
		resetButton = (Button) findViewById(R.id.resetButton);
		
		convertToEditText.setKeyListener((KeyListener) convertToEditText.getTag());
		
		conversionTypespinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				convertFromSpinner.setAdapter(null);
				convertToSpinner.setAdapter(null);
				
				List<String> unitList; 
				
				Resources res = getResources();
				
				switch (position) {
				case 0:
					unitList = new ArrayList<String>(Arrays.asList(
							res.getStringArray(R.array.length_units)));
					break;
				case 1:
					unitList = new ArrayList<String>(Arrays.asList(
							res.getStringArray(R.array.mass_units)));
					break;
				case 2:
					unitList = new ArrayList<String>(Arrays.asList(
							res.getStringArray(R.array.time_units)));
					break;
				case 3:
					unitList = new ArrayList<String>(Arrays.asList(
							res.getStringArray(R.array.temperature_units)));
					break;
				case 4:
					unitList = new ArrayList<String>(Arrays.asList(
							res.getStringArray(R.array.power_units)));
					break;

				default:
					unitList = new ArrayList<String>();
					break;
				}
				
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
						android.R.layout.simple_spinner_dropdown_item, unitList);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				convertFromSpinner.setAdapter(dataAdapter);
				convertToSpinner.setAdapter(dataAdapter);
				convertToSpinner.setSelection(1);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		convertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				double input, result;
				result = 0;
				try{
					input = Double.parseDouble(convertFromEditText.getText().toString());
					if(convertFromSpinner.getSelectedItem().equals("Metre")){
						if(convertToSpinner.getSelectedItem().equals("Foot")) {
							result = ConvertUnit.metreToFoot(input);
						} else if (convertToSpinner.getSelectedItem().equals("Centi-Metre")) {
							result = ConvertUnit.metreToCentiMetre(input);
						} else if (convertToSpinner.getSelectedItem().equals("Inch")) {
							result = ConvertUnit.metreToInch(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Foot")) {
						if(convertToSpinner.getSelectedItem().equals("Metre")) {
							result = ConvertUnit.footToMetre(input);
						} else if (convertToSpinner.getSelectedItem().equals("Centi-Metre")) {
							result = ConvertUnit.footToCentiMetre(input);
						} else if (convertToSpinner.getSelectedItem().equals("Inch")) {
							result = ConvertUnit.footToInch(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Centi-Metre")) {
						if(convertToSpinner.getSelectedItem().equals("Metre")) {
							result = ConvertUnit.centiMetreToMetre(input);
						} else if (convertToSpinner.getSelectedItem().equals("Foot")) {
							result = ConvertUnit.centiMetreToFoot(input);
						} else if (convertToSpinner.getSelectedItem().equals("Inch")) {
							result = ConvertUnit.centiMetreToInch(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Inch")) {
						if(convertToSpinner.getSelectedItem().equals("Metre")) {
							result = ConvertUnit.inchToMetre(input);
						} else if (convertToSpinner.getSelectedItem().equals("Foot")) {
							result = ConvertUnit.inchToFoot(input);
						} else if (convertToSpinner.getSelectedItem().equals("Centi-Metre")) {
							result = ConvertUnit.inchToCentiMetre(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Kilo-Gram")) {
						if(convertToSpinner.getSelectedItem().equals("Pound")) {
							result = ConvertUnit.kiloGramToPound(input);
						} else if (convertToSpinner.getSelectedItem().equals("Gram")) {
							result = ConvertUnit.kiloGramToGram(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Pound")) {
						if(convertToSpinner.getSelectedItem().equals("Kilo-Gram")) {
							result = ConvertUnit.poundToKiloGram(input);
						} else if (convertToSpinner.getSelectedItem().equals("Gram")) {
							result = ConvertUnit.poundToGram(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Gram")) {
						if(convertToSpinner.getSelectedItem().equals("Kilo-Gram")) {
							result = ConvertUnit.gramToKiloGram(input);
						} else if (convertToSpinner.getSelectedItem().equals("Pound")) {
							result = ConvertUnit.gramToPound(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Second")) {
						if(convertToSpinner.getSelectedItem().equals("Minute")) {
							result = ConvertUnit.secondToMinute(input);
						} else if (convertToSpinner.getSelectedItem().equals("Hour")) {
							result = ConvertUnit.secondToHour(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Minute")) {
						if(convertToSpinner.getSelectedItem().equals("Second")) {
							result = ConvertUnit.minuteToSecond(input);
						} else if (convertToSpinner.getSelectedItem().equals("Hour")) {
							result = ConvertUnit.minuteToHour(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Hour")) {
						if(convertToSpinner.getSelectedItem().equals("Second")) {
							result = ConvertUnit.hourToSecond(input);
						} else if (convertToSpinner.getSelectedItem().equals("Minute")) {
							result = ConvertUnit.hourToMinute(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Kelvin")) {
						if(convertToSpinner.getSelectedItem().equals("Celsius")) {
							result = ConvertUnit.kelvinToCelsius(input);
						} else if (convertToSpinner.getSelectedItem().equals("Farenheit")) {
							result = ConvertUnit.kelvinToFarenheit(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Celsius")) {
						if(convertToSpinner.getSelectedItem().equals("Kelvin")) {
							result = ConvertUnit.celsiusToKelvin(input);
						} else if (convertToSpinner.getSelectedItem().equals("Farenheit")) {
							result = ConvertUnit.celsiusToFarenheit(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Farenheit")) {
						if(convertToSpinner.getSelectedItem().equals("Kelvin")) {
							result = ConvertUnit.farenheitToKelvin(input);
						} else if (convertToSpinner.getSelectedItem().equals("Celsius")) {
							result = ConvertUnit.farenheitToCelsius(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Watt")) {
						if(convertToSpinner.getSelectedItem().equals("Hourse-Power")) {
							result = ConvertUnit.wattToHorsePower(input);
						} else if (convertToSpinner.getSelectedItem().equals("Kilo-Watt")) {
							result = ConvertUnit.wattToKiloWatt(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Hourse-Power")) {
						if(convertToSpinner.getSelectedItem().equals("Watt")) {
							result = ConvertUnit.horsePowerToWatt(input);
						} else if (convertToSpinner.getSelectedItem().equals("Kilo-Watt")) {
							result = ConvertUnit.horsePowerToKilowatt(input);
						}
					} else if (convertFromSpinner.getSelectedItem().equals("Kilo-Watt")) {
						if(convertToSpinner.getSelectedItem().equals("Watt")) {
							result = ConvertUnit.kiloWattToWatt(input);
						} else if (convertToSpinner.getSelectedItem().equals("Hourse-Power")) {
							result = ConvertUnit.kiloWattToHorsePower(input);
						}
					} else {
						result = input;
					}
				} catch (NumberFormatException ex) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), 
							ex.getMessage(), 
							Toast.LENGTH_LONG).show();
				}
				
				convertToEditText.setText(String.valueOf(result));
				Toast.makeText(getApplicationContext(), 
						"Output :\n" + result, 
						Toast.LENGTH_LONG).show();
			}
		});
		
		switchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				int position = convertFromSpinner.getSelectedItemPosition();
				convertFromSpinner.setSelection(convertToSpinner.getSelectedItemPosition());
				convertToSpinner.setSelection(position);
			}
		});
		
		resetButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				conversionTypespinner.setSelection(0);
				convertFromSpinner.setSelection(0);
				convertToSpinner.setSelection(1);
				convertFromEditText.setText("");
				convertToEditText.setText("");
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
