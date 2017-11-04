import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

/**
 * @author Sofía Almeida Bruno
 * @author Fernando de la Hoz Moreno
 **/

// Clase mensaje asociada a calculadora
class Mensaje {
    private String archivo;


    public Mensaje(String archivo){
        this.archivo = archivo;

    }



    public String mensaje(Integer cod) throws IOException {

    String mensaje = "";
    String s1 = null, s2 = null;
    StringTokenizer st;
    FileReader fl = new FileReader(archivo);
    BufferedReader bf = new BufferedReader(fl);

    //Esta sección se encarga de buscar donde comienza el mensaje a escribir
    do{
        s1 = bf.readLine();                              //Lee una linea

        if(!s1.isEmpty()){                               //Si no esta vacia la divide en palabras
            st = new StringTokenizer(s1);

            try{                                         //Coge la primera palabra
                s2 = st.nextToken();
            }catch(NoSuchElementException e){
                ;
            }
        }
    } while(!s2.equals(cod.toString()));                         //Se repite mientras la palabra no sea el código del mensaje buscado


    cod++;

    s1 = bf.readLine();                                  //Se cogen lineas y se guardan hasta no llegar al siguiente código de mensaje
    st = new StringTokenizer(s1);
    try{
        s2 = st.nextToken();
    }catch(NoSuchElementException e){
        ;
    }

    while(!s2.equals(cod.toString())){
        mensaje += s1;
        mensaje += "\n";
        s1 = bf.readLine();
        if(!s1.isEmpty()){
            st = new StringTokenizer(s1);
            try{
                s2 = st.nextToken();
            }catch(NoSuchElementException e){
                ;
            }
        }
    }

	return mensaje;
    }
}

public class Calculadora {
    // Referencia a un socket para enviar/recibir las peticiones/respuestas
    private Socket socketServicio;
    private BufferedReader inReader;
    private PrintWriter outPrinter;


    // Constructor

    //
    public int encender() {
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
