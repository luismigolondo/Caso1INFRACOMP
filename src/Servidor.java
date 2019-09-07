/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Servidor extends Thread{
	
	private Buffer buffer;
	
	private Mensaje mensajeActual;
	
	public void solicitar() {
		mensajeActual = buffer.retirar();
	}
	
	public void respond()
	{
		mensajeActual.cambiar();
		buffer.guardar(mensajeActual);
	}
	
	public void run () {
		
	}
	
}
