import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Sofía Almeida Bruno
 * @author Fernando de la Hoz Moreno
 **/

// Clase mensaje asociada a calculadora
class Mensaje {
    public string mensaje(int cod) {
	string mensaje;
	//switch-case
	return mensaje
    }
}

public class Calculadora {
    // Referencia a un socket para enviar/recibir las peticiones/respuestas
    private Socket socketServicio;
    private BufferedReader inReader;
    private PrintWriter outPrinter;

    // Constructor

    //
    public int menu_ini() {
	//muestra mensaje correspondiente
	//bucle esperando respuesta válida
	return cod_menu_sec;
    }

    menu_sec(int cod) {
    }

    menu_op() {
	//Muestra el menú de operaciones
	//Pide opción y compueba si es válida
	
    }
    menu_ec() {
	//Muestra el menú de ecuaciones
    }
    
    public static void main() {
	int cod = menu_ini;
	
	if (cod == cod_menu_op)
	    menu_op();
	else
	    menu_ec();
	
    }
}
