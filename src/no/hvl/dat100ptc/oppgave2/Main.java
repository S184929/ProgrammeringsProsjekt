package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	public static void main(String[] args) {

		final String TIMESTR = "2017-08-13T08:52:26.000";
		final String LATSTR = "60.385390";
		final String LONGSTR = "5.217217";
		final String ELEVSTR = "61.9";

		System.out.println("Antall sekunder er: " + GPSDataConverter.toSeconds(TIMESTR) + "\n");

		System.out.println(GPSDataConverter.convert(TIMESTR, LATSTR, LONGSTR, ELEVSTR));

		GPSData gpsData = new GPSData(3);

		gpsData.insert("2017-08-13T08:52:26.000Z", "60.385390", "5.217217", "61.9");
		gpsData.insert("2017-08-13T08:53:00.000Z", "60.385588", "5.217857", "56.2");
		gpsData.insert("2017-08-13T08:54:00.000Z", "60.385700", "5.218000", "58.0");

		gpsData.print();
	}
}
