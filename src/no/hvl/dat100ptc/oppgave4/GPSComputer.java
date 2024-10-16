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

				speeds[i - 1] = Math.round(speed * 10.0) / 10.0;

				System.out.println("Gjennomsnitt hastigheten mellom to punkter er: " + speed);

			} else {
				speeds[i - 1] = 0;
			}

		}

		return speeds;

	}

	public double maxSpeed() {

		double maxspeed = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			double distance = GPSUtils.distance(gpspoints[i - 1], gpspoints[i]);
			double time = gpspoints[i].getTime() - gpspoints[i - 1].getTime();

			if (time > 0) {
				double speed = distance / time;

				if (speed > maxspeed) {
					maxspeed = speed;
				}
			}
		}

		return Math.round(maxspeed * 10.0) / 10.0;
	}

	public double averageSpeed() {

		double totalDistance = totalDistance();
		int totalTime = totalTime();

		if (totalTime > 0) {

			return Math.round((totalDistance / totalTime) * 10.0) / 10.0;
		} else {
			return 0;
		}
	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23694;

	public double kcal(double weight, int secs, double speed) {

		double met = 0;
		double mph = speed * MS;

		if (mph < 10) {
			met = 4.0;
		} else if (mph >= 10 && mph <= 12) {
			met = 6.0;
		} else if (mph >= 12 && mph <= 14) {
			met = 8.0;
		} else if (mph >= 14 && mph <= 16) {
			met = 10.0;
		} else if (mph >= 16 && mph <= 20) {
			met = 12.0;
		} else {
			met = 16.0;

		}
		// konverter timer til sekunder
		double timer = secs / 3600.0;

		double kcal = met * weight * timer;

		return kcal;

	}

	public double totalKcal(double weight) {
		
		double totalKcal = 0.0;

		for (int i = 1; i < gpspoints.length; i++) {

			// beregner tiden mellom to gpspunkt
			double tidiSekunder = gpspoints[i].getTime() - gpspoints[i - 1].getTime();

			if (tidiSekunder <= 0) {
				continue;
			}

			double distanse = GPSUtils.distance(gpspoints[i - 1], gpspoints[i]);

			// beregne fart i meter per sekund
			double speedInMps = (distanse / tidiSekunder);

			double kcal = kcal(weight, (int) tidiSekunder, speedInMps);

			totalKcal += kcal;
		}


		return totalKcal;

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		int totalTime = totalTime();
		double totalDistance = totalDistance() / 1000.0; // Distance in km (assuming totalDistance() returns meters)
		double totalElevation = totalElevation(); // Total elevation in meters
		double maxSpeed = maxSpeed() * 3.6; // Convert from m/s to km/h
		double averageSpeed = averageSpeed() * 3.6; // Convert from m/s to km/h
		double totalKcal = totalKcal(WEIGHT); // Energy consumption in kcal

		String formattedTime = String.format("%02d:%02d:%02d", totalTime / 3600, (totalTime % 3600) / 60,
				totalTime % 60);
		// første gir timer. totaltime % 3600 gir oss resten av tiden i sekunder også
		// deler vi med 60 for å få minutt.

		System.out.println("==============================================");
		System.out.printf("Total Time     :   %s%n", formattedTime);
		System.out.printf("Total distance :   %8.2f km%n", totalDistance);
		System.out.printf("Total elevation:   %8.2f m%n", totalElevation);
		System.out.printf("Max speed      :   %8.2f km/t%n", maxSpeed);
		System.out.printf("Average speed  :   %8.2f km/t%n", averageSpeed);
		System.out.printf("Energy         :   %8.2f kcal%n", totalKcal);
		System.out.println("==============================================");
		


	}
}
