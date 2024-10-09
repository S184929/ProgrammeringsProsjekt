package no.hvl.dat100ptc.oppgave3;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static void main(String[] args) {

		System.out.println(formatTime(9));
		System.out.println(formatTime(82));
		System.out.println(formatTime(60 * 60 * 3 + 60 * 2 + 1));

		System.out.println(formatDouble(1.346));
		
		GPSPoint g1 = new GPSPoint(0,60.385390, 5.217217,0);
		GPSPoint g2 = new GPSPoint(10,60.376988, 5.227082,0);


        // Kall på speed-metoden for å beregne hastighet
        double hastighet = speed(g1, g2);

        System.out.println("Hastighet er: " + String.format("%.0f", hastighet) + " m/s");
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

		double lat1 = Math.toRadians(gpspoint1.getLatitude());
		double long1 = Math.toRadians(gpspoint1.getLongitude());
		double lat2 = Math.toRadians(gpspoint2.getLatitude());
		double long2 = Math.toRadians(gpspoint2.getLongitude());

		// Beregner differanse i breddegrad og lengdegrad
		double latDifferanse = lat2 - lat1;
		double longDifferanse = long2 - long1;

		// beregn avstand mellom to punker
		double a = compute_a(lat1, lat2, latDifferanse, longDifferanse);

		double c = compute_c(a);
		
		double distanse = R*c;
		System.out.println("avstanden mellom punktene er: " + distanse);
		
		return distanse;

	}

	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		return Math.pow(Math.sinh(deltaphi / 2), 2)
				+ (Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltadelta), 2));

	}

	private static double compute_c(double a) {
		return 2 * (Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		

		double distanse = distance(gpspoint1, gpspoint2);
		
		double tid=1;
		
		double hastighet=distanse/tid;
		
		
		return hastighet;

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
