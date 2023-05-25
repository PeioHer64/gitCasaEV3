package controlador;

import java.util.ArrayList;
import java.util.Scanner;

import gestores.GestorFichMascotas;
import modelo.Gato;
import modelo.Mascota;
import modelo.Perro;
import modelo.tipoPelo;

/**
 * @author henry
 *clse de metods
 */
public class Metodos {
GestorFichMascotas gfm = new GestorFichMascotas();
ArrayList<Mascota> listaMascotas = new ArrayList<Mascota>();
	
	/**
	 * menu de app
	 * @param sc scanner
	 */
	public void menu(Scanner sc) {
		listaMascotas = gfm.leerTXT(listaMascotas);
		//cargarBD y insert
		int opcion =0;
		
		do {
		System.out.println("1. Añadir mascota.\r\n"
				+ "2. Mostrar datos\r\n"
				+ "3. Modificar mascota.\r\n"
				+ "4. Eliminar mascota.\r\n"
				+ "5. Eliminar mascota por DNI del dueño.\r\n"
				+ "6. Buscar mascota por DNI\r\n"
				+ "7. Salir");
		opcion = ValidarEntero(sc, 1, 7, "selecione opcion");
		System.out.println("hola");
		
		switch(opcion) {
		case 1:
			anadirMascota(sc,listaMascotas);//
			break;
		case 2:
			mostrarDatos(listaMascotas);//
			break;
		case 3:
			listaMascotas = modificarMascota(sc, listaMascotas);//
			break;
		case 4:
			eliminarMascota(sc, listaMascotas);//
			break;
		case 5:
			eliminarMascotaPorDNI(sc, listaMascotas);//
			break;
		case 6:
			buscarMascotaDNI_Mostrar(sc);//
			break;
		case 7:
			//txt + metodos BDD || y mirar selct
			gfm.escribirTXT(listaMascotas);//
			break;
			
		}
		
		}while(opcion!=7);
	}


	private void buscarMascotaDNI_Mostrar(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("introduzca un dni");
		String dni= sc.nextLine();
		
		if(buscarMascotaPorDNI(listaMascotas, dni)==null) {
			System.out.println("no se encontro la mascota");
		}else {
			System.out.println(buscarMascotaPorDNI(listaMascotas, dni).toString());
		}
		
		
	}


	private Mascota buscarMascotaPorID(ArrayList<Mascota> listaMascotas, int id) {
		// TODO Auto-generated method stub
		Mascota mascotaBus = null;
		int i = 0;
		do {
			if(id==listaMascotas.get(i).getId()) {
				mascotaBus = listaMascotas.get(i);
			}
			i++;
		}while(i < listaMascotas.size() && id != listaMascotas.get(i-1).getId());
		
		return mascotaBus;
	}
	

	private Mascota buscarMascotaPorDNI(ArrayList<Mascota> listaMascotas, String dni) {
		// TODO Auto-generated method stub
		Mascota mascotaBus = null;
		int i = 0;
		do {
			if(dni.equals(listaMascotas.get(i).getDniDueno())) {
				mascotaBus = listaMascotas.get(i);
			}
			i++;
		}while(i < listaMascotas.size() && !dni.equals(listaMascotas.get(i-1).getDniDueno()));
		
		return mascotaBus;
	}

	private void eliminarMascotaPorDNI(Scanner sc, ArrayList<Mascota> listaMascotas) {
		// TODO Auto-generated method stub
		System.out.println("introduzca un dni");
		String dni= sc.nextLine();
		Mascota maskBus = buscarMascotaPorDNI(listaMascotas, dni);
		
		if(maskBus==null) {
			System.out.println("no se encontro la mascota");
		}else {
			listaMascotas.remove(maskBus);
		}
		
	}

	private void eliminarMascota(Scanner sc, ArrayList<Mascota> listaMascotas) {
		// TODO Auto-generated method stub
		int id= ValidarEntero(sc, 0, 99, "introduzca un id");
		Mascota maskBus = buscarMascotaPorID(listaMascotas, id);
		
		if(maskBus==null) {
			System.out.println("no se encontro la mascota");
		}else {
			listaMascotas.remove(maskBus);
		}
	}

	private void mostrarDatos(ArrayList<Mascota> listaMascotas) {
		// TODO Auto-generated method stub
     for (Mascota mascota : listaMascotas) {
    	 System.out.println(mascota.toString());
	}
		
	}

	private ArrayList<Mascota> modificarMascota(Scanner sc, ArrayList<Mascota> listaMascotas) {
		// TODO Auto-generated method stub
		int id= ValidarEntero(sc, 0, 99, "introduzca un id");
		Mascota maskBus = buscarMascotaPorID(listaMascotas, id);
		
		if(maskBus==null) {
			System.out.println("no se encontro la mascota");
		}else {
			int opcionMod = ValidarEntero(sc, 1, 2, "que quiere cambiar?\r\n"
					+ "1- nombre\r\n"
					+ "2- edad");
			if(opcionMod==1) {
				System.out.println("Introduzca un nombre");
				maskBus.setNombre(sc.nextLine());
			}else {
				maskBus.setEdad(ValidarEntero(sc, 0, 90, "Introduzca una edad"));
			}
//			 if (mascota instanceof perro){
//				 
//			 }
			    
		}
		
		
		
		return listaMascotas;
		
	}

	private ArrayList<Mascota> anadirMascota(Scanner sc, ArrayList<Mascota> listaMascotas) {
		// TODO Auto-generated method stub
		Mascota mask = new Mascota("masUno");
		
		System.out.println("Introduzca un nombre");
		mask.setNombre(sc.nextLine());
		mask.setEdad(ValidarEntero(sc, 0, 90, "Introduzca una edad"));
		
		boolean errorD = false;
		do {
		System.out.println("Introduzca un DNI");
		try {
			mask.setDniDueno(sc.nextLine());
			errorD = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorD = true;
			System.out.println(e.getMessage());
		}
		}while(errorD);
		
		int opcionMascota = ValidarEntero(sc, 1, 2, "¿que es?\r\n"
				+ "1. perro\r\n"
				+ "2. gato");
		
		if(opcionMascota==1) {
			Perro per = new Perro(mask.getId(), mask.getNombre(), mask.getEdad(), mask.getDniDueno());
			System.out.println("Introduzca una raza");
			per.setRaza(sc.nextLine());
			
			boolean error=true;
			do {
				System.out.println("pulgas? (S/N)");
				String opcionPulg = sc.nextLine();
				if(opcionPulg.toUpperCase().equals("S")) {
					per.setPulgas(true);
					error=false;
				}else if(opcionPulg.toUpperCase().equals("N")) {
					per.setPulgas(false);
					error=false;
				}
			}while(error);
			
			listaMascotas.add(per);
		}else {
			Gato gat = new Gato(mask.getId(), mask.getNombre(), mask.getEdad(), mask.getDniDueno());
			System.out.println("Introduzca una color");
			gat.setColor(sc.nextLine());
			
			boolean error=true;
			do {
				System.out.println("pelo largo, medio o corto?(L/M/C)");
				String opcionPel=sc.nextLine();
				if(opcionPel.toUpperCase().equals("L")) {
					gat.setLargoPelo(tipoPelo.L);
					error=false;
				}else if(opcionPel.toUpperCase().equals("M")) {
					gat.setLargoPelo(tipoPelo.M);
					error=false;
				}else if(opcionPel.toUpperCase().equals("C")) {
					gat.setLargoPelo(tipoPelo.C);
					error=false;
				}
			}while(error);
			
			listaMascotas.add(gat);
		}
		
		
		return listaMascotas;
	}
	
	
	
	
	//extras
	
	
	/**
	 * validar string
	 * @param sc scanner
	 * @param min largura minima
	 * @param max largura maxima
	 * @param texto pregunta
	 * @return devuele un string validado
	 */
	public String ValidarString(Scanner sc, int min, int max, String texto) {
		String opcS = "";
		boolean error = false;
		do {
			System.out.println(texto);
			opcS = sc.nextLine();
			try {
				if (opcS.length() < min || opcS.length() > max)
					error = true;
				else
					error = false;
			} catch (Exception ex) {
				error = true;
			}
		} while (error);
		return opcS;
	}
	
	
	/**
	 * metodo validar entero
	 * @param sc scnner
	 * @param min minimo
	 * @param max maximo
	 * @param texto pregunta
	 * @return devueve un entero validado
	 */
	public int ValidarEntero(Scanner sc, int min, int max, String texto) {
		int num = -1;
		boolean error = false;
		do {
			System.out.println(texto);
			String opcS = sc.nextLine();
			try {
				num = Integer.valueOf(opcS);
				if (num < min || num > max)
					error = true;
				else
					error = false;
			} catch (Exception ex) {
				error = true;
			}
		} while (error);
		return num;
	}
	
	
	
	   /**
	 * @param dni numero de identificacion
	 * @return dni validado
	 */
	public boolean validarDni(String dni) {
	        if (dni.length() != 9) {
	            return false;
	        }
	        
	        String numeros = dni.substring(0, 8);
	        String letra = dni.substring(8);
	        
	        try {
	            Integer.parseInt(numeros);
	        } catch (NumberFormatException e) {
	            return false;
	        }
	        
	        return letra.matches("[a-zA-Z]");
	    }
	        
	        
	    
	
	
	
	
}
