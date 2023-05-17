package damian.hms.application;

import java.io.Serializable;

import damian.hms.constants.HmsGuiConstants;
import damian.hms.database.HmsDatabase;

public class Hospital implements Serializable{
	public static String hospitalName = HmsGuiConstants.HOSPITAL_NAME;
	public static String hospitalAddress = HmsGuiConstants.HOSPITAL_ADDRESS;
	public static HmsDatabase db = new HmsDatabase(true);
	
}
