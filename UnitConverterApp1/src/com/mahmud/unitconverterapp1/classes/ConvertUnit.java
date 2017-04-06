/**
 * 
 */
package com.mahmud.unitconverterapp1.classes;

/**
 * @author Mahmudul Hasan Khan CSE
 *
 */
public class ConvertUnit {

	/**
	 * 
	 */
	public ConvertUnit() {
		// TODO Auto-generated constructor stub
	}
	
	public static double metreToCentiMetre(double metre){
		return metre*100;
	}
	
	public static double centiMetreToMetre(double centiMetre){
		return centiMetre/metreToCentiMetre(1);
	}

	public static double footToInch(double foot){
		return foot*12;
	}
	
	public static double inchToFoot(double inch){
		return inch/footToInch(1);
	}
	
	public static double inchToCentiMetre(double inch){
		return inch*2.54;
	}
	
	public static double centiMetreToInch(double centiMetre){
		return centiMetre/inchToCentiMetre(1);
	}
	
	public static double footToCentiMetre(double foot){
		return inchToCentiMetre(footToInch(foot));
	}
	
	public static double centiMetreToFoot(double centiMetre){
		return centiMetre/footToCentiMetre(1);
	}
	
	public static double metreToInch(double metre){
		return centiMetreToInch(metreToCentiMetre(metre));
	}
	
	public static double inchToMetre(double inch){
		return inch/metreToInch(1);
	}
	
	public static double metreToFoot(double metre){
		return centiMetreToFoot(metreToCentiMetre(metre));
	}
	
	public static double footToMetre(double foot){
		return foot/metreToFoot(1);
	}
	
	public static double kiloGramToGram(double kiloGram){
		return kiloGram*1000; 
	}
	
	public static double gramToKiloGram(double gram){
		return gram/gramToKiloGram(1);
	}
	
	public static double poundToGram(double pound){
		return pound*453.5923703803783;
	}
	
	public static double gramToPound(double gram){
		return gram/poundToGram(1);
	}
	
	public static double kiloGramToPound(double kiloGram){
		return gramToPound(kiloGramToGram(kiloGram));
	}
	
	public static double poundToKiloGram(double pound){
		return pound/kiloGramToPound(1);
	}
	
	public static double minuteToSecond(double minute){
		return minute*60;
	}
	
	public static double secondToMinute(double second){
		return second/minuteToSecond(1);
	}
	
	public static double hourToMinute(double hour){
		return hour*60;
	}
	
	public static double minuteToHour(double minute){
		return minute/hourToMinute(1);
	}
	
	public static double hourToSecond(double hour){
		return minuteToSecond(hourToMinute(hour));
	}
	
	public static double secondToHour(double second){
		return second/hourToSecond(1);
	}
	
	public static double celsiusToKelvin(double celsius){
		return celsius + 273.15;
	}
	
	public static double kelvinToCelsius(double kelvin){
		return kelvin - celsiusToKelvin(0);
	}
	
	public static double celsiusToFarenheit(double celsius){
		return celsius*9/5 + 32;
	}
	
	public static double farenheitToCelsius(double farenheit){
		return (farenheit - 32)*5/9;
	}
	
	public static double kelvinToFarenheit(double kelvin){
		return celsiusToFarenheit(kelvinToCelsius(kelvin));
	}
	
	public static double farenheitToKelvin(double farenheit){
		return celsiusToKelvin(farenheitToCelsius(farenheit));
	}
	
	public static double kiloWattToWatt(double kiloWatt){
		return kiloWatt*1000;
	}
	
	public static double wattToKiloWatt(double watt){
		return watt/kiloWattToWatt(1);
	}
	
	public static double horsePowerToWatt(double horsePower){
		return horsePower*745.699872;
	}
	
	public static double wattToHorsePower(double watt){
		return watt/horsePowerToWatt(1);
	}
	
	public static double kiloWattToHorsePower(double kiloWatt){
		return wattToHorsePower(kiloWattToWatt(kiloWatt));
	}
	
	public static double horsePowerToKilowatt(double horsePower){
		return horsePower/kiloWattToHorsePower(1);
	}

}
