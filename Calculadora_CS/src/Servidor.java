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

class Calculador extends Thread{
    private Socket socket;
    
    Calculador(Socket socket){
        this.socket = socket;
    }

    public void run(){
        Calculadora calculadora = new Calculadora(socket);
        Calculadora.encender();
    }

}


public class Server{
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
    catch (Exception err) {
        System.out.println();
    }
}
