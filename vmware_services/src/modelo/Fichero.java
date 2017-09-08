package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Joel Cubero
 * @version 1.0 08/09/2017
 */

public class Fichero {
		//Contenido del fichero .bat para iniciar los servicios
		private final static String START = "@echo off \n"
				+ "REM .bat con permisos de administrador \n"
				+ ":------------------------------------- \n"
				+ "REM  --> Analizando los permisos \n"
				+ "IF \"%PROCESSOR_ARCHITECTURE%\" EQU \"amd64\" ( \n"
				+ ">nul 2>&1 \"%SYSTEMROOT%\\SysWOW64\\cacls.exe\" \"%SYSTEMROOT%\\SysWOW64\\config\\system\" \n"
				+ ") ELSE ( \n"
				+ ">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\" \n"
				+ ") \n"
				+ "REM --> Si hay error es que no hay permisos de administrador. \n"
				+ "if '%errorlevel%' NEQ '0' ( \n"
				+ "    goto UACPrompt \n"
				+ ") else ( goto gotAdmin ) \n"
				+ ":UACPrompt \n"
				+ "    echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\" \n"
				+ "    set params = %*:\"=\"\" \n"
				+ "    echo UAC.ShellExecute \"cmd.exe\", \"/c \"\"%~s0\"\" %params%\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\" \n"
				+ "    \"%temp%\\getadmin.vbs\" \n"
				+ "    del \"%temp%\\getadmin.vbs\" \n"
				+ "    exit /B \n"
				+ ":gotAdmin"
				+ "    pushd \"%CD%\" \n"
				+ "    CD /D \"%~dp0\" \n"
				+ ":-------------------------------------- \n"
				+ "REM .bat \n"
				+ "sc query \"VMAuthdService\" | find \"STATE\" | find \"STOPPED\" \n"
				+ "IF ERRORLEVEL 1 ( NET START \"VMAuthdService\" ) \n"
				+ "sc query \"VMnetDHCP\" | find \"STATE\" | find \"STOPPED\" \n"
				+ "IF ERRORLEVEL 1 ( NET START \"VMnetDHCP\" ) \n"
				+ "sc query \"VMware NAT Service\" | find \"STATE\" | find \"STOPPED\" \n"
				+ "IF ERRORLEVEL 1 ( NET START \"VMware NAT Service\" ) \n"
				+ "sc query \"VMUSBArbService\" | find \"STATE\" | find \"STOPPED\" \n"
				+ "IF ERRORLEVEL 1 ( NET START \"VMUSBArbService\" ) \n"
				+ "EXIT";
		//Contenido del fichero .bat para detener los servicios
		private final static String STOP = "@echo off \n"
				+ "REM .bat con permisos de administrador \n"
				+ ":------------------------------------- \n"
				+ "REM  --> Analizando los permisos \n"
				+ "IF \"%PROCESSOR_ARCHITECTURE%\" EQU \"amd64\" ( \n"
				+ ">nul 2>&1 \"%SYSTEMROOT%\\SysWOW64\\cacls.exe\" \"%SYSTEMROOT%\\SysWOW64\\config\\system\" \n"
				+ ") ELSE ( \n"
				+ ">nul 2>&1 \"%SYSTEMROOT%\\system32\\cacls.exe\" \"%SYSTEMROOT%\\system32\\config\\system\" \n"
				+ ") \n"
				+ "REM --> Si hay error es que no hay permisos de administrador. \n"
				+ "if '%errorlevel%' NEQ '0' ( \n"
				+ "    goto UACPrompt \n"
				+ ") else ( goto gotAdmin ) \n"
				+ ":UACPrompt \n"
				+ "    echo Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\getadmin.vbs\" \n"
				+ "    set params = %*:\"=\"\" \n"
				+ "    echo UAC.ShellExecute \"cmd.exe\", \"/c \"\"%~s0\"\" %params%\", \"\", \"runas\", 1 >> \"%temp%\\getadmin.vbs\" \n"
				+ "    \"%temp%\\getadmin.vbs\" \n"
				+ "    del \"%temp%\\getadmin.vbs\" \n"
				+ "    exit /B \n"
				+ ":gotAdmin"
				+ "    pushd \"%CD%\" \n"
				+ "    CD /D \"%~dp0\" \n"
				+ ":-------------------------------------- \n"
				+ "REM .bat \n"
				+ "sc query \"VMAuthdService\" | find \"STATE\" | find \"RUNNING\" \n"
				+ "IF ERRORLEVEL 0 ( NET STOP \"VMAuthdService\" ) \n"
				+ "sc query \"VMnetDHCP\" | find \"STATE\" | find \"RUNNING\" \n"
				+ "IF ERRORLEVEL 0 ( NET STOP \"VMnetDHCP\" ) \n"
				+ "sc query \"VMware NAT Service\" | find \"STATE\" | find \"RUNNING\" \n"
				+ "IF ERRORLEVEL 0 ( NET STOP \"VMware NAT Service\" ) \n"
				+ "sc query \"VMUSBArbService\" | find \"STATE\" | find \"RUNNING\" \n"
				+ "IF ERRORLEVEL 0 ( NET STOP \"VMUSBArbService\" ) \n"
				+ "EXIT";
		
		//Metodo para escribir el fichero correspondiente a detener e iniciar los servicios
		public static void escribir(String fichero) {
			try {
				File f =  new File(System.getProperty("java.io.tmpdir") + "/" + fichero);
				
				f.deleteOnExit();
				
				FileWriter fw = new FileWriter(f);
				
				if(fichero.equals("start.bat")) {
					fw.write(START, 0, START.length());
				} else if(fichero.equals("stop.bat")) {
					fw.write(STOP, 0, STOP.length());
				}
				
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
