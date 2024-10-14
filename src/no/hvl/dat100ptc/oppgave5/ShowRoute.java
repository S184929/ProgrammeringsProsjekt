package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		setColor(0,0,225);
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double long1 = gpspoints[i].getLongitude();
			double lat1 = gpspoints[i].getLatitude();
			double long2 = gpspoints[i+1].getLongitude();
			double lat2 = gpspoints[i+1].getLatitude();
			
			int x1 = MARGIN + (int) ((long1 - minlon) * xstep);
			int y1 = ybase - (int) ((lat1 - minlat) * ystep);
			int x2 = MARGIN + (int) ((long2 - minlon) * xstep);
			int y2 = ybase - (int) ((lat2 - minlat) * ystep);
			
			drawLine(x1, y1, x2, y2);
			fillCircle(x1, y1, 3);
			
		}
		
//		double lastLong = gpspoints[gpspoints.length - 1].getLongitude();
//	    double lastLat = gpspoints[gpspoints.length - 1].getLatitude();
//	    
//	    int lastX = MARGIN + (int) ((lastLong - minlon) * xstep);
//	    int lastY = ybase - (int) ((lastLat - minlat) * ystep);
//	    
//	    fillCircle(lastX, lastY, 3);
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		 // Startposisjon for statistikken
	    int xPosition = MARGIN;
	    int yPosition = MARGIN;
	    int lineSpacing = 20; // Avstand mellom linjene

	    // Hent statistikk fra gpscomputer-objektet
	    int totalTime = gpscomputer.totalTime();
	    double totalDistance = gpscomputer.totalDistance() / 1000; // Konverter til km
	    double totalElevation = gpscomputer.totalElevation();
	    double maxSpeed = gpscomputer.maxSpeed();
	    double averageSpeed = gpscomputer.averageSpeed();
	    double totalKcal = gpscomputer.totalKcal(80.0); // Anta en vekt på 80 kg

	    // Tegn statistikken i vinduet ved hjelp av drawString
	    setColor(0, 0, 0); // Sett fargen til svart for teksten
	    drawString("=== Statistikk ===", xPosition, yPosition);
	    drawString("Total tid: " + GPSUtils.formatTime(totalTime), xPosition, yPosition + lineSpacing);
	    drawString("Total distanse: " + String.format("%.2f", totalDistance) + " km", xPosition, yPosition + 2 * lineSpacing);
	    drawString("Total stigning: " + String.format("%.2f", totalElevation) + " m", xPosition, yPosition + 3 * lineSpacing);
	    drawString("Maks hastighet: " + String.format("%.2f", maxSpeed) + " km/t", xPosition, yPosition + 4 * lineSpacing);
	    drawString("Gjennomsnittlig hastighet: " + String.format("%.2f", averageSpeed) + " km/t", xPosition, yPosition + 5 * lineSpacing);
	    drawString("Forbrente kalorier: " + String.format("%.2f", totalKcal) + " kcal", xPosition, yPosition + 6 * lineSpacing);
		
	}

	public void replayRoute(int ybase) {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
