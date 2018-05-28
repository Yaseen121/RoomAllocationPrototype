package roomallocationprototype;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;

public class RoomBookings {
	private SimpleStringProperty roomName, sevenAM, eightAM, nineAM, tenAM, elevenAM, twelvePM, onePM, twoPM, threePM, fourPM, fivePM, sixPM, sevenPM;

	public RoomBookings(String rN, String sAM, String eAM, String nAM, String tAM, String elAM, String twPM, String oPM, String twoPM, String thPM, String fPM, String fvPM, String sxPM, String sPM) {
		roomName = new SimpleStringProperty(rN);
		sevenAM = new SimpleStringProperty(sAM);
		eightAM  = new SimpleStringProperty(eAM);
		nineAM  = new SimpleStringProperty(nAM);
		tenAM  = new SimpleStringProperty(tAM);
		elevenAM  = new SimpleStringProperty(elAM);
		twelvePM  = new SimpleStringProperty(twPM);
		onePM  = new SimpleStringProperty(oPM);
		this.twoPM  = new SimpleStringProperty(twoPM);
		threePM  = new SimpleStringProperty(thPM);
		fourPM  = new SimpleStringProperty(fPM);
		fivePM  = new SimpleStringProperty(fvPM);
		sixPM  = new SimpleStringProperty(sxPM);
		sevenPM  = new SimpleStringProperty(sPM);
	}
	
	public RoomBookings(ArrayList<String> arr ) {
		roomName = new SimpleStringProperty(arr.get(0));
		sevenAM = new SimpleStringProperty(arr.get(1));
		eightAM  = new SimpleStringProperty(arr.get(2));
		nineAM  = new SimpleStringProperty(arr.get(3));
		tenAM  = new SimpleStringProperty(arr.get(4));
		elevenAM  = new SimpleStringProperty(arr.get(5));
		twelvePM  =new SimpleStringProperty(arr.get(6));
		onePM  = new SimpleStringProperty(arr.get(7));
		twoPM  = new SimpleStringProperty(arr.get(8));
		threePM  = new SimpleStringProperty(arr.get(9));
		fourPM  = new SimpleStringProperty(arr.get(10));
		fivePM  = new SimpleStringProperty(arr.get(11));
		sixPM  = new SimpleStringProperty(arr.get(12));
		sevenPM  = new SimpleStringProperty(arr.get(13));
	}
	
	public String getRoomName() {
		return roomName.get();
	}

	public String getSevenAM() {
		return sevenAM.get();
	}

	public String getEightAM() {
		return eightAM.get();
	}

	public String getNineAM() {
		return nineAM.get();
	}

	public String getTenAM() {
		return tenAM.get();
	}

	public String getElevenAM() {
		return elevenAM.get();
	}

	public String getTwelvePM() {
		return twelvePM.get();
	}

	public String getOnePM() {
		return onePM.get();
	}

	public String getTwoPM() {
		return twoPM.get();
	}

	public String getThreePM() {
		return threePM.get();
	}

	public String getFourPM() {
		return fourPM.get();
	}

	public String getFivePM() {
		return fivePM.get();
	}

	public String getSixPM() {
		return sixPM.get();
	}

	public String getSevenPM() {
		return sevenPM.get();
	}
	
	
}
