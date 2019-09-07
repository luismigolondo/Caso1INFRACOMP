import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

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

	private int tamanio;

	private int numeroClientes;

	private int numeroServidores;

	private int consultasClientes;

	private ArrayList<Mensaje> mensajes = new ArrayList<>();

	public void guardar(Mensaje men) {
		synchronized (men) {
			try {
				if(mensajes.size() < tamanio)
				{
					//Si el buffer todavia tiene capacidad entonces almaceno el mensaje.
					mensajes.add(men);
					System.out.println("Mensaje " + men.getId() + " agregado. Esperando respuesta...");
					men.wait();
				}
				//si no, duermo el cliente sobre esta clase...
				else
				{
					System.out.println("No hay mas espacio, cliente a la espera...");
					wait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Mensaje retirar()
	{
		Random random = new Random();
		int idMensaje = random.nextInt(tamanio);
		mensajes.remove(idMensaje);

		return null;
	}

	public void leerArchivo()
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
		Buffer b = new Buffer();
		System.out.println("Caso 1 Infracomp - 2019-2");
		System.out.println("Luis Miguel Gomez Londono - 201729597");
		System.out.println("Daniel Bernal - #########");
		System.out.println("");
		b.leerArchivo();
		System.out.println("Numero de Clientes: " + b.numeroClientes);
		System.out.println("Numero de Servidores: " + b.numeroServidores);
		System.out.println("Numero de consultas: " + b.consultasClientes);
		System.out.println("Tamanio BUFFER: " + b.tamanio);	

		System.out.println();

		System.out.println("*******************************************");
		System.out.println("*                 WORKING                 *");
		System.out.println("*******************************************");
		//Creacion de clientes...
		for (int i = 0; i < b.numeroClientes; i++) {
			Cliente cliente =  new Cliente(i + 1, b.consultasClientes, b);
			cliente.run();
		}

	}

}
