/**
 * @author luisgomez
 *
 */
public class Cliente extends Thread{

	private Mensaje [] mensajes;
	//numero de mensajes
	private int numeroConsultas;
	
	private Buffer buffer;
	
	public Cliente(int pNum, Buffer b) {
		this.numeroConsultas = pNum;
		this.mensajes = new Mensaje[numeroConsultas];
		this.buffer = b;
	}
	
	public void enviarMensaje(int pIdentificador)
	{
		mensajes[pIdentificador].enviar();
	}
	
	public void run () {
		for (int i = 0; i < mensajes.length; i++) {
			enviarMensaje(i);
		}
	}
	
}
