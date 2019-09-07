/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Mensaje {

	private Buffer buffer;
	
	private Cliente cliente;
	
	private int id;
	
	private int contenido;
	
	public Mensaje() {
		
	}
	
	public void enviar() {
		// TODO Auto-generated method stub
		
	}

	public void cambiar() {
		// TODO Auto-generated method stub
		
	}

	public void guardado() {
		//Se pudo guardar entonces pongo el cliente a dormir...
		try {
			cliente.wait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void respondido() {
		cliente.notify();
	}

}
