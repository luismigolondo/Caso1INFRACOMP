import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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

	public synchronized int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public synchronized int getNumeroClientes() {
		return numeroClientes;
	}

	public void setNumeroClientes(int numeroClientes) {
		this.numeroClientes = numeroClientes;
	}

	public synchronized int getNumeroServidores() {
		return numeroServidores;
	}

	public void setNumeroServidores(int numeroServidores) {
		this.numeroServidores = numeroServidores;
	}

	public synchronized int getConsultasClientes() {
		return consultasClientes;
	}

	public void setConsultasClientes(int consultasClientes) {
		this.consultasClientes = consultasClientes;
	}

	public synchronized ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public synchronized int getMensajesRestantes() {
		return mensajesRestantes;
	}

	public void setMensajesRestantes(int mensajesRestantes) {
		this.mensajesRestantes = mensajesRestantes;
	}

	public void guardar(Mensaje men) {
		//para que cuando el cliente se despierte, tenga la posibliidad de agregar el mensaje...
		while(true)
		{
			try {

				synchronized (men) {
					if(mensajes.size() < tamanio)
					{
						//Si el buffer todavia tiene capacidad entonces almaceno el mensaje.
						mensajes.add(men);
						System.out.println("Mensaje " + men.getId() + " de cliente " + men.getCliente().getIdentificador() + " agregado. Esperando respuesta...");
						// mando a dormir el cliente dentro del mensaje que me esta usando mientras espero respuesta.
						men.wait();
						break;
					}
					//si no, duermo el cliente sobre esta clase...
					else
					{
						synchronized (this) {
							System.out.println("No hay mas espacio, cliente " + men.getCliente().getIdentificador() + " a la espera...");
							//poner a esperar el cliente que me esta usando si no hay espacio...
							wait();				
						}
					}
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
				retirado = mensajes.remove(0);
				mensajesRestantes--;
				if(retirado != null)
					System.out.println("Mensaje " + retirado.getId() +  "  de cliente " + retirado.getCliente().getIdentificador() + " respondido");
			}
			//Aca despierto al cliente ya que el buffer podria estar con capacidad, en caso de no tener, el mismo metodo de guardar
			//se encarga de volverlo a dormir...
			notifyAll();
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
			e.printStackTrace();
		}


	}

	public static void main(String[] args)
	{
		Buffer buffer = new Buffer();
		System.out.println("Caso 1 Infracomp - 2019-2");
		System.out.println("Luis Miguel Gomez Londono - 201729597");
		System.out.println("Daniel Bernal - 201519654");
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
			cliente.start();
		}
		//creacion de servidores...
		for (int i = 0; i < buffer.numeroServidores; i++) {
			Servidor servidor = new Servidor(i + 1, buffer);
			servidor.start();
		}


	}

}
