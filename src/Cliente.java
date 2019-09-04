/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Cliente extends Thread{

	private Mensaje [] mensajes;
	//numero de mensajes
	private int numeroConsultas;
	
	public Cliente(int pNum) {
		this.numeroConsultas = pNum;
		this.mensajes = new Mensaje[numeroConsultas];
	}
	
	public void enviarMensaje(int pIdentificador)
	{
		mensajes[pIdentificador].enviar();
	}
	
	public void run () {
		
	}
	
}
