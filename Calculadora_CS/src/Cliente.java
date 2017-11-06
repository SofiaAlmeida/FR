import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.util.*;


/**
 *
 * @author Fernando de la Hoz Moreno
 * @author Sofía Almeida Bruno
 */
public class Cliente {
    public static void main(String[] args) {
        String bufferRecepcion = null;
        String host = "localhost";
        int puerto = 8888;
        Socket socket = null;

        try {
            socket = new Socket(host, puerto);
            PrintWriter outPrinter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linea;
	    
	    while(!(linea = inReader.readLine()).equals("FIN")) 
		bufferRecepcion += linea + "\n";
	    
            while(!bufferRecepcion.equals("Adiós.")) {
		System.out.println(bufferRecepcion);
		bufferRecepcion = null; // Vaciamos buffer para no imprimir los mensajes anteriores cada vez
		
                // Pedir dato por teclado
                String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); //Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); //Invocamos un método sobre un objeto Scanner
                outPrinter.println(entradaTeclado);

                // Se bloquea hasta que tengamos algo que leer
                // Almacenamos el mensaje en bufferRecepcion
		while(!(linea = inReader.readLine()).equals("FIN")) 
		    bufferRecepcion += linea + "\n";
	    }

	    
	    System.out.println(bufferRecepcion);
	    socket.close();
	} catch (UnknownHostException e) {
	    System.err.println("Error: Nombre de host no encontrado");
	} catch (IOException ex) {
	    System.err.println("Error de entrada/salida al abrir el socket");
	}
    
    }
}
