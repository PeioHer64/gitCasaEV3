package gestores;

import java.util.ArrayList;

import modelo.Mascota;


/**
 * @author h
 *
 */
public interface GestorFichMascotasInterface {

	/**
	 * @param listaMascotas lista de mascotas bvacia
	 * @return lista de mask cargada
	 */
	ArrayList<Mascota> leerTXT(ArrayList<Mascota> listaMascotas);
	/**
	 * @param listaMascotas para escribir
	 */
	void escribirTXT(ArrayList<Mascota> listaMascotas);
	
}
