package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import static java.lang.Object.*;
import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {
	
	public static void main(String[] args) {
	
		System.out.println(formatTime(9));
		System.out.println(formatTime(82));
		System.out.println(formatTime(60 * 60 * 3 + 60 * 2 + 1));
		
		System.out.println(formatDouble(1.346));
		
		
	}

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {

		double min; 
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
	        latitudes[i] = gpspoints[i].getLatitude();
	    }
		System.out.println(latitudes);
		  return latitudes;
		  
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		
		double[] longitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
	        longitudes[i] = gpspoints[i].getLongitude();
	    }
		System.out.println(longitudes);
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		throw new UnsupportedOperationException(TODO.method());

		// TODO 
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO 

	}

	private static double compute_c(double a) {

		
		throw new UnsupportedOperationException(TODO.method());
		
		
		// TODO 

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO

	}

	public static String formatTime(int secs) {

		int timer = secs / 3600;
		int minutter = (secs % 3600) / 60;
		int sekunder = secs % 60;
		
		
		String tidStreng = String.format("%02d:%02d:%02d", timer, minutter, sekunder);
		
		String formatTid = String.format("%10s", tidStreng);
		
		return formatTid;
			
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String tallStreng = String.format(Locale.ENGLISH, "%.2f", d);
		
		String formatTall = String.format("%" + TEXTWIDTH + "s", tallStreng);
		
		return formatTall;
		
	}
}
