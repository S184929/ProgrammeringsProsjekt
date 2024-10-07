package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

			String timestr = "2017-08-13T08:52:26.000";
        
			int result = GPSDataConverter.toSeconds(timestr);
        
			System.out.println("Antall sekunder er: " + result);
		
	}
}
