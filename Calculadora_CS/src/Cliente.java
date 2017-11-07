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
        String bufferRecepcion = "";
        String host = "localhost";
        int puerto = 8888;
        Socket socket = null;

        try {
            socket = new Socket(host, puerto); // Establecemos conexión
	    // Establecemos los flujos de comunicación
            PrintWriter outPrinter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linea;
	    
	    // Leemos mensaje inicial
	    while(!(linea = inReader.readLine()).equals("FIN")) 
		bufferRecepcion += linea + "\n";
	    
	    // Mientras no sea el mensaje final, leemos mensajes, los pasamos por pantalla
	    // recibimos respuesta y se la pasamos a Calculadora
	    while(!bufferRecepcion.contains("Adiós.")) {
		System.out.println(bufferRecepcion);
		bufferRecepcion = ""; // Vaciamos buffer para no imprimir los mensajes anteriores cada vez
	
	        // Pedir dato por teclado
		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner(System.in); // Creación de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine(); // Invocamos un método sobre un objeto Scanner
		outPrinter.println(entradaTeclado);

		// Se bloquea hasta que tengamos algo que leer
		// Almacenamos el mensaje en bufferRecepcion
		while(!(linea = inReader.readLine()).equals("FIN")) 
		    bufferRecepcion += linea + "\n";
	    }

	    // Imprimir mensaje final
	    System.out.println(bufferRecepcion);
	    // Cerrar conexión	    
	    socket.close();
	} catch (UnknownHostException e) {
	    System.err.println("Error: Nombre de host no encontrado");
	} catch (IOException ex) {
	    System.err.println("Error de entrada/salida al abrir el socket");
	}
    
    }
}
