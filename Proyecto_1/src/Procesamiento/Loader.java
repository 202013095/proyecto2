package Procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Adicion;
import modelo.Cama;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reserva;
import modelo.Usuario;

public class Loader
{
		
	public static Map<String, Adicion> cargarAdiciones() throws IOException
	{
		Map<String, Adicion> adiciones = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./data/adiciones"));
		
		String linea = br.readLine();
		while (linea != null) 
		{
			String[] partes = linea.split(";");

			String nombre = partes[0];
			float precio = Float.valueOf(partes[1]);
				
			Adicion laAdicion = adiciones.get(nombre);
				
			if (laAdicion == null)
			{
				laAdicion = new Adicion(nombre, precio);
				adiciones.put(nombre, laAdicion);	
			}
			
			linea = br.readLine(); 
		}
		br.close();
		return adiciones;
	}
	
	public static Map<String, Cama> cargarCamas() throws IOException
	{
		Map<String, Cama> camas = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./data/camas"));
		
		String linea = br.readLine();
		while (linea != null) 
		{
			String[] partes = linea.split(";");

			String nombre = partes[0];
			float precio = Float.valueOf(partes[1]);
			int capacidad = Integer.parseInt(partes[2]);
				
			Cama laCama = camas.get(nombre);					
			
			if (laCama == null){
				laCama = new Cama(nombre, capacidad, precio);
				camas.put(nombre, laCama);
			}
			
			linea = br.readLine();
		}
		br.close();
		return camas;
	}
	
	public static Map<String, Habitacion> cargarHabitaciones() throws FileNotFoundException, IOException
	{
		Map<String, Habitacion> habitaciones = new HashMap<>();
	
		BufferedReader br = new BufferedReader(new FileReader("./data/habitaciones"));
	
		String linea = br.readLine();
		while (linea != null) 
		{
			String[] partes = linea.split(";");
					
			String id = partes[0];
			String tipo = partes[1];
			
			int precioBase = 0;
			
			if (tipo == "estandar") 
				precioBase=300000;
			else if (tipo=="suite")
				precioBase=400000;
			else if (tipo=="suite doble")
				precioBase=500000;	
												
			
			int capacidad = 0;
			int precioFinal = precioBase;

			ArrayList<Cama> listaCamas = new ArrayList<>();
			ArrayList<Adicion> listaAdiciones = new ArrayList<>();
			
			Habitacion laHabitacion = habitaciones.get(id);
			
			String[] camas = partes[2].split(",");
			
			for (String cama : camas) 
			{
				listaCamas.add(cargarCamas().get(cama));
				capacidad+=cargarCamas().get(cama).getCapacidad();
				precioFinal+=cargarCamas().get(cama).getPrecio();
			}
			

			
			String[] adiciones = partes[3].split(",");
			
			for (String adicion : adiciones) 
			{
				listaAdiciones.add(cargarAdiciones().get(adicion));
				precioFinal+=cargarAdiciones().get(adicion).getPrecio();
			}
			
			//Crea la habitacion
			if (laHabitacion == null)
			{
				laHabitacion = new Habitacion(id, tipo, listaCamas, listaAdiciones, capacidad, precioBase, precioFinal);
				habitaciones.put(id, laHabitacion);
			}
			
			linea = br.readLine(); 

		} 
		
		br.close();
		return habitaciones;
	}

	public static ArrayList<List<String>> cargarDescuentos() throws IOException
	{
		ArrayList<List<String>> info = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./data/descuentos"));
		
		String linea = br.readLine();
		while (linea != null) 
		{
			List<String> datos = new ArrayList<>();
			
			String[] partes = linea.split(";");

			String fechaInit = partes[0];
			String fechaFinit = partes[1];
			String descuento = partes[2];					
			
			datos.add(fechaInit);
			datos.add(fechaFinit);
			datos.add(descuento);
			
			info.add(datos);
			
			linea = br.readLine();
		}
		br.close();
		return info;
	}
	
	public static Map<String, Usuario> cargarUsuarios() throws IOException
	{
		Map<String, Usuario> usuarios = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./data/usuarios"));
		
		String linea = br.readLine();
		while (linea != null) 
		{
			String[] partes = linea.split(";");

			String login = partes[0];
			String contrasena = partes[1];
			String nombre = partes[2];
			String tipoUsuario = partes[3];
			String correo = partes[4];
			
				
			Usuario elUsuario = usuarios.get(login);
				
			if (elUsuario == null)
			{
				elUsuario = new Usuario(login, contrasena, nombre, tipoUsuario, correo);
				usuarios.put(login, elUsuario);	
			}
			
			linea = br.readLine(); 
		}
		br.close();	
		return usuarios;
	}
	
	public static Map<String, Huesped> cargarHuespedes() throws IOException
	{
		Map<String, Huesped> huesped = new HashMap<>();
		
		BufferedReader br = new BufferedReader(new FileReader("./data/huespedes"));
		
		br.readLine();
		String linea = br.readLine();
		while (linea != null) 
		{
			String[] partes = linea.split(";");

			String nombre = partes[0];
			String doc = partes[1];
			String correo = partes[2];
			String celular = partes[3];
			String edad = partes[4];
			
				
			Huesped elHuesped = huesped.get(doc);
				
			if (elHuesped == null)
			{
				elHuesped = new Huesped(nombre, doc, correo, celular, edad);
				huesped.put(doc, elHuesped);	
			}
			
			linea = br.readLine(); 
		}
		br.close();
		return huesped;
	}
	
	public static void cargarReservas(Map<String, Habitacion> habitaciones, Map<String, Huesped> huesped) throws IOException
	{		
		BufferedReader br = new BufferedReader(new FileReader("./data/reservas"));
		
		br.readLine();
		String linea = br.readLine();
		
		while (linea != null) 
		{
			String[] partes = linea.split(";");

			String fechaInit = partes[0];
			String fechaFinit = partes[1];
			String numHabitacion = partes[2];
			boolean checkIn = Boolean.parseBoolean(partes[3]);
			
			String[] listaHuespedes = partes[4].split(",");			
			ArrayList<String> huespedes = new ArrayList<>();
						
			for (String elHuesped: listaHuespedes) {
				 huespedes.add(elHuesped);
			}
						
			Reserva laReserva = new Reserva(fechaInit, fechaFinit, checkIn, huespedes);
			
			habitaciones.get(numHabitacion).getListaReservas().add(laReserva);
			
			linea = br.readLine(); 
		}
		br.close();
	}
}
