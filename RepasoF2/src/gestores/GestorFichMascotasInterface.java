package gestores;

import java.util.ArrayList;

import modelo.Mascota;

public interface GestorFichMascotasInterface {

	ArrayList<Mascota> leerTXT(ArrayList<Mascota> listaMascotas);
	void escribirTXT(ArrayList<Mascota> listaMascotas);
	
}
