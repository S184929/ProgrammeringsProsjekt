package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);

		}
		return distance;

	}

	public double totalElevation() {

		double totalElevation = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			double elevationDifferanse = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();

			if (elevationDifferanse > 0) {
				totalElevation += elevationDifferanse;
			}

		}
		return totalElevation;

	}
	
	public int totalTime() {
		if (gpspoints.length < 2) {
		return 0;
	}
		
	int startTime = gpspoints[0].getTime();
	int endTime = gpspoints[gpspoints.length - 1].getTime();
	
	return endTime - startTime;
	}

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length - 1];

		for (int i = 1; i < gpspoints.length; i++) {

			double distanse = GPSUtils.distance(gpspoints[i - 1], gpspoints[i]);		 

			double time = gpspoints[i].getTime() - gpspoints[i - 1].getTime();

			if (time > 0) {
				double speed = (distanse / time);

				speeds[i - 1] = Math.round(speed*10.0)/10.0;
				
				System.out.println("Gjennomsnitt hastigheten mellom to punkter er: " + speed);
				
			} else {
				speeds[i - 1] = 0;
			}
			
		}
		
		return speeds;

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO
		throw new UnsupportedOperationException(TODO.method());

	}

	public double averageSpeed() {

		double average = 0;

		// TODO
		throw new UnsupportedOperationException(TODO.method());

	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;
		double speedmph = speed * MS;

		// TODO
		throw new UnsupportedOperationException(TODO.method());

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		throw new UnsupportedOperationException(TODO.method());

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		// TODO
		throw new UnsupportedOperationException(TODO.method());

	}

}
