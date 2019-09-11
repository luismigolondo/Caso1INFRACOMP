/**
 * 
 */

/**
 * @author luisgomez
 *
 */
public class Servidor extends Thread{
	
	private Buffer buffer;
	
	private int id;
	
	public Servidor (int id, Buffer b) {
		this.buffer = b;
		this.id = id;
	}
	
	public void run () {
		System.out.println("Servidor " + id + " activado");
		while(true) {
			Mensaje retirado = buffer.retirar();
			if(retirado != null)
			{
				System.out.println("Mensaje " + retirado.getId() + " de cliente "+ retirado.getCliente().getId() +" retirado por servidor " + id);				
				retirado.cambiar();
				yield();
			}
			if(buffer.getMensajesRestantes() == 0)
			{
				System.out.println("El servidor " + id + " termino");
				break;
			}
		}
	}
	
}
