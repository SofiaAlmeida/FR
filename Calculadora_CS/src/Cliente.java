import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


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

            inReader.read(bufferRecepcion);
            while(bufferRecepcion.equals("Adiós.")) {
                System.out.println(bufferRecepcion);

                // Pedir dato por teclado
                String s = ...

                outPrinter.println(s);

                // Se bloquea hasta que tengamos algo que leer
                // Almacenamos el mensaje en bufferRecepcion
                inReader.read(bufferRecepcion);

            }

            System.out.println(bufferRecepcion);
            socket.close()
        } catch (UnknownHostException e) {
            System.err.println("Error: Nombre de host no encontrado");
        } catch (IOException ex) {
            System.err.println("Error de entrada/salida al abrir el socket");
        }
    }
}
