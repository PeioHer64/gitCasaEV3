package modelo;

import controlador.Metodos;
import excepciones.ExceptionDNI;

public class Mascota {
	protected int id;
	protected String nombre;
	protected int edad;
	protected String dniDueno;
	
	public static int idGP=1;
	
	public Mascota(int id, String nombre, int edad, String dniDueno) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.dniDueno = dniDueno;
	}
	
	public Mascota() {

	}
	
	public Mascota(String masUno) {
		id = idGP;
		idGP++;

	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public int getEdad() {
		return edad;
	}
	public String getDniDueno() {
		return dniDueno;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void setDniDueno(String dniDueno) throws Exception {
		Metodos mts = new Metodos();
		
		
		if(mts.validarDni(dniDueno)) {
			this.dniDueno = dniDueno;
		}else {
			throw new ExceptionDNI("DNI incorrecto");
		}
	}
}
