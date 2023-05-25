package modelo;

public class Perro extends Mascota{
private String raza;
private boolean pulgas;





public Perro(int id, String nombre, int edad, String dniDueno, String raza, boolean pulgas) {
	super(id, nombre, edad, dniDueno);
	this.raza = raza;
	this.pulgas = pulgas;
}


//f
public Perro(int id, String nombre, int edad, String dniDueno) {
	// TODO Auto-generated constructor stub
	super(id, nombre, edad, dniDueno);
}

public Perro() {
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "P,"+id+","+nombre+","+edad+","+raza+","+pulgas+","+dniDueno;
}



public String getRaza() {
	return raza;
}
public boolean isPulgas() {
	return pulgas;
}
public void setRaza(String raza) {
	this.raza = raza;
}
public void setPulgas(boolean pulgas) {
	this.pulgas = pulgas;
}



}
