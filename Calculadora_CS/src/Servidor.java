import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Sofía Almeida Bruno
 * @author Fernando de la Hoz Moreno
 **/

// Establece la comunicación, espera un cliente
// Cuando un cliente establece comunicación establece un socket TCP que luego le pasamos a Calculadora
// A partir de ese momento Calculadora realiza la comunicación con el cliente a través del socket

class Calculador extends Thread {
    private Socket socket;
    
    Calculador(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
	    Calculadora calculadora = new Calculadora(socket);
	    calculadora.encender();
	} catch (IOException e) {
	    System.err.println("Error al crear/encender calculadora");
	}
    }

}


public class Servidor {

    public static void main(String[] args) {
	int puerto = 8888;
	ServerSocket servidorSocket;
	Socket socket;

	try {
	    servidorSocket = new ServerSocket(puerto);

	    do{
		socket = servidorSocket.accept();
		Calculador calculador = new Calculador(socket);
		calculador.start();

	    } while(true);
	}
	catch (IOException e) {
	    System.err.println("Error al escuchar en el puerto " + puerto);
	}
    }
}
