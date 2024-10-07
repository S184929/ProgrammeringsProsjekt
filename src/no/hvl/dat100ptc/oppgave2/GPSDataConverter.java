package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int sek=Integer.parseInt(timestr.substring(17, 19));
		int min =Integer.parseInt(timestr.substring(14, 16));
		int hr=Integer.parseInt(timestr.substring(11, 13));
		
		int totalSekund = ((hr*3600)+(min*60)+sek);
		
		return totalSekund;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		int time= toSeconds(timeStr);
		
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
				
		return new GPSPoint(time, latitude, longitude, elevation);
		

		
	}
	
}
