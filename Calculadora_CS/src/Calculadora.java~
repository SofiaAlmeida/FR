import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;


/**
 * @author Sofía Almeida Bruno
 * @author Fernando de la Hoz Moreno
 **/

// Clase mensaje asociada a calculadora
class Mensaje {
    // Ruta del archivo donde se encuentren los mensajes
    private final String archivo = "../mensajes.txt";

    public String mensaje(Integer cod) throws IOException {
	/*File f = new File(archivo);
	  f.getAbsolutePath()*/
	    String mensaje = "";
	    String s1 = null, s2 = null;
	    StringTokenizer st;
	    FileReader fl;
	    BufferedReader bf;
	try {
	    fl = new FileReader(archivo);
	    bf = new BufferedReader(fl);
	} catch (IOException e) {
	    throw new ExceptionInInitializerError(e.getMessage());
	}
    //Esta sección se encarga de buscar donde comienza el mensaje a escribir
    do {
        s1 = bf.readLine();                              //Lee una linea

        if(!s1.isEmpty()) {                               //Si no esta vacia la divide en palabras
            st = new StringTokenizer(s1);

            try {                                         //Coge la primera palabra
                s2 = st.nextToken();
            } catch(NoSuchElementException e) {}
        }
    } while(!s2.equals(cod.toString()));                         //Se repite mientras la palabra no sea el código del mensaje buscado


    cod++;

    s1 = bf.readLine();                                  //Se cogen lineas y se guardan hasta no llegar al siguiente código de mensaje
    st = new StringTokenizer(s1);
    try {
        s2 = st.nextToken();
    } catch(NoSuchElementException e) {}

    while(!s2.equals(cod.toString())) {
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
    private Socket socket;
    private BufferedReader inReader;
    private PrintWriter outPrinter;
    private Mensaje m;


    // Constructor
    Calculadora(Socket socket) throws IOException {
        this.socket=socket;
	inReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
	outPrinter = new PrintWriter(socket.getOutputStream(),true);
	m = new Mensaje();
    }


    private Character res_volver(String s) throws IOException{
        Character c;

        c = comunicacion("El resultado es: " + s + "\n\n" + m.mensaje(10));
        while((!c.equals('S')) && (!c.equals('N'))) {
            c = comunicacion("\nOpción incorrecta: \n" +m.mensaje(10));
        }

        return c;
    }

    private String peticion_datos(String s) throws IOException {
        String datos;
        outPrinter.println(s); // Le pasamos el mensaje
        outPrinter.flush();
        datos = inReader.readLine(); // Cliente devuelve los datos

        return datos;
    }

    private Character comunicacion(String s) throws IOException {
	Character c;

	outPrinter.println(s); // Le pasamos el mensaje
	outPrinter.flush();
        c = (char) inReader.read(); // Cliente devuelve la opción inicial
	
        return c;
    }


    public void encender() throws IOException {
        Character c;

        do {
        c = comunicacion(m.mensaje(1));
        while(!c.equals('A') && !c.equals('B'))
            c = comunicacion("Error: opción incorrecta\n\n"+ m.mensaje(1));
        
        if(c.equals('A'))
            c = menu_op();
        else
            c = menu_ec();

        } while(c.equals('S'));

        outPrinter.println(m.mensaje(11));
        outPrinter.flush();
    }

    public Character menu_ec() throws IOException {
        Character c;
        c = comunicacion(m.mensaje(3));
	
        while(c < 'A' || c > 'B')
            c = comunicacion("Error: opción incorrecta\n\n" + m.mensaje(3));

        if(c.equals('A'))
            c = ec1();
        else
            c = ec2();

        return c;
    }

    public Character ec1() throws IOException {
        String s = peticion_datos(m.mensaje(7));
        Double d1, d2;

        StringTokenizer st = new StringTokenizer(s);
        s = st.nextToken();
        d1 = Double.parseDouble(s);
        s = st.nextToken();
        d2 = Double.parseDouble(s);

        s = d1 == 0 ? "No tiene solución" : "x = " + String.valueOf(-d2 / d1);
        return res_volver(s);
    }

    public Character ec1(double d1, double d2) throws IOException {
        String s = d1 == 0 ? "No tiene solución" : "x = " + String.valueOf(-d2 / d1);
        return res_volver(s);
    }

    public Character ec2() throws IOException {
        String s = peticion_datos(m.mensaje(8));
        Double d1, d2, d3, d;
        Character c;

        StringTokenizer st = new StringTokenizer(s);
        s = st.nextToken();
        d1 = Double.parseDouble(s);
        s = st.nextToken();
        d2 = Double.parseDouble(s);
        s = st.nextToken();
        d3 = Double.parseDouble(s);

        if(d1 == 0)
            c = ec1(d2, d3);
        else {
            d = (-d2 + sqrt(d2*d2 - 4*d1*d3)) / 2*d1;
            d1 = (-d2 - sqrt(d2*d2 - 4*d1*d3)) / 2*d1;

            s = "x1 = " + String.valueOf(d) + ", x2 = " + String.valueOf(d1);
            c = res_volver(s);
        }
        return c;
    }

    Character menu_op() throws IOException {
	Character c;

        c = comunicacion(m.mensaje(2));
        while(c < 'A' || c > 'G'){
            c = comunicacion("Error: opción incorrecta\n\n"+ m.mensaje(2));
        }

        if(c >= 'F')
            c = op_mon(c);
        else
            c = op_bin(c);

        return c;
    }

    Character op_mon(Character c) throws IOException{
        String s = peticion_datos(m.mensaje(5));
        Double d = Double.parseDouble(s);

        if(c.equals('F')) //Si es una logarítmica
            d = log(d);
        else
            d = exp(d);

        return res_volver(String.valueOf(d));
    }

    public Character op_bin(Character c) throws IOException{
        String s;
        Double d1, d2;

        if(c.equals('E'))
            s = peticion_datos(m.mensaje(6));
        else
            s = peticion_datos(m.mensaje(4));

        StringTokenizer st = new StringTokenizer(s);
        s = st.nextToken();
        d1 = Double.parseDouble(s);
        s = st.nextToken();
        d2 = Double.parseDouble(s);

        switch (c) {
            case 'A':
                d1 = d1 + d2;
                break;
            case 'B':
                d1 = d1 - d2;
                break;
            case 'C':
                d1 = d1 * d2;
                break;
            case 'D':
                d1 = d1 / d2;
                break;
            case 'E':
                d1 = pow(d1, d2);
                break;
        }

        return res_volver(String.valueOf(d1));
    }
}
