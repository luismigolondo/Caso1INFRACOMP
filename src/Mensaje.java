/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Mensaje {

	private Buffer buffer;

	private int id;

	private int contenido;

	public Mensaje() {

	}

	public void enviar() {
		buffer.guardar(this);		
	}

	public void cambiar() {
		// TODO Auto-generated method stub

	}

}
