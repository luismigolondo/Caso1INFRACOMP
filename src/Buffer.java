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
	
	private int mensajesRestantes;
	
	private ArrayList<Mensaje> mensajes = new ArrayList<>();

	public Buffer() {
		leerArchivo();
		mensajesRestantes = numeroClientes * consultasClientes;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public int getNumeroClientes() {
		return numeroClientes;
	}

	public void setNumeroClientes(int numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public int getNumeroServidores() {
		return numeroServidores;
	}

	public void setNumeroServidores(int numeroServidores) {
		this.numeroServidores = numeroServidores;
	}

	public int getConsultasClientes() {
		return consultasClientes;
	}

	public void setConsultasClientes(int consultasClientes) {
		this.consultasClientes = consultasClientes;
	}

	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public int getMensajesRestantes() {
		return mensajesRestantes;
	}

	public void setMensajesRestantes(int mensajesRestantes) {
		this.mensajesRestantes = mensajesRestantes;
	}

	public void guardar(Mensaje men) {
		synchronized (this) {
			try {
				if(mensajes.size() < tamanio)
				{
					//Si el buffer todavia tiene capacidad entonces almaceno el mensaje.
					mensajes.add(men);
					System.out.println("Mensaje " + men.getId() + " agregado. Esperando respuesta...");
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
		Mensaje retirado = null;
		synchronized(this)
		{
			if(!mensajes.isEmpty())
			{
				Random random = new Random();
				int idMensaje = random.nextInt(tamanio);
				retirado = mensajes.remove(0);
				//Como ya liberamos uno entonces le decimos a los clientes en espera que sigan...
				notifyAll();
				System.out.println("Restantes " + mensajesRestantes);
				mensajesRestantes--;
			}
		}

		return retirado;
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
		Buffer buffer = new Buffer();
		System.out.println("Caso 1 Infracomp - 2019-2");
		System.out.println("Luis Miguel Gomez Londono - 201729597");
		System.out.println("Daniel Bernal - #########");
		System.out.println("");
		System.out.println("Numero de Clientes: " + buffer.getNumeroClientes());
		System.out.println("Numero de Servidores: " + buffer.getNumeroServidores());
		System.out.println("Numero de consultas: " + buffer.getConsultasClientes());
		System.out.println("Tamanio BUFFER: " + buffer.getTamanio());	

		System.out.println();

		System.out.println("*******************************************");
		System.out.println("*                 WORKING                 *");
		System.out.println("*******************************************");
		//Creacion de clientes...
		for (int i = 0; i < buffer.numeroClientes; i++) {
			Cliente cliente =  new Cliente(i + 1, buffer.getConsultasClientes(), buffer);
			cliente.run();
		}
		//creacion de servidores...
		for (int i = 0; i < buffer.numeroServidores; i++) {
			Servidor servidor = new Servidor(i + 1, buffer);
			servidor.run();
		}
		

	}

}
