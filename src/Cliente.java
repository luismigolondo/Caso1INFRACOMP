/**
 * @author luisgomez
 *
 */
public class Cliente extends Thread{

	private int id;
	
	private Mensaje [] mensajes;
	//numero de mensajes
	private int numeroConsultas;
	
	private Buffer buffer;
	
	public Cliente(int id, int pNum, Buffer b) {
		this.id = id;
		this.numeroConsultas = pNum;
		this.buffer = b;
		this.mensajes = new Mensaje[numeroConsultas];
	}
	
	public void enviarMensaje(int pIdentificador)
	{
		buffer.guardar(mensajes[pIdentificador]);
	}
	
	public void run () {
		System.out.println("Cliente " + id + " activado.");
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i] = new Mensaje(i, buffer);
			enviarMensaje(i);
		}
	}
	
}
