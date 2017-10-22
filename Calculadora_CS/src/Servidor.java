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
