package Procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Adicion;
import modelo.Administrador;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Usuario;

	public class Hotel
	{
		/*Attributes*/
		
		private Map <String, Habitacion> habitaciones;
		private Map <String, Usuario> usuario;
		private Map <String, Huesped> huesped;
		/*Constructors*/
		
		public Hotel () throws IOException
		{
			this.habitaciones = Loader.cargarHabitaciones();
			this.usuario = Loader.cargarUsuarios();
			this.huesped= Loader.cargarHuespedes();
			Loader.cargarReservas(habitaciones, huesped);			
		}
		
		
		public Map<String, Usuario> getUsuario() {
			return usuario;
		}

		public void setUsuario(Map<String, Usuario> usuario) {
			this.usuario = usuario;
		}			

		public Map<String, Habitacion> getHabitaciones() {
			return habitaciones;
		}

		public void setHabitaciones(Map<String, Habitacion> habitaciones) {
			this.habitaciones = habitaciones;
		}

		public Map<String, Huesped> getHuesped() {
			return huesped;
		}

		public void setHuesped(Map<String, Huesped> huesped) {
			this.huesped = huesped;
		}
		
		
		
}
	