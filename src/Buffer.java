import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Buffer {

	private static int tamanio;
	
	private static int numeroClientes;
	
	private static int numeroServidores;
	
	private static int consultasClientes;

	private ArrayList<Mensaje> mensajes = new ArrayList<>();

	public Mensaje asignarMensaje() {
		// TODO Auto-generated method stub
		return null;
	}

	public void guardar(Mensaje men) {

		if(mensajes.size() == tamanio)
		{
			//No lo pude guardar porque me encuentro lleno. Cliente a dormir.
		}
		else
		{
			//lo pude guardar
			mensajes.add(men);
			men.guardado();
		}
		
	}

	public static void leerArchivo()
	{
		try {
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setCurrentDirectory(new File("./data/"));
			jFileChooser.setDialogTitle("Seleccione el archivo de configuracion del Caso");
			int result = jFileChooser.showOpenDialog(new JFrame());
			File selectedFile = null;
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = jFileChooser.getSelectedFile();
				System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
			}
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			br.readLine();
			br.readLine();
			String linea = br.readLine();
			
			String [] l = linea.split(",");
			
			numeroClientes = (int) Integer.parseInt(l[0]);
			numeroServidores = (int) Integer.parseInt(l[1]);
			consultasClientes = (int) Integer.parseInt(l[2]);
			tamanio = (int) Integer.parseInt(l[3]);

			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void main(String[] args)
	{
		System.out.println("Caso 1 Infracomp - 2019-2");
		System.out.println("Luis Miguel Gomez Londono - 201729597");
		System.out.println("Daniel Bernal - #########");
		System.out.println("");
		leerArchivo();
		System.out.println("Numero de Clientes: " + numeroClientes);
		System.out.println("Numero de Servidores: " + numeroServidores);
		System.out.println("Numero de consultas: " + consultasClientes);
		System.out.println("Tamanio BUFFER: " + tamanio);	
		
		System.out.println();
		
		System.out.println("*******************************************");
		System.out.println("*                 WORKING                 *");
		System.out.println("*******************************************");

		
	}

}
