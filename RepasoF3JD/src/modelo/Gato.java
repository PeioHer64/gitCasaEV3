package modelo;

public class Gato extends Mascota{
	private String color;
	private tipoPelo largoPelo;
	
	

	public Gato(int id, String nombre, int edad, String dniDueno, String color, tipoPelo largoPelo) {
		super(id, nombre, edad, dniDueno);
		this.color = color;
		this.largoPelo = largoPelo;
	}



//f
	public Gato(int id, String nombre, int edad, String dniDueno) {
		// TODO Auto-generated constructor stub
		super(id, nombre, edad, dniDueno);
	}

	public Gato() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "G,"+id+","+nombre+","+edad+","+color+","+largoPelo+","+dniDueno;
	}




	public String getColor() {
		return color;
	}
	public tipoPelo getLargoPelo() {
		return largoPelo;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setLargoPelo(tipoPelo largoPelo) {
		this.largoPelo = largoPelo;
	}
	
}

