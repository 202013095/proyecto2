package modelo;

import java.util.ArrayList;

public class Reserva extends Fecha{
	
	private boolean checkIn;
	private ArrayList<String> listaHuespedes;
	
	public Reserva(String fechaInit, String fechaFinit, boolean checkIn, ArrayList<String> listaHuespedes) {
		super(fechaInit, fechaFinit);		
		this.checkIn = checkIn;
		this.listaHuespedes = listaHuespedes;
	}

	public boolean getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	public ArrayList<String> getListaHuespedes() {
		return listaHuespedes;
	}

	public void setListaHuespedes(ArrayList<String> listaHuespedes) {
		this.listaHuespedes = listaHuespedes;
	}
	
	

}
