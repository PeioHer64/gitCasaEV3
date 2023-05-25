package gestores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Gato;
import modelo.Mascota;
import modelo.Perro;
import modelo.tipoPelo;

/**
 * @author hery
 *gestor de mask
 */
public class GestorFichMascotas extends GestorFichMascotasAbstract implements GestorFichMascotasInterface {

	public ArrayList<Mascota> leerTXT(ArrayList<Mascota> listaMascotas){
		File file = new File(path+"/"+filename);
		BufferedReader fichero;
		try {
			fichero = new BufferedReader(new FileReader(file));
		String linea;
	
		
		while((linea = fichero.readLine())!=null){
			if(linea.contains("P") || linea.contains("G")){
			
				if (linea.split(",")[0].equals("G")){
					//int id, String nombre, int edad, String dniDueno, String color, tipoPelo largoPelo
					int id = Integer.valueOf(linea.split(",")[1]);
					String nombre = linea.split(",")[2];
					int edad = Integer.valueOf(linea.split(",")[3]);
					String color = linea.split(",")[4];
					tipoPelo largoPelo = tipoPelo.valueOf(linea.split(",")[5]);
					String dniDueno = linea.split(",")[6];
					
					
					Gato obj = new Gato(id, nombre, edad, dniDueno, color, largoPelo);
					listaMascotas.add(obj);
					Mascota.idGP = Mascota.idGP+1;
				}
				if (linea.split(",")[0].equals("P")){
					//int id, String nombre, int edad, String dniDueno, String raza, boolean pulgas
					int id = Integer.valueOf(linea.split(",")[1]);
					String nombre = linea.split(",")[2];
					int edad = Integer.valueOf(linea.split(",")[3]);
					String raza = linea.split(",")[4];
					boolean pulgas = Boolean.valueOf(linea.split(",")[5]);
					String dniDueno = linea.split(",")[6];
					
					Perro obj = new Perro(id, nombre, edad, dniDueno, raza, pulgas);
					listaMascotas.add(obj);
					Mascota.idGP = Mascota.idGP+1;
				}
				
			}
				
				
		}
		System.out.println("todo leido TXT");
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(arrayMontes[0].getNombre());
		return listaMascotas;
	}
	
	public void escribirTXT(ArrayList<Mascota> listaMascotas){
	File file = new File(path+"/"+filename);
		
		BufferedWriter fichero;
		
			try {
				fichero = new BufferedWriter(new FileWriter(file));
			for(int i =0;i<listaMascotas.size();i++)
			{
				fichero.write(listaMascotas.get(i).toString()+"\n");
			}
			fichero.close();
			System.out.println("se ha escrito al TXT");
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	/**
	 * @param listaMascotas lista de masc
	 * @return lista cargada
	 * @throws Exception sql except?
	 */
	public ArrayList<Mascota> leerBD(ArrayList<Mascota> listaMascotas) throws Exception {
		// TODO Auto-generated method stub
				Connection conexion;
				
				try {
					conexion=(Connection) DriverManager.getConnection("DBUtils.URL","DBUtils.USER","DBUtils.PASS");
					Statement comando=(Statement) conexion.createStatement();
					
					ResultSet registro = comando.executeQuery("select * from "+"ManagerAbstract.TABLE_BOOKS"+"");
					while (registro.next()) {
						
						
						if(registro.getString(3)==null) {//raza
						Perro obj = new Perro();
						obj.setNombre(registro.getString(1));
						obj.setEdad(registro.getInt(1));
						obj.setDniDueno(registro.getString(1));
						obj.setPulgas(registro.getBoolean(1));
						obj.setRaza(registro.getString(1));
						
						listaMascotas.add(obj);
						}else {
						Gato obj = new Gato();
						obj.setNombre(registro.getString(1));
						obj.setEdad(registro.getInt(1));
						obj.setDniDueno(registro.getString(1));
						obj.setColor(registro.getString(1));
						obj.setLargoPelo(tipoPelo.valueOf(registro.getString(1)));
						
						listaMascotas.add(obj);
						}
						
						
						
					}
					registro.close();

					
					comando.close();
					conexion.close();
				} catch(SQLException ex){
						ex.printStackTrace();

				}
				
				if(listaMascotas.size()==0) {
					throw new Exception("carga de bd fallida");	
				}
				
				return listaMascotas;
	}
	
	
	/**
	 * escribe cosas
	 * @throws SQLException excepcion de sql
	 */
	public void escribirBD() throws SQLException {
		Connection conexion=(Connection) DriverManager.getConnection("DBUtils.URL","DBUtils.USER","DBUtils.PASS");
		Statement comando = (Statement) conexion.createStatement();
		comando.executeUpdate("INSERT INTO "+"TABLAS.SECCION"+" VALUES ('"+"su.getCodigoSuper()"+"','"+"se.getCodigoSeccion()"+"','"+".getNombreSeccion()"+"','"+"se.getNumArticulo()"+"')");
		comando.close();
	}
	

}
