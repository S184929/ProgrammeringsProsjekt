package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		gpspoints= new GPSPoint[n];
		antall=0;
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		if (antall<gpspoints.length) {
			gpspoints[antall]= gpspoint;
			antall++;
			return true;
		}
		return false;
				
			
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		throw new UnsupportedOperationException(TODO.method());

		// TODO 
		
	}

	public void print() {

		throw new UnsupportedOperationException(TODO.method());

		// TODO 
	}
}
