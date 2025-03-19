package modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @version 1.0 08/09/2017
 */

public class Comando {
	
	//Metodo que ejecuta un comando en consola y retorna como cadena
	//la respuesta del comando
	public String exec(String comando) {
		String resultado = "";
		try {
			String linea;
			
	        Process p = Runtime.getRuntime().exec("cmd /C " + comando);
	        
	        InputStreamReader entrada = new InputStreamReader(p.getInputStream());
            BufferedReader input = new BufferedReader(entrada);
            
            
            while ((linea = input.readLine()) != null) {
            	resultado += linea + "\n";
            }            
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
		return resultado;
	}
	
	//Metodo para comprobar estado de los servicios
	public int comprobarEstado() {
		int status = 0;
		String [] servicios = { "VMAuthdService", "VMnetDHCP", "VMware NAT Service", "VMUSBArbService" };
		int [] estados = new int[4];
		//Comprobamos uno a uno los servicios y almacenamos en estados
		for (int i = 0; i < servicios.length; i++) {
			String resultado = exec("sc query " + "\""+servicios[i]+"\"");
			if(resultado.contains("STOPPED")) {
				estados[i] = 0;
            } else if (resultado.contains("RUNNING")) {
            	estados[i] = 1;
            }
		}
		//Comprobamos si todoso los servicios estan iniciados o detenidos
		if(estados[0] == 0 && estados[1] == 0 && estados[2] == 0 && estados[3] == 0) {
			status = 0;
		} else if(estados[0] == 1 && estados[1] == 1 && estados[2] == 1 && estados[3] == 1) {
			status = 1;
		}
		return status;
	}
	
}
