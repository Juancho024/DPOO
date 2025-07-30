package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat; 
import java.util.Date;           

public class Servidor extends Thread {

    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
        	
            sfd = new ServerSocket(7000);
            System.out.println("Servidor iniciado en el puerto 7000. Esperando conexiones...");
        } catch (IOException ioe) {
            System.err.println("Error: No se pudo iniciar el servidor en el puerto 7000. " + ioe.getMessage());
            System.exit(1); 
        }

        while (true) { 
            Socket nsfd = null;
            DataInputStream oos = null;
            DataOutputStream escritor = null;
            try {
              
                nsfd = sfd.accept();
                System.out.println("Conexión aceptada de: " + nsfd.getInetAddress().getHostAddress());

                oos = new DataInputStream(nsfd.getInputStream());
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
                Date now = new Date();
                String fechaActual = formatter.format(now); 
                
                String nombreBaseArchivo = "Bolsa";
                String extensionArchivo = ".dat";
                
                String nombreArchivoRespaldo = nombreBaseArchivo + "_" + fechaActual + "_respaldo" + extensionArchivo;
                
                System.out.println("Guardando el respaldo como: " + nombreArchivoRespaldo);

                escritor = new DataOutputStream(new FileOutputStream(new File(nombreArchivoRespaldo)));
                
                int unByte;
                
                while ((unByte = oos.read()) != -1) {
                    escritor.write(unByte);
                }
                System.out.println("Archivo recibido y guardado como '" + nombreArchivoRespaldo + "' exitosamente.");

            } catch (IOException ioe) {
                System.err.println("Error durante la conexión o transferencia: " + ioe.getMessage());
                // ioe.printStackTrace(); 
            } finally {
                
                try {
                    if (oos != null) oos.close();
                    if (escritor != null) escritor.close();
                    if (nsfd != null) nsfd.close();
                } catch (IOException closeEx) {
                    System.err.println("Error al cerrar recursos: " + closeEx.getMessage());
                }
            }
        }
    }
}